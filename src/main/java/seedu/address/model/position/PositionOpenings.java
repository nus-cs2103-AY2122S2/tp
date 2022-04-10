package seedu.address.model.position;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.ImmutableCounter;

/**
 * Represents the number of openings in a Position in HireLah.
 * Guarantees: is non-negative
 */
public class PositionOpenings implements ImmutableCounter {

    public static final String MESSAGE_CONSTRAINTS =
            "Number of openings should only contain numbers, and it should be between 1 to 5 digits.\n"
            + "Number must not start with a 0.";

    /*
     * Number of openings must be a valid integer with 1 to 5 digits.
     */
    public static final String VALIDATION_REGEX = "\\d{1,5}";

    private Integer numOfOpenings;

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
     * Constructs a {@code PositionOpenings}
     * Constructor is used internally to increment or decrement the counter
     */
    private PositionOpenings(Integer openings) {
        requireNonNull(openings);
        numOfOpenings = openings;
    }

    /**
     * Returns true if a given string is a valid number for openings.
     */
    public static boolean isValidNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public PositionOpenings increment() {
        return new PositionOpenings(numOfOpenings + 1);
    }

    @Override
    public PositionOpenings decrement() {
        assert numOfOpenings > 0;
        return new PositionOpenings(numOfOpenings - 1);
    }

    @Override
    public Integer getCount() {
        return numOfOpenings;
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
