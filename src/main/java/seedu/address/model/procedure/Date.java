package seedu.address.model.procedure;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Procedure's date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS =
            "Please ensure that you have typed an existing date in the correct format. \n"
        + "Dates should be in the format DD/MM/YYYY hh:mm, and it should not be blank \n"
            + "Years can take any value in the range 0000 to 9999";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{2}[/]\\d{2}[/]\\d{4} \\d{2}:\\d{2}";

    public static final DateTimeFormatter FORMAT_WITH_DATE = DateTimeFormatter
            .ofPattern("dd/MM/uuuu HH:mm");

    public static final String START_OF_DAY = " 00:00";

    public static final String END_OF_DAY = " 23:59";

    public final LocalDateTime validDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        validDate = LocalDateTime.parse(date, FORMAT_WITH_DATE);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                LocalDateTime.parse(test, FORMAT_WITH_DATE
                        .withResolverStyle(ResolverStyle.STRICT));
                return true;
            } catch (DateTimeParseException err) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return validDate.format(FORMAT_WITH_DATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && validDate.equals(((Date) other).validDate)); // state check
    }

    @Override
    public int hashCode() {
        return validDate.hashCode();
    }

    @Override
    public int compareTo(Date otherDate) {
        return validDate.compareTo(otherDate.validDate);
    }
}
