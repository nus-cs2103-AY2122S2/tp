package seedu.address.logic;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class FilterArgument {

    public static final String MESSAGE_CONSTRAINTS =
            "Filter arguments should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the filter argument must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String filterArgument;

    /**
     * Constructs a {@code FilterArgument}.
     *
     * @param argument A valid argument.
     */
    public FilterArgument(String argument) {
        requireNonNull(argument);
        checkArgument(isValidFilterArgument(argument), MESSAGE_CONSTRAINTS);
        filterArgument = argument;
    }

    /**
     * Returns true if a given string is a valid filter argument.
     */
    public static boolean isValidFilterArgument(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return filterArgument;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterArgument // instanceof handles nulls
                && filterArgument.equals(((FilterArgument) other).filterArgument)); // state check
    }

    @Override
    public int hashCode() {
        return filterArgument.hashCode();
    }
}
