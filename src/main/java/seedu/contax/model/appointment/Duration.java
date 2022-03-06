package seedu.contax.model.appointment;

import static seedu.contax.commons.util.AppUtil.checkArgument;

/**
 * Represents an {@link Appointment}'s duration in minutes in the schedule.
 * Guarantees: Immutable; is valid declared as in {@link #isValidDuration(int)}
 */
public class Duration {
    public static final String MESSAGE_CONSTRAINTS = "Duration should be a non-negative integer";

    public final int duration;

    /**
     * Constructs a {@code Duration}
     *
     * @param duration A valid duration, as defined in {@link #isValidDuration(int)}
     */
    public Duration(int duration) {
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        this.duration = duration;
    }

    /**
     * Returns true if a given integer is a valid duration.
     * A duration is considered valid if it is a non-negative integer.
     *
     * @param test The integer to test.
     * @return A boolean indicating if the test integer supplied is a valid duration.
     */
    public static boolean isValidDuration(int test) {
        return test >= 0;
    }

    @Override
    public String toString() {
        return String.format("%d Minutes", duration);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Duration // instanceof handles nulls
                && duration == ((Duration) other).duration); // state check
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(duration);
    }
}
