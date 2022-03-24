package seedu.trackbeau.model.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Service's duration in trackBeau.
 * Guarantees: immutable; is valid as declared in {@link #isValidDuration(String)}
 */
public class Duration {


    public static final String MESSAGE_CONSTRAINTS =
        "Duration should only contain positive numbers and have a value that is greater than 0";
    public static final String VALIDATION_REGEX = "^\\s*(?=.*[1-9])\\d*$";
    public final Integer value;

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(String duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(duration);
    }

    /**
     * Constructs a {@code Duration}.
     *
     * @param duration A valid duration.
     */
    public Duration(int duration) {
        requireNonNull(duration);
        value = duration;
    }

    /**
     * Returns true if a given string is a valid duration.
     */
    public static boolean isValidDuration(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Duration // instanceof handles nulls
            && value.equals(((Duration) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

