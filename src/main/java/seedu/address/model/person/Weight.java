package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's weight in the MyGM.
 * Currently hard coded - to be further developed.
 */
public class Weight {

    public static final String MESSAGE_CONSTRAINTS = "Weights should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain numeric characters.\n"
            + "2. Weight should be between 1 and 200 (inclusive).\n"
            + "3. Weight should be a whole number.\n";
    public static final String VALIDATION_REGEX = "^([1-9]|[1-9][0-9]|[1][0-9][0-9]|20[0-0])$";
    public final String value;

    /**
     * Constructs a {@code Weight}.
     */
    public Weight(String weight) {
        requireNonNull(weight);
        checkArgument(isValidWeight(weight), MESSAGE_CONSTRAINTS);
        this.value = weight;
    }

    /**
     * Checks if the given weight is valid.
     */
    public static boolean isValidWeight(String weightString) {
        return weightString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value + "kg";
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
