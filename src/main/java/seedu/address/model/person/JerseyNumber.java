package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's jersey number in MyGM.
 */
public class JerseyNumber implements Comparable<JerseyNumber> {

    public static final String MESSAGE_CONSTRAINTS = "Jersey numbers should adhere to the following constraints:\n"
            + "1. Jersey number should only contain numeric characters.\n"
            + "2. Jersey number should be between 0 and 99 (inclusive).\n"
            + "3. Single digit jersey number must be in single digit. E.g '1' and not '01'";
    public static final String VALIDATION_REGEX = "^([0-9]|[1-9][0-9])$";
    public final String value;

    /**
     * Constructs a {@code JerseyNumber}.
     */
    public JerseyNumber(String value) {
        requireNonNull(value);
        checkArgument(isValidJerseyNumber(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Checks if the given jersey number is valid.
     */
    public static boolean isValidJerseyNumber(String jerseyNumberString) {
        return jerseyNumberString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JerseyNumber // instanceof handles nulls
                && value.equals(((JerseyNumber) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(JerseyNumber jn) {
        return Integer.parseInt(value) - Integer.parseInt(jn.value);
    }
}
