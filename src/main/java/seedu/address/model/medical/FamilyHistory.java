package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's family history in the MedBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidFamilyHistory(String)}
 */
public class FamilyHistory {

    public static final String MESSAGE_CONSTRAINTS = "Family history can take any values, and it should not be blank";

    /*
     * The first character of the familyHistory must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code FamilyHistory}.
     *
     * @param familyHistory Information about the patient's family history.
     */
    public FamilyHistory(String familyHistory) {
        requireNonNull(familyHistory);
        checkArgument(isValidFamilyHistory(familyHistory), MESSAGE_CONSTRAINTS);
        value = familyHistory;
    }

    /**
     * Returns true if given string is valid.
     */
    public static boolean isValidFamilyHistory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FamilyHistory // instanceof handles nulls
                && value.equals(((FamilyHistory) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
