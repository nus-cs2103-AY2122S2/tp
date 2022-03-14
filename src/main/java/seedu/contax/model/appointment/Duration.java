package seedu.contax.model.appointment;

import static seedu.contax.commons.util.AppUtil.checkArgument;

/**
 * Represents an {@link Appointment}'s duration in minutes in the schedule.
 * Guarantees: Immutable; is valid declared as in {@link #isValidDuration(int)}.
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Duration should be a positive integer";

    public final int value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration A valid duration, as defined in {@link #isValidDuration(int)}.
     */
    public Duration(int duration) {
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.value = duration;
    }

    /**
     * Returns true if a given integer is a valid duration.
     * A duration is considered valid if it is a positive integer.
     *
     * @param test The integer to test.
     * @return A boolean indicating if the test integer supplied is a valid duration.
     */
    public static boolean isValidDuration(int test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return String.format("%d Minutes", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && value == ((Duration) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}
