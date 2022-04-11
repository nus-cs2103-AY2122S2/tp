package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final GitUsername gitUsername;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Constructs a Person object using 5 fields: name, Phone, Email, Address, and any number of tags.
     *
     * @param name Name of Person.
     * @param phone Phone Number of Person.
     * @param email Email address of Person.
     * @param gitUsername Github GitUsername of Person.
     * @param tags Tags for Person.
     */
    public Person(Name name, Phone phone, Email email, GitUsername gitUsername, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, gitUsername, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gitUsername = gitUsername;
        this.tags.addAll(tags);
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

    public GitUsername getUsername() {
        return this.gitUsername;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons are the same object.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.equals(this);
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
        return otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getUsername().equals(getUsername())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, gitUsername, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Github: ")
                .append(getUsername());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ")
                    .append(tags.stream().<CharSequence>map(Tag::toString).collect(Collectors.joining(", ")));
        }
        return builder.toString();
    }

}
