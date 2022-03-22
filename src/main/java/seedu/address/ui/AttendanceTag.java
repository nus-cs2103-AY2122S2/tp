package seedu.address.ui;

import static seedu.address.commons.util.AttendanceUtil.ATTENDANCE_DATE_GUI_FORMATTER;

import java.util.Optional;

import javafx.scene.control.Label;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.MissingAttendanceEntry;

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
     * @return a green label if the pet is present on the given date,
     * a red label if the pet is absent,
     * and a grey label if no attendance was marked on that day.
     */
    public static Label createAttendanceTag(AttendanceEntry attendanceEntry) {
        Optional<Boolean> isPresent = attendanceEntry.getIsPresent();
        String dateString = attendanceEntry.getAttendanceDate()
                .format(ATTENDANCE_DATE_GUI_FORMATTER);

        Label attendanceTag = new Label(dateString);

        if (isPresent.isEmpty()) {
            attendanceTag.setStyle("-fx-background-color: #c4c4c4");
            return attendanceTag;
        }

        if (isPresent.get()) {
            attendanceTag.setStyle("-fx-background-color: #95ff7a");
        } else {
            attendanceTag.setStyle("-fx-background-color: #ff7e7e");
        }

        return attendanceTag;
    }
}
