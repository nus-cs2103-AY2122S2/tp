package seedu.address.model.entry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company extends Entry {

    // Identity fields
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public Company(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(phone, email, address);
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Creates a dummy Company object with the given name.
     * @param companyName The name of the dummy Company.
     * @return The dummy Company.
     */
    public static Company createDummyCompany(Name companyName) {
        return new Company(companyName, new Phone("1234"), new Email("a@a.com"), new Address("a"), new HashSet<>());
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
     * Returns true if both entries are a company and have the same name.
     * This defines a weaker notion of equality between two companies.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }

        if (!(otherEntry instanceof Company)) {
            return false;
        }

        return otherEntry.getName().equals(getName());
    }

    @Override
    public void updateCompanyName(String oldName, String newName) {
        return;
    }

    /**
     * Returns true if both companies have the same identity and data fields.
     * This defines a stronger notion of equality between two companies.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Company)) {
            return false;
        }

        Company otherPerson = (Company) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), phone, email, address, getTags());
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
        return builder.toString();
    }
}
