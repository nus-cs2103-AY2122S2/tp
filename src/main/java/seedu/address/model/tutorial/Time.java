package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the starting time of a Tutorial in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = "Times should be provided in the format of hh:mm";
    public static final String VALIDATION_REGEX = "^([0-1][0-9]|2[0-3]):[0-5][0-9]";
    public final String time;

    /**
     * Constructs a {@code Time}.
     *
     * @param value represents the position of the day in the week.
     */
    public Time(String value) {
        requireNonNull(value);
        checkArgument(isValidTime(value), MESSAGE_CONSTRAINTS);
        time = value;
    }

    /**
     * Returns true if given String represents a valid time of the day.
     */
    public static boolean isValidTime(String value) {
        return value.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
