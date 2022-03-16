package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's immunization history in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidImmunizationHistory(String)}
 */
public class ImmunizationHistory {

    public static final String MESSAGE_CONSTRAINTS =
            "Immunization history can take any values, and it should not be blank";

    /*
     * The first character of the familyHistory must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code ImmunizationHistory}.
     *
     * @param familyHistory Information about the patient's family history.
     */
    public ImmunizationHistory(String familyHistory) {
        requireNonNull(familyHistory);
        checkArgument(isValidImmunizationHistory(familyHistory), MESSAGE_CONSTRAINTS);
        value = familyHistory;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidImmunizationHistory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImmunizationHistory // instanceof handles nulls
                && value.equals(((ImmunizationHistory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
