import com.github.javafaker.Faker;
import javafx.beans.property.*;
import org.apache.commons.lang3.time.StopWatch;

public class Person
{

    // Properties of the person (name, address, job)
    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty street;
    private StringProperty zipCode;
    private StringProperty city;
    private StringProperty country;
    private StringProperty job;

    public Person(int id){
//        StopWatch watch = new StopWatch();
//        watch.start();
        Faker faker = new Faker();

        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(faker.name().firstName());
        this.lastName = new SimpleStringProperty(faker.name().lastName());
        this.street = new SimpleStringProperty(faker.address().streetAddress());
        this.zipCode = new SimpleStringProperty(faker.address().zipCode());
        this.city = new SimpleStringProperty(faker.address().cityName());
        this.country = new SimpleStringProperty(faker.address().country());
        this.job = new SimpleStringProperty(faker.company().name());
//        watch.stop();
//        System.out.println(watch.getTime());
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(int id) {
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