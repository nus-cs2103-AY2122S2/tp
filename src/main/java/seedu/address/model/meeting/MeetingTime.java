package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

/**
 * Represents the meeting time for a meeting.
 */
public class MeetingTime implements Comparable<MeetingTime> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting time must be in the following format: dd-MM-yyyy HH:mm\n Example: 25-05-2022 23:59";

    public static final String MESSAGE_FUTURE_CONSTRAINT =
            "Meeting time must be in the future, not in the past";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    public final LocalDateTime dateTime;

    /**
     * Creates a new meeting time.
     * @param dateTime Meeting date and time.
     */
    public MeetingTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidMeetingTime(dateTime), MESSAGE_CONSTRAINTS);
        LocalDateTime parsedTime = formatTime(dateTime);
        checkArgument(isFutureMeetingTime(parsedTime), MESSAGE_FUTURE_CONSTRAINT);
        this.dateTime = formatTime(dateTime);
    }

    /**
     * Returns true if a given string is a valid meeting time.
     */
    public static boolean isValidMeetingTime(String test) {
        try {
            LocalDateTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given meeting is in the future.
     */
    public static boolean isFutureMeetingTime(LocalDateTime test) {
        return test.isAfter(LocalDateTime.now());
    }

    public static LocalDateTime formatTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter);
    }

    /**
     * Formats the time in a user-friendly 12-hour format.
     * @param dateTime
     * @return formatted time
     */
    public static String prettyTime(LocalDateTime dateTime) {
        String time = "11:59 PM"; // To bypass uninitialised string error
        try {
            String timeToFormat = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            SimpleDateFormat twentyFourHrFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat twelveHrFormat = new SimpleDateFormat("hh:mm a");
            Date dateToFormat = twentyFourHrFormat.parse(timeToFormat);
            time = twelveHrFormat.format(dateToFormat);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return time;
    }

    /**
     * Formats the date in a user-friendly manner.
     * @param dateTime
     * @return formatted date
     */
    public static String prettyDate(LocalDateTime dateTime) {
        String date = dateTime.format(DateTimeFormatter.ofPattern("MMM dd uuuu"));
        return date;
    }

    @Override
    public String toString() {
        return dateTime.format(formatter);
    }

    public String toPrettyString() {
        return prettyDate(dateTime) + ", " + prettyTime(dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingTime // instanceof handles nulls
                & dateTime.isEqual(((MeetingTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public int compareTo(MeetingTime other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
