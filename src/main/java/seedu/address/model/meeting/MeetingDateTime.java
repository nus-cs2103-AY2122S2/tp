package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.model.meeting.exceptions.InvalidDateTimeException;

/**
 * Represents a Meeting's date and time in the meeting list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class MeetingDateTime implements Comparable<MeetingDateTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be formatted as dd-MM-yyyy HHmm; e.g. 30-04-2022 1400";
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("E, d MMM uuuu, h:mma")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DISPLAY_TIME_FORMAT = DateTimeFormatter.ofPattern("h:mma")
            .withResolverStyle(ResolverStyle.STRICT);

    public final LocalDateTime datetime;

    /**
     * Constructs a {@code MeetingDateTime}.
     *
     * @param datetime A {@code String} representing the meeting date and time, formatted according
     * to {@link #INPUT_FORMAT}.
     */
    public MeetingDateTime(String datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = parseDateTime(datetime);
    }

    /**
     * Constructs a {@code MeetingDateTime}.
     *
     * @param datetime A {@code LocalDateTime} representing the meeting date and time.
     */
    public MeetingDateTime(LocalDateTime datetime) {
        requireNonNull(datetime);
        checkArgument(isValidDateTime(datetime), MESSAGE_CONSTRAINTS);
        this.datetime = datetime;
    }

    /**
     * Returns true if a given {@code String} is a valid date and time.
     *
     * @param test The {@code String} to test.
     * @return True, if the {@code String} represents a valid date and time.
     */
    public static boolean isValidDateTime(String test) {
        try {
            parseDateTime(test);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if a given {@code LocalDateTime} is a valid date and time.
     *
     * @param test The {@code LocalDateTime} to test.
     * @return True, if the {@code LocalDateTime} is a valid date and time in the Gregorian calendar.
     */
    public static boolean isValidDateTime(LocalDateTime test) {
        return isValidDateTime(test.format(INPUT_FORMAT));
    }

    /**
     * Converts the given string to its date and time representation.
     *
     * @param dateTime The string to convert, formatted according to {@link #INPUT_FORMAT}.
     * @return A datetime representation of {@code dateTime}.
     * @throws DateTimeParseException If {@code dateTime} is not formatted according to {@link #INPUT_FORMAT}.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, INPUT_FORMAT);
        } catch (Exception ex) {
            throw new InvalidDateTimeException(String.format("%s is not a valid date time", dateTime));
        }
    }

    /**
     * Returns a string representation of the meeting date and time.
     *
     * @return A string representation of the meeting date and time, formatted according to {@link #DISPLAY_FORMAT}.
     */
    @Override
    public String toString() {
        return datetime.format(DISPLAY_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingDateTime // instanceof handles nulls
                && datetime.equals(((MeetingDateTime) other).datetime)); // state check
    }

    @Override
    public int hashCode() {
        return datetime.hashCode();
    }

    @Override
    public int compareTo(MeetingDateTime other) {
        return datetime.compareTo(other.datetime);
    }
}
