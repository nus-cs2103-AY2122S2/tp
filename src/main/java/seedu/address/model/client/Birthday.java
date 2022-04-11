package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday should be a valid date in dd-MM-yyyy format.";
    public static final String MESSAGE_FUTURE_DATE = "Birthday should not be in the future.";

    /*
     * Birthday should be in dd-MM-yyyy format.
     */
    public static final String VALIDATION_REGEX = "(((0[1-9]|[12]\\d|3[01])-(0[13578]|1[02])-((19|[2-9]\\d)\\d{2}))|((0"
            + "[1-9]|[12]\\d|30)-(0[13456789]|1[012])-((19|[2-9]\\d)\\d{2}))|((0[1-9]|1\\d|2[0-8])-02-((19|[2-9]\\d)\\d"
            + "{2}))|(29-02-((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))";

    public final String value;

    public Birthday() {
        value = "";
    }

    /**
     * Constructs a {@code Birthday}.
     *
     * @param date A valid date.
     */
    public Birthday(String date) {
        requireNonNull(date);
        checkArgument(isValidBirthday(date), MESSAGE_CONSTRAINTS);
        checkArgument(isPastBirthday(date), MESSAGE_FUTURE_DATE);
        value = date;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a past date.
     */
    public static boolean isPastBirthday(String test) {
        LocalDate testDate = parse(test);
        return !testDate.isAfter(LocalDate.now());
    }

    /**
     * Converts a valid date from {@code String} to {@code LocalDate}.
     *
     * @param date A valid date in {@code String}.
     */
    public static LocalDate parse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public int getAge() {
        return Period.between(parse(value), LocalDate.now()).getYears();
    }

    public int getMonth() {
        return parse(value).getMonthValue();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
