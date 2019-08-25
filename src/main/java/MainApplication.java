import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;

public class MainApplication extends Application {

    private static int INITIAL_NUMBER_OF_RECORDS = 20000;
    private static int INITIAL_BATCH_INSERT_NUMBER = 100;
    private static long SCHEDULE_DELAY = 10000;
    private static long SCHEDULE_TIME_PERIOD = 200;
    private static TimeUnit SCHEDULE_TIME_UNIT = TimeUnit.MILLISECONDS;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage stage)
    {
        AtomicBoolean initialLoadFinished = new AtomicBoolean(false);

        TableView filteredTableView = getFilteredTableView();
        TableView unfilteredTableView = getUnfilteredTableView();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });

        scheduler.execute(() -> {
//            The UI thread got busy if the loop is inside Platform.runLater
//            Platform.runLater(()->{
//                // This is slow and block UI if INITIAL_NUMBER_OF_RECORDS is large (e.g. >100)
////                for (int i = 0; i < INITIAL_NUMBER_OF_RECORDS; i++) {
////                    TableViewHelper.addPerson();
////                }
//
//                TableViewHelper.addPeople(INITIAL_NUMBER_OF_RECORDS);
//
//                initialLoadFinished.set(true);
//            });

            // move UI thread to run the add to observable, use worker thraed to create fake person/people
            for (int batch = 0; batch < Math.floorDiv(INITIAL_NUMBER_OF_RECORDS, INITIAL_BATCH_INSERT_NUMBER); batch ++){
                TableViewHelper.addPeople(INITIAL_BATCH_INSERT_NUMBER);
            }

            TableViewHelper.addPeople(INITIAL_NUMBER_OF_RECORDS - Math.floorDiv(INITIAL_NUMBER_OF_RECORDS, INITIAL_BATCH_INSERT_NUMBER) * INITIAL_BATCH_INSERT_NUMBER);

            initialLoadFinished.set(true);
        });

        scheduler.scheduleAtFixedRate(() -> {
            if (initialLoadFinished.get()){
                TableViewHelper.addPerson();
            }
        }, SCHEDULE_DELAY, SCHEDULE_TIME_PERIOD, SCHEDULE_TIME_UNIT);

        Button flipPredicate = new Button("Add id by 1");
        flipPredicate.onActionProperty().setValue(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                TableViewHelper.changeList();
            }
        });

        // Create the VBox
        VBox root = new VBox();
        // Add the Table to the VBox
        root.getChildren().add(flipPredicate);
        root.getChildren().add(filteredTableView);
        root.getChildren().add(unfilteredTableView);
        // Set the Padding and Border for the VBox
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A simple TableView Example");
        // Display the Stage
        stage.show();
    }

    private TableView<Person> getFilteredTableView(){
        // Create a TableView with a list of persons
        TableView<Person> tableFiltered = new TableView<>();

        // Add rows to the TableView
        //  table.getItems().addAll(TableViewHelper.getPersonList());

        tableFiltered.setItems(TableViewHelper.getFilteredPersonList());

        // Add columns to the TableView
        tableFiltered.getColumns().addAll(TableViewHelper.getIdColumn(), TableViewHelper.getFirstNameColumn(),
                TableViewHelper.getLastNameColumn(),TableViewHelper.getStreetColumn(),
                TableViewHelper.getZipCodeColumn(), TableViewHelper.getCityColumn(), TableViewHelper.getCountryColumn());

        // Set the column resize policy to constrained resize policy
        tableFiltered.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        tableFiltered.setPlaceholder(new Label("No visible columns and/or data exist."));
        return tableFiltered;
    }

    private TableView<Person> getUnfilteredTableView(){
        // Create a TableView with a list of persons
        TableView<Person> table = new TableView<>();

        // Add rows to the TableView
          table.setItems(TableViewHelper.getPersonList());

//        tableFiltered.getItems().addAll(TableViewHelper.getFilteredPersonList());

        // Add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getIdColumn(), TableViewHelper.getFirstNameColumn(),
                TableViewHelper.getLastNameColumn(),TableViewHelper.getStreetColumn(),
                TableViewHelper.getZipCodeColumn(), TableViewHelper.getCityColumn(), TableViewHelper.getCountryColumn());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No visible columns and/or data exist."));

        return table;
    }
}