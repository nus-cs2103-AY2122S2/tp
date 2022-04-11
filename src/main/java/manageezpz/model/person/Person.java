package manageezpz.model.person;

import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private int numOfTasks;

    /**
     * Constructs an {@code Person}.
     * @param name A valid name.
     * @param phone A valid phone number.
     * @param email A valid Email.
     * @param numOfTasks the number of task assigned to the person.
     */
    public Person(Name name, Phone phone, Email email, int numOfTasks) {
        requireAllNonNull(name, phone, email);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.numOfTasks = numOfTasks;
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

    public int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Returns true if both persons have the same name or email or phone number.
     * This defines a weaker notion of equality between two persons.
     * @param otherPerson the person to check against.
     * @return true if both persons are the same, false otherwise.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (otherPerson.getName().equals(getName())
                || otherPerson.getEmail().equals(getEmail())
                || otherPerson.getPhone().equals(getPhone()));
    }

    /**
     * Increases the number of tasks by one.
     */
    public void increaseTaskCount() {
        this.numOfTasks = numOfTasks + 1;
    }

    /**
     * Decreases the number of tasks by one.
     * At any time, the number of tasks should not be lesser than zero.
     */
    public void decreaseTaskCount() {
        assert numOfTasks >= 0 : "numOfTasks should not be lesser than 0";
        this.numOfTasks = numOfTasks - 1;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());
        return builder.toString();
    }

}
