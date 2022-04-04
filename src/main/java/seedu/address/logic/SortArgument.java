package seedu.address.logic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class SortArgument {

    public static final String VALIDATION_REGEX = "asc|dsc";

    public static final String MESSAGE_CONSTRAINTS = "Invalid sort argument! Sort argument is either asc or dsc";

    public final String argument;

    /**
     * Constructs a {@code SortArgument}.
     *
     * @param argument A valid argument.
     */
    public SortArgument(String argument) {
        requireNonNull(argument);
        checkArgument(isValidSortArgument(argument), MESSAGE_CONSTRAINTS);
        this.argument = argument;
    }

    /**
     * Tests is a given string is a valid {@code SortArgument} according to the regular expression.
     */
    public static boolean isValidSortArgument(String test) {
        return test.toLowerCase().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return argument;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortArgument // instanceof handles nulls
                && argument.equals(((SortArgument) other).argument)); // state check
    }

    @Override
    public int hashCode() {
        return argument.hashCode();
    }
}
