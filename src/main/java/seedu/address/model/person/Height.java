package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's height in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Height {

    public static final String MESSAGE_CONSTRAINTS = "Heights should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Height should be between 1 and 300 (inclusive).\n"
            + "3. Height should be a whole number.\n";
    public static final String VALIDATION_REGEX = "^([1-9]|[1-9][0-9]|[2][0-9][0-9]|30[0-0])$";
    public final String value;

    /**
     * Constructs a {@code Height}.
     */
    public Height(String value) {
        requireNonNull(value);
        checkArgument(isValidHeight(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Checks if the given height is valid.
     *
     * @param heightString Input height.
     * @return True if the height is valid.
     */
    public static boolean isValidHeight(String heightString) {
        return heightString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value + "cm";
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
