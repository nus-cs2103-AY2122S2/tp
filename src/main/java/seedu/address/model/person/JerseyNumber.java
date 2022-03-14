package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's jersey number in the MyGM.
 */
public class JerseyNumber {

    public static final String MESSAGE_CONSTRAINTS = "Jersey numbers should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Jersey number should be between 0 and 99 (inclusive).\n";
    public static final String VALIDATION_REGEX = "^[0-9][0-9]?$|^99$";
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
                || (other instanceof Email // instanceof handles nulls
                && value.equals(((Age) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
