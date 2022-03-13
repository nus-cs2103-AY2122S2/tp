package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the number of openings in a Position in HireLah.
 * Guarantees: is non-negative
 */
public class PositionOpenings {

    public static final String MESSAGE_CONSTRAINTS =
            "Number of openings should be a valid non-negative integer.";

    /*
     * Number of openings must be a valid integer with 1 to 5 digits.
     */
    public static final String VALIDATION_REGEX = "\\d{1,5}";

    public final Integer numOfOpenings;

    /**
     * Constructs a {@code PositionOpenings}
     *
     * @param openings A valid non-negative integer that is between 1 and 3 digits.
     */
    public PositionOpenings(String openings) {
        requireNonNull(openings);
        checkArgument(isValidNumber(openings), MESSAGE_CONSTRAINTS);
        numOfOpenings = Integer.parseInt(openings);
    }

    /**
     * Returns true if a given string is a valid number for openings.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return numOfOpenings.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PositionOpenings // instanceof handles nulls
                && numOfOpenings.equals(((PositionOpenings) other).numOfOpenings)); // state check
    }

    @Override
    public int hashCode() {
        return numOfOpenings.hashCode();
    }

}
