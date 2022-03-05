package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Group in the UniBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidGroup(String)}
 */
public class Group {

    public static final String MESSAGE_CONSTRAINTS = "Group can take any values";

    /*
     * The first character of the group must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Group}.
     *
     * @param group A valid group.
     */
    public Group(String group) {
        requireNonNull(group);
        checkArgument(isValidGroup(group), MESSAGE_CONSTRAINTS);
        value = group;
    }

    /**
     * Returns true if a given string is a valid Group.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Office // instanceof handles nulls
                && value.equals(((Office) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
