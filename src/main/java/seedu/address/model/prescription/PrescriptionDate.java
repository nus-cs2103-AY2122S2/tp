package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PrescriptionDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Invalid date! Please ensure that the format is YYYY-MM-DD and the value is valid";

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

    /**
     * To validate the date
     * @param test String to be validated
     * @return true if the date is valid, false otherwise.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
