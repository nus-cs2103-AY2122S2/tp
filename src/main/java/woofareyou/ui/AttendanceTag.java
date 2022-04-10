package woofareyou.ui;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.util.AttendanceUtil.ATTENDANCE_DATE_GUI_FORMATTER;

import java.util.Optional;

import javafx.scene.control.Label;
import woofareyou.model.attendance.AttendanceEntry;

/**
 * A class that processes and produces attendance tags for the GUI.
 */
public class AttendanceTag extends Label {
    private static final String MISSING_ATTENDANCE_TAG_STYLE = "-fx-background-color: #c4c4c4";
    private static final String PRESENT_ATTENDANCE_TAG_STYLE = "-fx-background-color: #90be6d";
    private static final String ABSENT_ATTENDANCE_TAG_STYLE = "-fx-background-color: #ff595e";

    /**
     * Creates a new pet attendance tag.
     */
    public AttendanceTag(AttendanceEntry attendanceEntry) {
        super(getAttendanceTagDetails(attendanceEntry));
        super.setStyle(getTagStyle(attendanceEntry));
    }

    /**
     * Retrieves the style to be applied to the attendance tag.
     *
     * @param attendanceEntry the incoming attendance entry.
     * @return the style of the attendance tag to be applied.
     */
    private static String getTagStyle(AttendanceEntry attendanceEntry) {
        requireNonNull(attendanceEntry);
        Optional<Boolean> isPresent = attendanceEntry.getIsPresent();

        if (isPresent.isEmpty()) {
            return MISSING_ATTENDANCE_TAG_STYLE; // missing attendance entry, grey tag
        }

        return isPresent.get()
            ? PRESENT_ATTENDANCE_TAG_STYLE // pet is present, green tag
            : ABSENT_ATTENDANCE_TAG_STYLE; // pet is absent, red tag
    }

    /**
     * Retrieves the attendance date to be displayed in the tag.
     *
     * @param attendanceEntry the incoming attendance entry.
     * @return the attendance date, formatted into a string in GUI format.
     */
    private static String getAttendanceTagDetails(AttendanceEntry attendanceEntry) {
        requireNonNull(attendanceEntry);
        return attendanceEntry
            .getAttendanceDate()
            .format(ATTENDANCE_DATE_GUI_FORMATTER);
    }
}
