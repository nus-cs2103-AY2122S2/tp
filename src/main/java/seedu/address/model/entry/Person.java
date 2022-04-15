package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person extends Entry {

    // Identity fields
    private final Phone phone;
    private final Email email;

    // Data fields
    private Name companyName;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Name companyName, Phone phone, Email email, Set<Tag> tags, boolean isArchived) {
        super(name, tags, isArchived);
        requireAllNonNull(phone, email, companyName);
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Constructor if no isArchived value is provided; default value is false.
     */
    public Person(Name name, Name companyName, Phone phone, Email email, Set<Tag> tags) {
        this(name, companyName, phone, email, tags, false);
    }

    public Name getCompanyName() {
        return this.companyName;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns true if both entries are a person and have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        if (!(otherEntry instanceof Person)) {
            return false;
        }

        return otherEntry.getName().equals(getName());
    }

    @Override
    public void updateCompanyName(String oldName, String newName) {
        if (hasCompanyName(oldName)) {
            companyName = new Name(newName);
        }
    }

    @Override
    public boolean hasCompanyName(String testName) {
        return testName.equals(companyName.toString());
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
                && otherPerson.getCompanyName().equals(getCompanyName())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), phone, email, companyName, getTags());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Company: ")
                .append(getCompanyName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
