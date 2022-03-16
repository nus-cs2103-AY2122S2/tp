package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidEthnicity(String)}
 */
public class Ethnicity {

    public static final String MESSAGE_CONSTRAINTS = "Ethinicity can take any values, and it should not be blank";

    /*
     * The first character of the familyHistory must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ImmunizationHistory}.
     *
     * @param ethnicity Patient's ethnicity.
     */
    public Ethnicity(String ethnicity) {
        requireNonNull(ethnicity);
        checkArgument(isValidEthnicity(ethnicity), MESSAGE_CONSTRAINTS);
        value = ethnicity;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidEthnicity(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ethnicity // instanceof handles nulls
                && value.equals(((Ethnicity) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
