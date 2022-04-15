package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.schedule.Schedule;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Telegram telegram;
    private final GitHub github;
    private final Email email;
    private final Address address;
    private final Schedule schedule;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Telegram telegram, GitHub github, Email email,
                  Address address, Schedule schedule, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.telegram = telegram;
        this.github = github;
        this.email = email;
        this.address = address;
        this.schedule = schedule;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public GitHub getGithub() {
        return github;
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

    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * Returns a schedule containing upcoming events that are happening in the next {@code daysForward} days.
     */
    public Schedule getUpcomingSchedule(int daysForward) {
        return schedule.getUpcomingSchedule(daysForward);
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
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone());
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
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getGithub().equals(getGithub())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSchedule().equals(getSchedule())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone());

        if (!getTelegram().isEmpty()) {
            builder.append("; Telegram: ")
                    .append(getTelegram());
        }

        if (!getGithub().isEmpty()) {
            builder.append("; GitHub: ")
                    .append(getGithub());
        }

        if (!getEmail().isEmpty()) {
            builder.append("; Email: ")
                    .append(getEmail());
        }

        if (!getAddress().isEmpty()) {
            builder.append("; Address: ")
                    .append(getAddress());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
