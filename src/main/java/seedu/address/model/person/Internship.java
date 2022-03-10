package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's internship tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Internship extends Tag {
    public final String value;

    /**
     * Constructs a {@code Internship}.
     *
     * @param internship an internship tag.
     */
    public Internship(String internship) {
        super(requireNonNull(internship));
        this.value = internship.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Internship
                && this.value.equals(((Internship) other).value));
    }
}
