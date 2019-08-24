import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static javafx.application.Application.launch;

public class MainApplication extends Application {

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage stage)
    {
        AtomicInteger personId = new AtomicInteger(2);

        TableView filteredTableView = getFilteredTableView();
        TableView unfilteredTableView = getUnfilteredTableView();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        });
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(()->{
                TableViewHelper.addPerson(personId.incrementAndGet());
//                filteredTableView.refresh();
//                unfilteredTableView.refresh();

                System.out.println("Filtered List: ");
                TableViewHelper.getFilteredPersonList().forEach(p -> System.out.println("Person " + p.getId() + " " + p.getFirstName()));
            });
            System.out.println("added person. List length =" + TableViewHelper.getPersonList().size());

        },0, 5000, TimeUnit.MILLISECONDS);

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