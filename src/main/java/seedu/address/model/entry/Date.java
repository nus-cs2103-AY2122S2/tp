package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents an Event's Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Date should follow the format YYYY-MM-DD and be a valid date.";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }

    /**
     * Compares this date with another date.
     * Returns a negative integer, zero, or a positive integer as this date is less than, equal to, or
     * greater than the specified date.
     * <p>
     * 
     * @param otherDate the date to be compared.
    */
    @Override
    public int compareTo(Date otherDate) {
        return this.date.compareTo(otherDate.date);
    }


    @Override
    public String toString() {
        return String.format("%d %s %d, %s",
                date.getDayOfMonth(),
                date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                date.getYear(),
                date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
