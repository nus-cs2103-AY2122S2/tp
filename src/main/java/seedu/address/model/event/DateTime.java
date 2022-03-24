package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static final String DATETIME_MESSAGE_CONSTRAINTS = "Date and Time has to be valid!";
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date has to be in the format of yyyy-MM-DD!";
    public static final String TIME_MESSAGE_CONSTRAINTS = "Time has to be in the format of HH:mm!";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

    public static final String DATE_VALIDATION_REGEX = "^(\\d{4})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
    public static final String TIME_VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final LocalDateTime value;

    /**
     * Constructs a {@Code DateTime}
     * @param year the event year
     * @param month the event month
     * @param day the event day
     * @param hour the event hour
     * @param min the event min
     */
    public DateTime(int year, int month, int day, int hour, int min) {
        checkArgument(isValidDateTime(year, month, day, hour, min), DATETIME_MESSAGE_CONSTRAINTS);
        LocalDateTime temp = LocalDateTime.of(year, month, day, hour, min);
        value = temp;
    }

    /**
     * Returns true if a given date and time is valid.
     */
    public static boolean isValidDateTime(int year, int month, int day, int hour, int min) {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(year, month, day, hour, min).isAfter(now);
    }

    public static boolean isValidDate(String test) {
        return test.matches(DATE_VALIDATION_REGEX);
    }

    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    public String getDateTime() {
        return value.format(DISPLAY_FORMATTER);
    }

    @Override
    public String toString() {
        return value.format(DATE_TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DateTime
                && value.isEqual(((DateTime) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
