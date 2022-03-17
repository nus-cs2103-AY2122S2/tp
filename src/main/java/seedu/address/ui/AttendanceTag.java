package seedu.address.ui;

import javafx.scene.control.Label;

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
     *
     * @param attendance the attendance information to be included.
     * @return a green label if the pet was present on the given date, a red label if the pet was absent.
     */
    public static Label createAttendanceTag(String attendance) {
        String[] stringArray = attendance.split("\\s");
        String date = stringArray[0];
        Label presentLabel = new Label(date);

        return presentLabel;
    }
}
