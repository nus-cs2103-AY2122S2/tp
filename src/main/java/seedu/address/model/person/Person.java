package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.image.ImageDetailsList;
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
    private final Address address;
    private final DeadlineList deadlines;
    private final Notes notes;
    private final Set<Tag> tags = new HashSet<>();
    private final Favourite favouriteStatus;
    private final ImageDetailsList imageDetailsList;
    private final HighImportance highImportanceStatus;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, DeadlineList deadlines, Notes notes,
                  Set<Tag> tags, Favourite favouriteStatus, HighImportance highImportanceStatus,
                  ImageDetailsList images) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.deadlines = deadlines;
        this.notes = notes;
        this.favouriteStatus = favouriteStatus;
        this.highImportanceStatus = highImportanceStatus;
        this.tags.addAll(tags);
        this.imageDetailsList = images;
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

    public DeadlineList getDeadlines() {
        return deadlines;
    }

    public Notes getNotes() {
        return notes;
    }

    public Favourite getFavouriteStatus() {
        return favouriteStatus;
    }

    public boolean isFavourite() {
        return favouriteStatus.isFavourite();
    }

    public ImageDetailsList getImageDetailsList() {
        return imageDetailsList;
    }

    public HighImportance getHighImportanceStatus() {
        return highImportanceStatus;
    }

    public boolean hasHighImportance() {
        return highImportanceStatus.hasHighImportance();
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getDeadlines().equals(getDeadlines())
                && otherPerson.getNotes().equals(getNotes())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getFavouriteStatus().equals(getFavouriteStatus())
                && otherPerson.getImageDetailsList().equals(getImageDetailsList())
                && otherPerson.getHighImportanceStatus().equals(getHighImportanceStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, deadlines, notes, tags, favouriteStatus, imageDetailsList,
                highImportanceStatus);
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
                .append("; Deadline(s): ")
                .append(getDeadlines())
                .append("; Notes: ")
                .append(getNotes())
                .append("; Favourite: ")
                .append(getFavouriteStatus())
                .append("; Importance Status: ")
                .append(getHighImportanceStatus());

        Set<Tag> tags = getTags();

        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
