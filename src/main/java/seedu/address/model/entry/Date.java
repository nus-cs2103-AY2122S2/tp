package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

import seedu.address.commons.util.StringUtil;

/**
 * Represents an Event's Date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Comparable<Date> {

    public static final String MESSAGE_CONSTRAINTS = "Date should follow the format YYYY-MM-DD and be a valid date.";
    public static final String TODAY_CONSTANT = "today";

    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);

        if (date.equalsIgnoreCase(TODAY_CONSTANT)) {
            this.date = LocalDate.now();
        } else if (isRelativeToToday(date)) {
            String[] dateSplit = date.split(" ");
            this.date = LocalDate.now().plusDays(Long.parseLong(dateSplit[1]));
        } else {
            this.date = LocalDate.parse(date);
        }
    }

    /**
     * Returns true if the string passed follows the form "today [long]".
     */
    public static boolean isRelativeToToday(String test) {
        String[] testSplit = test.split(" ");

        if (testSplit.length != 2) {
            return false;
        }

        if (!testSplit[0].equalsIgnoreCase(TODAY_CONSTANT)) {
            return false;
        }

        return StringUtil.isLong(testSplit[1]);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            if (test.equalsIgnoreCase(TODAY_CONSTANT) || isRelativeToToday(test)) {
                return true;
            }
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


    /**
     * Accesses and returns the date attribute
     * @return the date attribute as LocalDate
     */
    public LocalDate getPure() {
        return this.date;
    }

}
