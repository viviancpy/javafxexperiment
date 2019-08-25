import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import utils.FXCollectionsWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class TableViewHelper
{

    // Backed by LinkedList - not work
    // private static ObservableList<Person> personMasterList = FXCollections.<Person>observableList(new LinkedList<>(), extractor());

    // Backed by Array List - work
    // private static ObservableList<Person> personMasterList = FXCollections.<Person>observableList(new ArrayList<>(), extractor());

    private static ObservableList<Person> personMasterList = FXCollectionsWrapper.<Person>observableList(new LinkedList<>(), extractor());

    private static ObservableList<Person> filteredPersonList = getPersonList()
    .filtered(new Predicate<Person>() {
        @Override
        public boolean test(Person person) {
            return person.getId() % 2 == 0;
        }
    }).sorted(new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            return Integer.compare(o1.getId(), o2.getId());
        }
    });

    public static Callback<Person, Observable[]> extractor() {
        return (Person p) -> new Observable[]{p.idProperty()};
    }

    public static void addPerson(){
        Person randomPerson = new Person(personMasterList.size());
        Platform.runLater(()->personMasterList.add(randomPerson));

    }

    public static void addPeople(int numberOfPeople){
        List<Person> randomPeople = new ArrayList<>(numberOfPeople);
        for (int i = personMasterList.size(); i < personMasterList.size() + numberOfPeople; i++) {
            randomPeople.add(new Person(i));
        }
        Platform.runLater(()->personMasterList.addAll(randomPeople));
    }

    public static void changeList(){
        personMasterList.forEach(p -> p.setId(p.getId()+1));
    }

    // Returns an observable list of persons
    public static ObservableList<Person> getPersonList()
    {
        return personMasterList;
    }

    public static ObservableList<Person> getFilteredPersonList(){
        return filteredPersonList;
    }

    // Returns Person Id TableColumn
    public static TableColumn<Person, Integer> getIdColumn()
    {
        TableColumn<Person, Integer> idCol = new TableColumn<>("Id");
        PropertyValueFactory<Person, Integer> idCellValueFactory = new PropertyValueFactory<>("id");
        idCol.setCellValueFactory(idCellValueFactory);
        idCol.setSortType(TableColumn.SortType.DESCENDING);
        return idCol;
    }

    // Returns First Name TableColumn
    public static TableColumn<Person, String> getFirstNameColumn()
    {
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        PropertyValueFactory<Person, String> firstNameCellValueFactory = new PropertyValueFactory<>("firstName");
        firstNameCol.setCellValueFactory(firstNameCellValueFactory);
        return firstNameCol;
    }

    // Returns Last Name TableColumn
    public static TableColumn<Person, String> getLastNameColumn()
    {
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        PropertyValueFactory<Person, String> lastNameCellValueFactory = new PropertyValueFactory<>("lastName");
        lastNameCol.setCellValueFactory(lastNameCellValueFactory);
        return lastNameCol;
    }

    // Returns Street TableColumn 
    public static TableColumn<Person, String> getStreetColumn()
    {
        TableColumn<Person, String> streetCol = new TableColumn<>("Street");
        PropertyValueFactory<Person, String> streetCellValueFactory = new PropertyValueFactory<>("street");
        streetCol.setCellValueFactory(streetCellValueFactory);
        return streetCol;
    }

    // Returns ZipCode TableColumn
    public static TableColumn<Person, String> getZipCodeColumn()
    {
        TableColumn<Person, String> zipCodeCol = new TableColumn<>("Zip Code");
        PropertyValueFactory<Person, String> zipCodeCellValueFactory = new PropertyValueFactory<>("zipCode");
        zipCodeCol.setCellValueFactory(zipCodeCellValueFactory);
        return zipCodeCol;
    }

    /* Returns City TableColumn */
    public static TableColumn<Person, String> getCityColumn()
    {
        TableColumn<Person, String> cityCol = new TableColumn<>("City");
        PropertyValueFactory<Person, String> cityCellValueFactory = new PropertyValueFactory<>("city");
        cityCol.setCellValueFactory(cityCellValueFactory);
        return cityCol;
    }

    // Returns Country TableColumn
    public static TableColumn<Person, String> getCountryColumn()
    {
        TableColumn<Person, String> countryCol = new TableColumn<>("Country");
        PropertyValueFactory<Person, String> countryCellValueFactory = new PropertyValueFactory<>("country");
        countryCol.setCellValueFactory(countryCellValueFactory);
        return countryCol;
    }

}