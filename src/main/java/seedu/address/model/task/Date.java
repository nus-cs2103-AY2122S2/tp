package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


/**
 * Represents a Task's date in the schedule book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be in the following format DD-MM-YYYY "
            + "and adhere to the following constraints: \n"
            + "1. YYYY should be a 4-digit numeric \n"
            + "2. MM should be a 2-digit numeric ranging from 01 to 12 \n"
            + "3. DD should be a 2-digit numeric, "
            + "however the range of this numeric depends on the month and year "
            + "e.g. 2020-02-29 is valid as it is a leap year while 2021-02-29 is invalid";

    public final String value;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns true if the given date is valid.
     */
    public static boolean isValidDate(String date) {
        try {
            DateTimeFormatter customFormat = DateTimeFormatter
                    .ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
            LocalDate.parse(date, customFormat);
        } catch (DateTimeParseException ex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.equals(((Date) other).value)); // state check

    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

