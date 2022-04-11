package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the date and time instances in Expense Expert as LocalDate and LocalTime.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {

    public static final String VALIDATION_REGEX = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in a valid format " + DATE_FORMAT + ", and it should not be blank";
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public final LocalDate date;

    /**
     * Constructs a {@code Date}
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        this.date = LocalDate.parse(date, dtf);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        boolean regexTest = test.matches(VALIDATION_REGEX);
        if (regexTest) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setLenient(false);
                df.parse(test);
            } catch (ParseException e) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the month of a LocalDate instance.
     */
    public String getMonth() {
        return date.format(DateTimeFormatter.ofPattern("MMMM"));
    }

    /**
     * Returns whether date is outdated i.e. not from this month, this year.
     */
    public boolean isOutdated() {
        boolean monthUpdated = LocalDate.now().getMonthValue() == (date.getMonthValue());
        boolean yearUpdated = (LocalDate.now().getYear() == (date.getYear()));

        return !(monthUpdated && yearUpdated);
    }

    @Override
    public String toString() {
        return date.format(dtf);
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
