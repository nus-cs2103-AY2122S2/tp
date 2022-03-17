package seedu.address.model.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Candidate's availability.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Availability {

    public static final String MESSAGE_CONSTRAINTS = "Availability should consists of a list of numbers from 1 to 7, "
            + "separated by commas ','";
    public static final String VALIDATION_REGEX = "^[1-7]{1}+(?:,[1-7]{1}+)*$";

    public final String availability;

    /**
     * Constructs a {@code Availability}.
     *
     * @param availability A valid available date.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidDate(availability), MESSAGE_CONSTRAINTS);
        this.availability = availability;
    }

    /**
     * Returns true if a given string is a valid date format.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Availability // instanceof handles nulls
                && availability.equals(((Availability) other).availability)); // state check
    }

    @Override
    public int hashCode() {
        return availability.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + availability + ']';
    }
}
