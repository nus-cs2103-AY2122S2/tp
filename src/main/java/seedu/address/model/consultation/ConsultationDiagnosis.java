package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's Notes in the address book.
 */
public class ConsultationDiagnosis {

    public static final String MESSAGE_CONSTRAINTS = "Diagnosis can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String consultationDiagnosis;

    /**
     * Constructs a {@code ConsultationDiagnosis}.
     *
     * @param consultationDiagnosis A valid consultationDiagnosis.
     */
    public ConsultationDiagnosis(String consultationDiagnosis) {
        requireNonNull(consultationDiagnosis);
        checkArgument(isValidDiagnosis(consultationDiagnosis), MESSAGE_CONSTRAINTS);
        this.consultationDiagnosis = consultationDiagnosis;
    }

    /**
     * Returns true if a given string is a valid diagnosis.
     */
    public static boolean isValidDiagnosis(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String value() {
        return consultationDiagnosis;
    }

    @Override
    public String toString() {
        return consultationDiagnosis;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationDiagnosis // instanceof handles nulls
                && consultationDiagnosis.equals(((ConsultationDiagnosis) other).consultationDiagnosis)); // state check
    }

    @Override
    public int hashCode() {
        return consultationDiagnosis.hashCode();
    }

}
