package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.lineup.Lineup;
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
    private final Set<LineupName> lineups = new HashSet<>();
    private final Weight weight;

    /**
     * Every field must be present and not null.
     * Constructor to create a new player without lineups.
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

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Height height, JerseyNumber jerseyNumber,
                  Set<Tag> tags, Weight weight, Set<LineupName> lineups) {
        requireAllNonNull(name, phone, email, height, jerseyNumber, tags, weight, lineups);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.height = height;
        this.jerseyNumber = jerseyNumber;
        this.tags.addAll(tags);
        this.weight = weight;
        this.lineups.addAll(lineups);
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
        return Collections.unmodifiableSet(this.tags);
    }

    /**
     * Returns an immutable lineup set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<LineupName> getLineupNames() {
        return Collections.unmodifiableSet(this.lineups);
    }

    public Set<LineupName> getModifiableLineupNames() {
        return this.lineups;
    }

    public void addLineupName(Lineup lineup) {
        this.lineups.add(lineup.getLineupName());
    }

    /**
     * Replaces the old lineup name with a new lineup name
     * @param oldName The old LineupName
     * @param newName The new LineupName
     */
    public void replaceLineupName(LineupName oldName, LineupName newName) {
        this.lineups.remove(oldName);
        this.lineups.add(newName);
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
     * Returns true if some person's name is {@code targetName}.
     */
    public boolean isMatchName(Name targetName) {
        requireNonNull(targetName);
        return getName().equals(targetName);
    }

    /**
     * Returns true if the the player is in lineup.
     */
    public boolean isInLineup(Lineup lineup) {
        /* for delete command */
        return lineups.contains(lineup.getLineupName());
    }

    /**
     * Returns true if the person's jersey number is already taken.
     */
    public boolean isSameJerseyNumber(JerseyNumber jerseyNumber) {
        requireNonNull(jerseyNumber);
        return getJerseyNumber().equals(jerseyNumber);
    }

    public void removeFromLineup(Lineup lineup) {
        this.lineups.remove(lineup.getLineupName());
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLineupNames().equals(getLineupNames());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, jerseyNumber, height, tags, weight, lineups);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        final StringBuilder lineupBuilder = new StringBuilder();
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
        Set<LineupName> lineups = getLineupNames();

        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        if (!lineups.isEmpty()) {
            lineupBuilder.append("\nLineups: ");
            lineups.forEach(lineupBuilder::append);
        }
        return builder.toString();
    }
}
