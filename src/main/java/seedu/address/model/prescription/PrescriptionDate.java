package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class PrescriptionDate {

    public static final String MESSAGE_CONSTRAINTS = "Prescription Date should not be blank, "
        + "and should be in the format: YYYY-MM-DD";

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

    public String toDefaultString() {
        return date.toString();
    }

    @Override
    public String toString() {
        String formattedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        return formattedDate;
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
