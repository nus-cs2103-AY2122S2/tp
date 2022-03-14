package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.activity.Activity;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final ClassCode classCode;

    // Data fields
    private final Address address;
    private final Set<Activity> activities = new HashSet<>();
    private Status status;



    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Status status,
                  ClassCode classCode, Set<Activity> activities) {
        requireAllNonNull(name, phone, email, address, status, classCode, activities);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.status = status;
        this.classCode = classCode;
        this.activities.addAll(activities);
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

    public Status getStatus() {
        return status;
    }

    public ClassCode getClassCode() {
        return classCode;
    }

    /**
     * Returns an immutable activity set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Activity> getActivities() {
        return Collections.unmodifiableSet(activities);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getActivities().equals(getActivities())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getClassCode().equals(getClassCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, status, classCode, activities);
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
                .append("; Status: ")
                .append(getStatus())
                .append("; Class Code: ")
                .append(getClassCode());

        Set<Activity> activities = getActivities();
        if (!activities.isEmpty()) {
            builder.append("; Activities: ");
            activities.forEach(builder::append);
        }
        return builder.toString();
    }
}
