package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Incorrect time format. Time should be in the format hhmm";

    public static final String VALIDATION_REGEX = "([0-1][0-9]|2[0-3])[0-5][0-9]";

    public final String value;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid meeting time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = time;
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Compares if time is later than a given time.
     *
     * @param time A valid meeting time.
     * @return true if this time is later than given time.
     */
    public boolean isBefore(Time time) {
        return this.value.compareTo(time.value) < 0;
    }

    /**
     * Compares if time is earlier than a given time.
     *
     * @param time A valid meeting time.
     * @return true if this time is earlier than given time.
     */
    public boolean isAfter(Time time) {
        return this.value.compareTo(time.value) > 0;
    }


    /**
     * Checks if time falls between two given timings.
     *
     * @param start A valid meeting time.
     * @param end A valid meeting time.
     * @return true if time falls between the two given timings.
     */
    public boolean isWithin(Time start, Time end) {
        return isAfter(start) && isBefore(end);
    }

    @Override
    public int compareTo(Time time) {
        return this.value.compareTo(time.value);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
