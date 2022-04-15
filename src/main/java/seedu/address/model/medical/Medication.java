package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's medication in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidMedication(String)}
 */
public class Medication {

    public static final String MESSAGE_CONSTRAINTS = "Medication can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Medication}.
     *
     * @param medication Information about the patient's medication.
     */
    public Medication(String medication) {
        requireNonNull(medication);
        checkArgument(isValidMedication(medication), MESSAGE_CONSTRAINTS);
        value = medication;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidMedication(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Medication // instanceof handles nulls
                && value.equals(((Medication) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
