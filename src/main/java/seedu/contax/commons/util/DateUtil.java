package seedu.contax.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import seedu.contax.commons.util.datetimeparser.DateParser;
import seedu.contax.commons.util.datetimeparser.TimeParser;

/**
 * Provides helper methods for working with date and time objects.
 * Serves as a facade for date and time parsing services.
 */
public class DateUtil {

    /**
     * Parses a date input string to a {@code LocalDate} object.
     * See {@link DateParser#parseDate(String)} for detailed information on accepted formats.
     *
     * @param input The input date string to parse.
     * @return An Optional container that contains a LocalDate object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalDate> parseDate(String input) {
        requireNonNull(input);
        return DateParser.parseDate(input);
    }

    /**
     * Parses a time input string to a {@code LocalTime} object.
     * See {@link TimeParser#parseTime(String)} for detailed information on accepted formats.
     *
     * @param input The input time string to parse.
     * @return An Optional container that contains a LocalTime object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalTime> parseTime(String input) {
        requireNonNull(input);
        return TimeParser.parseTime(input);
    }

    /**
     * Returns a {@code LocalDateTime} which contains the date of {@code date} and only the hour and minute
     * fields of {@code time}. The other time fields are zeroed.
     *
     * @param date The LocalDate to combine into a LocalDateTime.
     * @param time The LocalTime from which the hour and minute field should be extracted.
     * @return A LocalDateTime object with both the fields of {@code date} and {@code time}.
     */
    public static LocalDateTime combineDateTime(LocalDate date, LocalTime time) {
        requireNonNull(date);
        requireNonNull(time);

        LocalDateTime dateWithZeroedTime = date.atStartOfDay();
        return dateWithZeroedTime.withHour(time.getHour()).withMinute(time.getMinute());
    }

    /**
     * Returns a modified copy of {@code baselineDateTime} with the date fields changed to the date of
     * {@code date}.
     *
     * @param baselineDateTime The reference LocalDateTime object to modify.
     * @param date The date the new LocalDateTime object is updated to.
     * @return A LocalDateTime object with the date fields of {@code date}.
     */
    public static LocalDateTime updateDate(LocalDateTime baselineDateTime, LocalDate date) {
        requireNonNull(baselineDateTime);
        requireNonNull(date);

        LocalDateTime baselineWithDateRemoved =
                baselineDateTime.withDayOfMonth(1).withMonth(1).withYear(1970);
        return baselineWithDateRemoved.withYear(date.getYear())
                .withMonth(date.getMonthValue()).withDayOfMonth(date.getDayOfMonth());
    }

    /**
     * Returns a modified copy of {@code baselineDateTime} with the hour and minute fields changed to the
     * corresponding values of {@code time}.
     *
     * @param baselineDateTime The reference LocalDateTime object to modify.
     * @param time The time the new LocalDateTime object is updated to.
     * @return A LocalDateTime object with the hour and minute fields of {@code time}.
     */
    public static LocalDateTime updateTime(LocalDateTime baselineDateTime, LocalTime time) {
        requireNonNull(baselineDateTime);
        requireNonNull(time);

        return baselineDateTime.withHour(time.getHour()).withMinute(time.getMinute());
    }

    /**
     * Returns true if {@code dateTime1} is chronologically before or equal to {@code dateTime2}.
     *
     * @param dateTime1 The date time to compare.
     * @param dateTime2 The reference date time being compared against.
     * @return True if {@code dateTime1} is chronologically before or equal to {@code dateTime2},
     *         False otherwise.
     */
    public static boolean isBeforeOrEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        requireNonNull(dateTime1);
        requireNonNull(dateTime2);

        return dateTime1.equals(dateTime2) || dateTime1.isBefore(dateTime2);
    }

    /**
     * Returns true if {@code dateTime1} is chronologically after or equal to {@code dateTime2}.
     *
     * @param dateTime1 The date time to compare.
     * @param dateTime2 The reference date time being compared against.
     * @return True if {@code dateTime1} is chronologically after or equal to {@code dateTime2},
     *         False otherwise.
     */
    public static boolean isAfterOrEqual(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        requireNonNull(dateTime1);
        requireNonNull(dateTime2);

        return dateTime1.equals(dateTime2) || dateTime1.isAfter(dateTime2);
    }

}
