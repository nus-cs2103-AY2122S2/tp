package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

public class PrescriptionDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Prescription Date should not be blank, and should be in the format YYY-MM-DD";

    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

    public final LocalDate date;

    /**
     * Constructs an {@code PrescriptionDate}.
     *
     * @param value A valid date.
     */
    public PrescriptionDate(String value) {
        requireNonNull(value);
        checkArgument(isValidDate(value), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(value);
    }

    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrescriptionDate // instanceof handles nulls
                && date.equals(((PrescriptionDate) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }
}
