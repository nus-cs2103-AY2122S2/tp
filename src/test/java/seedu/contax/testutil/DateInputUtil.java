package seedu.contax.testutil;

import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_END;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME_START;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import seedu.contax.commons.util.DateUtil;

/**
 * Provides conversion service from java datetime objects to String inputs for parsing tests.
 */
public class DateInputUtil {
    /**
     * Returns {@code date} in a string that can be accepted as command inputs.
     *
     * @param date The date to convert into an input string.
     * @return A string representing {@code date} that can be accepted as a command input.
     */
    public static String formatDateToInputString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.DATE_PATTERN);
        return date.format(formatter);
    }

    /**
     * Returns {@code time} in a string that can be accepted as command inputs.
     *
     * @param time The time to convert into an input string.
     * @return A string representing {@code time} that can be accepted as a command input.
     */
    public static String formatTimeToInputString(LocalTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.TIME_PATTERN);
        return time.format(formatter);
    }

    /**
     * Returns the parameterized inputs for a datetime range specified by {@start} and {@end}.
     *
     * @param start The start of the datetime range.
     * @param end The end of the datetime range.
     * @return A parameterized string for input into a command expecting a date range.
     */
    public static String getDateRangeInput(LocalDateTime start, LocalDateTime end) {
        String startDate = " " + PREFIX_DATE_START + formatDateToInputString(start.toLocalDate());
        String endDate = " " + PREFIX_DATE_END + formatDateToInputString(end.toLocalDate());
        String startTime = " " + PREFIX_TIME_START + formatTimeToInputString(start.toLocalTime());
        String endTime = " " + PREFIX_TIME_END + formatTimeToInputString(end.toLocalTime());

        return startDate + endDate + startTime + endTime;
    }
}
