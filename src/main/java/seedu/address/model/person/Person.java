package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.name.Name;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.ListUtil;

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
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Note> strengths = new ArrayList<>();
    private final List<Note> weaknesses = new ArrayList<>();
    private final List<Note> miscellaneous = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  List<Note> strength, List<Note> weaknesses, List<Note> misc) {
        requireAllNonNull(name, phone, email, address, tags, strength, weaknesses, misc);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.strengths.addAll(strength);
        this.weaknesses.addAll(weaknesses);
        this.miscellaneous.addAll(misc);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Note> getStrengths() {
        return Collections.unmodifiableList(strengths);
    }
    /**
     * Returns an immutable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Note> getWeaknesses() {
        return Collections.unmodifiableList(weaknesses);
    }
    /**
     * Returns an immutable note list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Note> getMiscellaneous() {
        return Collections.unmodifiableList(miscellaneous);
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getStrengths().equals(getStrengths())
                && otherPerson.getWeaknesses().equals(getWeaknesses())
                && otherPerson.getMiscellaneous().equals(getMiscellaneous());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, strengths, weaknesses, miscellaneous);
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
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        List<Note> strengths = getStrengths();
        if (!strengths.isEmpty()) {
            builder.append("; Strengths: ");
            ListUtil.toIndexedStringList(strengths)
                    .forEach(builder::append);
        }

        List<Note> weaknesses = getWeaknesses();
        if (!weaknesses.isEmpty()) {
            builder.append("; Weaknesses: ");
            ListUtil.toIndexedStringList(weaknesses)
                    .forEach(builder::append);
        }

        List<Note> miscellaneous = getMiscellaneous();
        if (!miscellaneous.isEmpty()) {
            builder.append("; Misc: ");
            ListUtil.toIndexedStringList(miscellaneous)
                    .forEach(builder::append);
        }
        return builder.toString();
    }
}
