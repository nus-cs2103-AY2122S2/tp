package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event's Time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {

    public static final String MESSAGE_CONSTRAINTS = "Time should follow the 24-hour format HH:MM and be a valid time.";

    public final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time);;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return time.toString();
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
