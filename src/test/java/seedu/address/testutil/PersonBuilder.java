package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Job;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Stage;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_JOB = "Software Engineer";
    public static final String DEFAULT_STAGE = "INPROGRESS";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Job job;
    private Stage stage;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        job = new Job(DEFAULT_JOB);
        stage = new Stage(DEFAULT_STAGE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        job = personToCopy.getJob();
        stage = personToCopy.getStage();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Job} of the {@code Person} that we are building.
     */
    public PersonBuilder withJob(String job) {
        this.job = new Job(job);
        return this;
    }

    /**
     * Sets the {@code Stage} of the {@code Person} that we are building.
     */
    public PersonBuilder withStage(String stage) {
        this.stage = new Stage(stage);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, job, stage);
    }

}
