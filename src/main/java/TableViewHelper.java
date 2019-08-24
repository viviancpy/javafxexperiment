import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.function.Predicate;

public class TableViewHelper
{
    // Backed by LinkedList - not work
    // private static ObservableList<Person> personMasterList = FXCollections.<Person>observableList(new LinkedList<>(), extractor());

    // Backed by Array List - work
    // private static ObservableList<Person> personMasterList = FXCollections.<Person>observableList(new ArrayList<>(), extractor());

    private static ObservableList<Person> personMasterList = FXCollectionsWrapper.<Person>observableList(new LinkedList<>(), extractor());

    private static ObservableList<Person> filteredPersonList = getPersonList().filtered(new Predicate<Person>() {
        @Override
        public boolean test(Person person) {
            return person.getId() % 2 == 0;
        }
    });

    public static Callback<Person, Observable[]> extractor() {
        return (Person p) -> new Observable[]{p.idProperty()};
    }

    static{
        Person p1 = new Person(){{setId(1); setFirstName(Character.toString((char)(1+64)));}};
        Person p2 = new Person(){{setId(2); setFirstName(Character.toString((char)(2+64)));}};
        personMasterList.addAll(p1,p2);
    }

    public static void addPerson(int id){
        Person randomPerson = new Person(){{setId(id); setFirstName(Character.toString((char)(id+64)));}};
        personMasterList.add(randomPerson);
    }

    public static void changeList(){
        personMasterList.forEach(p -> p.setId(p.getId()+1));
    }

    // Returns an observable list of persons
    public static ObservableList<Person> getPersonList()
    {
//        return FXCollections.<Person>observableArrayList(p1, p2, p3, p4, p5, p6);
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