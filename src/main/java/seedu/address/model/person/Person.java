package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Job job;
    private final Stage stage;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Job job, Stage stage) {
        requireAllNonNull(name, phone, email, address, job, stage);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.job = job;
        this.stage = stage;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Job getJob() {
        return job;
    }

    public Stage getStage() {
        return stage;
    }
    /**
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getName().equals(getName()));
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getJob().equals(getJob())
                && otherPerson.getStage().equals(getStage());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, job, stage);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Job: ")
                .append(getJob())
                .append("; Stage: ")
                .append(getStage());

        return builder.toString();
    }

}
