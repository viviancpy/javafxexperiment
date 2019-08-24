import javafx.beans.property.*;

import java.util.Observable;
import java.util.UUID;

public class Person
{
    // Properties of the person (name, address, job)
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,5));
    private StringProperty street = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,10));
    private StringProperty zipCode = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,4));
    private StringProperty city = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,5));
    private StringProperty country = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,6));
    private StringProperty job = new SimpleStringProperty(UUID.randomUUID().toString().substring(0,7));

    public IntegerProperty idProperty() {
        return id;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.setValue(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getJob() {
        return job.get();
    }

    public void setJob(String job) {
        this.job.set(job);
    }
}