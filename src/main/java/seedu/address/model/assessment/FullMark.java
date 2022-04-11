package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the full marks for an Assessment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFullMark(String)}
 */
public class FullMark {
    public static final String MESSAGE_CONSTRAINTS = "The full mark should be an integer between 1 and 1000 inclusive";
    /**
     * The full mark must be an integer larger than 0
     */
    public static final String VALIDATION_REGEX = "^([1-9][0-9]?[0-9]?|1000)";
    public final int fullMark;

    /**
     * Constructs a {@code FullMark}.
     *
     * @param value a full mark.
     */
    public FullMark(String value) {
        requireNonNull(value);
        checkArgument(isValidFullMark(value), MESSAGE_CONSTRAINTS);
        fullMark = Integer.parseInt(value);
    }

    /**
     * Returns true if a given string is a valid weightage.
     */
    public static boolean isValidFullMark(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(fullMark);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof FullMark
                && fullMark == ((FullMark) other).fullMark);
    }

    @Override
    public int hashCode() {
        return fullMark;
    }
}
