package seedu.contax.testutil;

import java.time.LocalDate;
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
}
