package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's NUSNET ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class NusNetId {
    public static final String MESSAGE_CONSTRAINTS =
            "NUSNET IDs should contain an 'e' followed by 7 numerical digits";
    public static final String VALIDATION_REGEX = "^e\\d{7}";
    public static final String NULL_INPUT = "NULL_INPUT";

    public final String id;

    /**
     * Constructs a {@code NusNetId}.
     *
     * @param value an NUSNET ID.
     */
    public NusNetId(String value) {
        requireNonNull(value);
        checkArgument(isValidId(value), MESSAGE_CONSTRAINTS);
        this.id = value;

    }

    /**
     * Returns true if a given string is a valid NUSNET ID.
     */
    public static boolean isValidId(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NusNetId // instanceof handles nulls
                && id.equals(((NusNetId) other).id)); // state check
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
