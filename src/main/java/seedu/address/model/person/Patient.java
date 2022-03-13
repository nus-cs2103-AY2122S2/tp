package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in Medbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient extends Person {
    public Patient(Nric nric, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(nric, name, phone, email, address, tags); // TODO: Add more attributes in the future.
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getNric())
                .append("; Name: ")
                .append(getName())
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
