package seedu.address.model.testresult;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Medical Test in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMedicalTest(String)}
 */
public class MedicalTest {

    public static final String MESSAGE_CONSTRAINTS = "Medical tests can take any values, and it should not be blank";

    /*
     * The first character of the medical test must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code MedicalTest}.
     *
     * @param medicalTest A valid medical test.
     */
    public MedicalTest(String medicalTest) {
        requireNonNull(medicalTest);
        checkArgument(isValidMedicalTest(medicalTest), MESSAGE_CONSTRAINTS);
        value = medicalTest;
    }

    /**
     * Returns true if a given string is a valid medical test.
     */
    public static boolean isValidMedicalTest(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalTest // instanceof handles nulls
                && value.equals(((MedicalTest) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
