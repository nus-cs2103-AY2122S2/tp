package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.activity.Activity;
import seedu.address.model.person.Address;
import seedu.address.model.person.ClassCode;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Status;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STATUS = "Negative";
    public static final String DEFAULT_CLASSCODE = "2A";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Status status;
    private ClassCode classCode;
    private Set<Activity> activities;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        status = new Status(DEFAULT_STATUS);
        classCode = new ClassCode(DEFAULT_CLASSCODE);
        activities = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        status = personToCopy.getStatus();
        classCode = personToCopy.getClassCode();
        activities = new HashSet<>(personToCopy.getActivities());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withActivities(String ... activities) {
        this.activities = SampleDataUtil.getActivitySet(activities);
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
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        this.status = new Status(status);
        return this;
    }

    /**
     * Sets the {@code classCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withClassCode(String classCode) {
        this.classCode = new ClassCode(classCode);
        return this;
    }

    /**
     * Sets the {@code Activity} of the {@code Person} that we are building.
     */
    public PersonBuilder withActivity(String stringActivities) {
        Set<Activity> newSet = new HashSet<>();
        String[] activityStringArray = stringActivities.split(", ");
        for (String activityString : activityStringArray) {
            newSet.add(new Activity(activityString));
        }
        this.activities = newSet;
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, status, classCode, activities);
    }

}
