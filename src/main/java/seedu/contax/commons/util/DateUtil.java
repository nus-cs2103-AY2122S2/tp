package seedu.contax.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Provides parsing services for dates and times used in the application.
 */
public class DateUtil {

    public static final String DATE_PATTERN = "dd-MM-yyyy";
    public static final String TIME_PATTERN = "HH:mm";

    /**
     * Parses a date input string to a {@code LocalDate} object.
     *
     * @param input The input date string to parse.
     * @return An Optional container that contains a LocalDate object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalDate> parseDate(String input) {
        requireNonNull(input);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        try {
            return Optional.ofNullable(LocalDate.parse(input, dateFormatter));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
    }

    /**
     * Parses a time input string to a {@code LocalTime} object.
     *
     * @param input The input time string to parse.
     * @return An Optional container that contains a LocalTime object if parsing was successful, or an empty
     *         Optional container if parsing was unsuccessful.
     */
    public static Optional<LocalTime> parseTime(String input) {
        requireNonNull(input);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        try {
            return Optional.of(LocalTime.parse(input, timeFormatter));
        } catch (DateTimeParseException ex) {
            return Optional.empty();
        }
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

}
