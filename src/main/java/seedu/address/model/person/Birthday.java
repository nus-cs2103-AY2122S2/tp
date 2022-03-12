package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValid(String)}
 */
public class Birthday extends Field {
    public static final Prefix PREFIX = new Prefix("b/", false);
    public static final String MESSAGE_CONSTRAINTS =
        "Birthday should be in YYYY-MM-DD format, must exist, and cannot be in the future.";

    private final LocalDate value;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        super(PREFIX);
        requireNonNull(birthday);
        birthday = birthday.trim(); // Wow, look at that, we don't need a whole fucking parser to do one line.
        checkArgument(Birthday.isValid(birthday), MESSAGE_CONSTRAINTS);
        value = LocalDate.parse(birthday);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValid(String str) {
        LocalDate date;
        try {
            date = LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return date.isBefore(LocalDate.now());
    }

    public int getAge() {
        return Period.between(value, LocalDate.now()).getYears();
    }

    @Override
    public String getValue() {
        return value.toString() + " (" + getAge() + "yrs)";
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Birthday // instanceof handles nulls
            && value.equals(((Birthday) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
