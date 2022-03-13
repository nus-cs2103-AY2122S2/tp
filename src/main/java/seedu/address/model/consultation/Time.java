package seedu.address.model.consultation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Date should not be blank, and should be in format HH-mm (24h time)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^([2][0-4]|[10][0-9])-([543210][0-9])$";

    public LocalTime time;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);

        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH-mm"));
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}

