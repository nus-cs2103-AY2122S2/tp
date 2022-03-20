package seedu.address.ui;

import static seedu.address.commons.util.AttendanceUtil.ATTENDANCE_DATE_GUI_FORMATTER;

import javafx.scene.control.Label;
import seedu.address.model.attendance.AttendanceEntry;

/**
 * A class that processes and produces attendance tags for the GUI.
 */
public class AttendanceTag {

    /**
     * Private constructor to prevent creation.
     */
    private AttendanceTag() {
    }

    /**
     * Produces an attendance tag to be added to {@code PetCard}.
     * @param attendanceEntry the attendance to be converted to an attendance tag.
     * @return a green label if the pet is present on the given date, and
     * a red label if the pet is absent.
     */
    public static Label createAttendanceTag(AttendanceEntry attendanceEntry) {
        boolean isPresent = attendanceEntry.getIsPresent();
        String dateString = attendanceEntry.getAttendanceDate()
                .format(ATTENDANCE_DATE_GUI_FORMATTER);

        Label attendanceTag = new Label(dateString);

        if (isPresent) {
            attendanceTag.setStyle("-fx-background-color: #bffcc6");
        } else {
            attendanceTag.setStyle("-fx-background-color: #fbbebc");
        }

        return attendanceTag;
    }
}
