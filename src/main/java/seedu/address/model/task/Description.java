package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's description in the task list.
 * Guarantees: immutable;
 */
public class Description implements Comparable<Description> {

    public static final String MESSAGE_CONSTRAINTS =
            "Description should only contain alphanumeric characters, punctuations (except '/') and spaces, "
                    + "and should not start with punctuations.\n "
                    + "Description has a character limit of 1-255 characters.";

    /*
     * The first character of the name must not be a whitespace or punctuation.
     * For other characters, they must be alphanumeric or spaces.
     * For all characters, slashes are not allowed.
     * The maximum number of characters allowed is 255.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Punct}\\s&&[^/]]{0,254}";
    public final String value;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        value = description;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        if (test == null) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Description d) {
        return this.value.compareTo(d.value);
    }
}
