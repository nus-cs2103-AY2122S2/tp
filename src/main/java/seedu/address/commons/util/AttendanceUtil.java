package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.MissingAttendanceEntry;
import seedu.address.model.pet.AttendanceHashMap;
import seedu.address.storage.JsonAdaptedAttendance;

/**
 * A container for Attendance-related methods and functionality.
 */
public class AttendanceUtil {
    public static final String ATTENDANCE_DATE_GUI_FORMAT = "dd/MM";
    public static final String ATTENDANCE_DATE_FORMAT = "dd-MM-yyyy";
    public static final DateTimeFormatter ATTENDANCE_DATE_GUI_FORMATTER =
        DateTimeFormatter.ofPattern(ATTENDANCE_DATE_GUI_FORMAT);
    public static final DateTimeFormatter ATTENDANCE_DATE_FORMATTER =
        DateTimeFormatter.ofPattern(ATTENDANCE_DATE_FORMAT);

    /**
     * Checks if a given string is in the valid format to be parsed into a {@code Boolean}.
     *
     * @param isPresent the given isPresent string.
     * @return true if string is valid, false otherwise.
     */
    public static boolean isValidIsPresentString(String isPresent) {
        return isPresent.equals("true") || isPresent.equals("false");
    }

    /**
     * Converts a date string found in {@link JsonAdaptedAttendance} into a
     * {@code LocalDate} object.
     *
     * @param jsonDate the time string to be converted.
     * @return a LocalDate object.
     * @throws DateTimeParseException if the time string is invalid.
     */
    public static LocalDate convertToModelDate(String jsonDate) throws DateTimeParseException {
        return LocalDate.parse(jsonDate);
    }

    /**
     * Converts a time string found in {@link JsonAdaptedAttendance} into a
     * {@code LocalTime} object.
     *
     * @param jsonTime the date string to be converted.
     * @return a LocalTime object.
     * @throws DateTimeParseException if the date string is invalid.
     */
    public static LocalTime convertToModelTime(String jsonTime) throws DateTimeParseException {
        // No time specified.
        if (jsonTime.isEmpty()) {
            return null;
        }

        return LocalTime.parse(jsonTime);
    }

    /**
     * Creates and returns a list of attendance entries for the past week,
     * starting from six days ago (inclusive) to the current date (inclusive).
     * If no attendance has been marked on a particular date, a missing attendance entry is added.
     *
     * @param attendanceHashMap the attendance hash map of the pet.
     * @return a list of attendance entries for the past week.
     */
    public static List<AttendanceEntry> getPastWeekAttendance(AttendanceHashMap attendanceHashMap) {
        requireNonNull(attendanceHashMap);
        ArrayList<AttendanceEntry> weeklyAttendanceList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now(); // the current date
        LocalDate startDate = currentDate.minusDays(6); // the date a week before
        LocalDate endDate = currentDate.plusDays(1); // the date tomorrow

        for (LocalDate d = startDate; d.isBefore(endDate); d = d.plusDays(1)) {
            weeklyAttendanceList.add(
                attendanceHashMap
                    .getAttendance(d)
                    .orElse(new MissingAttendanceEntry(d))
            );
        }

        return weeklyAttendanceList;
    }
}
