package seedu.address.model.prescription;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PrescriptionDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Prescription Date can only be in the format: YYYY-MM-DD";

    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

    public final LocalDate date;

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
