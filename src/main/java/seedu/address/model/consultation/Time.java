package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Consultation's time in the address book.
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should not be blank, and should be in format HH-mm (24h time)";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "^([2][0-3]|[10][0-9])-([0-5][0-9])$";

    private LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);

        this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH-mm"));
    }

    public LocalTime value() {
        return time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
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

