package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Consultation's Fee in the address book.
 */
public class ConsultationFee {

    public static final String MESSAGE_CONSTRAINTS =
            "Fee cannot be blank, and has to have either format: \n"
                    + "XX.YY, where XX is dollars, YY is cents, OR; \n"
                    + "XX, where XX is dollars with no cents";

    private static final String VALIDATION_REGEX = "^\\-?[0-9]+(?:\\.[0-9]{2})?$";

    private String consultationFee;

    /**
     * Constructs a {@code ConsultationFee}.
     *
     * @param consultationFee A valid consultationFee.
     */
    public ConsultationFee(String consultationFee) {
        requireNonNull(consultationFee);
        checkArgument(isValidFee(consultationFee), MESSAGE_CONSTRAINTS);

        this.consultationFee = consultationFee;
    }

    /**
     * Returns true if a given string is a valid fee.
     */
    public static boolean isValidFee(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String value() {
        return consultationFee;
    }


    @Override
    public String toString() {
        return consultationFee;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationFee // instanceof handles nulls
                && consultationFee.equals(((ConsultationFee) other).consultationFee)); // state check
    }

    @Override
    public int hashCode() {
        return consultationFee.hashCode();
    }

}
