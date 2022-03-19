package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A container for Attendance-related methods and functionality.
 */
public class AttendanceUtil {
    public static final String ATTENDANCE_DATE_GUI_FORMAT = "dd/MM";
    public static final DateTimeFormatter ATTENDANCE_DATE_GUI_FORMATTER =
            DateTimeFormatter.ofPattern(ATTENDANCE_DATE_GUI_FORMAT);

    public static boolean isValidIsPresentString(String isPresent) {
        if (isPresent.equals("true") || isPresent.equals("false")) {
            return true;
        }

        return false;
    }

    public static LocalDate convertToModelDate(String jsonDate) throws DateTimeParseException {
        return LocalDate.parse(jsonDate);
    }

    public static LocalTime convertToModelTime(String jsonTime) throws DateTimeParseException {
        return LocalTime.parse(jsonTime);
    }
}
