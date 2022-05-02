package seedu.address.model.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Candidate's availability.
 * Guarantees: immutable; is valid as declared in {@link #isValidDay(String)}
 */
public class Availability {

    public static final String MESSAGE_CONSTRAINTS = "Availability should consists of a list of 5 numbers, "
            + "ranging from 1 to 5, separated by commas ','\nEg. avail/1,2,3,4,5";
    public static final String VALIDATION_REGEX = "^[1-5]{1}+(?:,[1-5]{1}+)*$";
    public static final String[] WEEK = {"Mon", "Tue", "Wed", "Thu", "Fri"};

    public final String availability;
    private boolean[] availabilityList = new boolean[6];
    /**
     * Constructs a {@code Availability}.
     *
     * @param availability A valid available date.
     */
    public Availability(String availability) {
        requireNonNull(availability);
        checkArgument(isValidDay(availability), MESSAGE_CONSTRAINTS);
        this.availability = availability;
        this.availabilityList = setAvailableList(availability);
    }

    /**
     * Returns true if a given string is a valid date format.
     */
    public static boolean isValidDay(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return test.split(",").length <= 5;
        }
        return false;
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
        return availability;
    }

    public boolean[] getAvailableListAsBoolean() {
        return availabilityList;
    }

    private boolean[] setAvailableList(String availability) {
        for (String day: availability.split(",")) {
            availabilityList[Integer.parseInt(day) - 1] = true;
        }
        return availabilityList;
    }
}
