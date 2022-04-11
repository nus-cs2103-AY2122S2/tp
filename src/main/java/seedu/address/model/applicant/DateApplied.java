package seedu.address.model.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Represents an Applicant's date applied for the job in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateApplied(String)}
 */
public class DateApplied implements Comparable<DateApplied> {
    public static final String MESSAGE_CONSTRAINTS =
            "DateApplied should be of the format yyyy-mm-dd "
                    + "and adhere to the following constraints:\n"
                    + "1. The date should not be blank\n"
                    + "2. The day should be within a range of 1-31\n"
                    + "3. The month should be within a range of 1-12\n"
                    + "Please also ensure that the date exists E.g 2022-02-30 does not exist.";
    /*
     * The first character of the date must not be a whitespace,
     * and the date has to be valid and in the format of yyyy-mm-dd with leading zeros.
     */
    public static final String VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    public final LocalDate date;

    /**
     * Constructs a Date Applied.
     *
     * @param dateApplied A valid dateApplied.
     */
    public DateApplied(String dateApplied) {
        requireNonNull(dateApplied);
        checkArgument(isValidDateApplied(dateApplied), MESSAGE_CONSTRAINTS);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        date = LocalDate.parse(dateApplied, format);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateApplied(String test) {
        requireNonNull(test);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        if (test.equals("PENDING")) {
            return true;
        }
        try {
            LocalDate parsedDate = LocalDate.parse(test, format);
            return test.matches(VALIDATION_REGEX);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.applicant.DateApplied // instanceof handles nulls
                && date.equals(((seedu.address.model.applicant.DateApplied) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public int compareTo(DateApplied o) {
        return date.compareTo(o.date);
    }
}
