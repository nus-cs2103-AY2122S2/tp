package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isSameStringIgnoreCases;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name implements Comparable<Name> {

    public static final String MESSAGE_CONSTRAINTS =
            "Name should only contain alphanumeric characters, punctuations (except '/') and spaces, "
                    + "and should not start with punctuations.\n"
                    + "Name has a character limit of 1-255 characters.";

    /*
     * The first character of the name must not be a whitespace or punctuation.
     * For other characters, they must be alphanumeric or spaces.
     * For all characters, slashes are not allowed.
     * The maximum number of characters allowed is 255.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum}\\p{Punct}\\s&&[^/]]{0,254}";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        if (test == null) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Checks whether two {@code Name}s are equal.
     * If two {@code Name}s contain the same {@code String}, ignoring the cases,
     * they are considered as the same.
     *
     * @param other the other {@code Object} to be compared with
     * @return true if {@code other} is a {@code Name} and has the same {@code String} internally, ignoring the cases
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && isSameStringIgnoreCases(this.fullName, ((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    @Override
    public int compareTo(Name n) {
        return this.fullName.toLowerCase()
                .compareTo(n.fullName.toLowerCase());
    }
}
