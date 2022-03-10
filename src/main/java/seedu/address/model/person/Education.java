package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;
/**
 * Represents a Person's education tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Education extends Tag {
    public final String value;

    /**
     * Constructs a {@code Education}.
     *
     * @param education an education tag.
     */
    public Education(String education) {
        super(requireNonNull(education));
        this.value = education.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Education
                && this.value.equals(((Education) other).value));
    }
}
