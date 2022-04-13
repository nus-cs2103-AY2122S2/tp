package seedu.tinner.model.role;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.commons.util.AppUtil.checkArgument;

/**
 * Represents a Role's name in the role list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class RoleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Role names should only contain alphanumeric characters, spaces and "
                    + "ending with an optional pair of round brackets to specify the role type. "
                    + "It should not be blanked, and have a character limit of at most 30";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*\\(?[\\p{Alnum} ]*\\)?";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public RoleName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return (test.length() <= 30) && (test.matches(VALIDATION_REGEX)) && areValidBrackets(test);
    }

    private static boolean areValidBrackets(String test) {
        int count = 0;
        for (char c : test.toCharArray()) {
            if (c == '(') {
                count += 1;
            }
            if (c == ')') {
                count -= 1;
            }
            if ((count < -1) || (count > 1)) {
                return false;
            }
        }
        return count == 0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoleName // instanceof handles nulls
                && value.replaceAll("\\s+", "").toLowerCase()
                .equals(((RoleName) other).value.replaceAll("\\s+", "").toLowerCase())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
