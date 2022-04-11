package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.telegram.Telegram;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Faculty faculty;
    private final Role role;

    // Data fields
    private final Telegram telegram;
    private final Set<Tag> tags = new HashSet<>();
    private final Favourite favourite;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Faculty faculty, Role role, Telegram telegram,
                  Favourite favourite, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, role, telegram, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
        this.role = role;
        this.telegram = telegram;
        this.favourite = favourite;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public Role getRole() {
        return role;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Favourite getFavourite() {
        return favourite;
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
     * Returns true if both persons have the same name, and same favourite
     * This defines a weaker notion of equality between two persons, but stronger than just name alone.
     */
    public boolean isSameFavouritePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        boolean result = otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getFavourite().equals(getFavourite());

        return result;
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEmail(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same email.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePhone(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getFaculty().equals(getFaculty())
                && otherPerson.getRole().equals(getRole())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getFavourite().equals(getFavourite())
                && otherPerson.getTags().equals(getTags());
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, faculty, role, telegram, favourite, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; \nPhone: ")
                .append(getPhone())
                .append("; \nEmail: ")
                .append(getEmail())
                .append("; \nFaculty: ")
                .append(getFaculty())
                .append("; \nRole: ")
                .append(getRole())
                .append("; \nTelegram: ")
                .append(getTelegram())
                .append("; \nFavourite: ")
                .append(getFavourite());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; \nTags: ");
            tags.forEach(builder::append);
        }

        return builder.toString();
    }

}
