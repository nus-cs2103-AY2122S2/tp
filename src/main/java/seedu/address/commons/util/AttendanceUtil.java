package seedu.address.commons.util;

import java.time.format.DateTimeFormatter;

/**
 * A container for Attendance-related methods and functionality.
 */
public class AttendanceUtil {
    public static final String ATTENDANCE_DATE_FORMAT = "dd/MM";
    public static final DateTimeFormatter ATTENDANCE_DATE_FORMATTER =
            DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT);


}
