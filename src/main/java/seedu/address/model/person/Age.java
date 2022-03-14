package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's age in the MyGM.
 */
public class Age {
    public static final String MESSAGE_CONSTRAINTS = "Ages should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Age should be between 0 and 100 (inclusive).\n";
    public static final String VALIDATION_REGEX = "^[1-9][0-9]?$|^100$";
    public final String value;

    /**
     * Constructs a {@code Age}.
     */
    public Age(String age) {
        requireNonNull(age);
        checkArgument(isValidAge(age), MESSAGE_CONSTRAINTS);
        this.value = age;
    }

    /**
     * Checks if the given age is valid.
     */
    public static boolean isValidAge(String ageString) {
        return ageString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Age // instanceof handles nulls
                && value.equals(((Age) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
