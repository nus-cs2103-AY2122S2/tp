package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.lineup.LineupName;
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

    // Data fields
    private final Height height;
    private final JerseyNumber jerseyNumber;
    private final Set<Tag> tags = new HashSet<>();
    private final Weight weight;
    //private final List<LineupName> lineupName;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Height height, JerseyNumber jerseyNumber,
                  Set<Tag> tags, Weight weight) {
        requireAllNonNull(name, phone, email, height, jerseyNumber, tags, weight);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.height = height;
        this.jerseyNumber = jerseyNumber;
        this.tags.addAll(tags);
        this.weight = weight;
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

    public Height getHeight() {
        return height;
    }

    public JerseyNumber getJerseyNumber() {
        return jerseyNumber;
    }

    public Weight getWeight() {
        return weight;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getHeight().equals(getHeight())
                && otherPerson.getWeight().equals(getWeight())
                && otherPerson.getJerseyNumber().equals(getJerseyNumber())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, jerseyNumber, height, tags, weight);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nHeight: ")
                .append(getHeight())
                .append("\nWeight: ")
                .append(getWeight())
                .append("\nJerseyNumber: ")
                .append(getJerseyNumber());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
