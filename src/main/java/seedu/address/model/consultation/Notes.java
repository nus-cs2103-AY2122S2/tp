package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Notes in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Notes {

    public static final String MESSAGE_CONSTRAINTS =
            "Notes should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public String notes;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Notes(String notes) {
        requireNonNull(notes);
        checkArgument(isValid(notes), MESSAGE_CONSTRAINTS);
        this.notes = notes;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValid(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return notes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Notes // instanceof handles nulls
                && notes.equals(((Notes) other).notes)); // state check
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }

}
