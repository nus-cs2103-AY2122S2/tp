package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a contact in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Telegram telegramUsername;
    private boolean isBeingEdited;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Contact(Name name, Phone phone, Email email, Telegram id, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, id, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramUsername = id;
        this.isBeingEdited = false;
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

    public Telegram getTelegram() {
        return telegramUsername;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean getEditStatus() {
        return isBeingEdited;
    }

    public void setEditStatus() {
        this.isBeingEdited = !isBeingEdited;
    }

    /**
     * Returns true if both contacts have at least one identity field that is the same and
     * neither contacts are being edited.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getEditStatus() == getEditStatus()
                && (otherContact.getEmail().equals(getEmail())
                        || otherContact.getName().equals(getName())
                        || otherContact.getPhone().equals(getPhone())
                        || otherContact.getTelegram().equals(getTelegram()));
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherPerson = (Contact) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegramUsername, tags);
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Telegram: ")
                .append(getTelegram())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }



}
