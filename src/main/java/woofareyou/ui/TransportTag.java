package woofareyou.ui;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import javafx.scene.control.Label;
import woofareyou.model.attendance.AttendanceEntry;

/**
 * A class that processes and produces transport arrangement tags for the GUI.
 */
public class TransportTag extends Label {
    private static final String TODAY_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #f9c74f";
    private static final String TOMORROW_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #f9844a";
    private static final String NO_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #c4c4c4";
    private static final String TRANSPORT_TAG_ARRANGEMENT = "%1$s | Pick-up: %2$s, Drop-off: %3$s";
    private static final String TRANSPORT_TAG_NO_ARRANGEMENT = "%1$s | No Arrangement";

    /**
     * Creates a new pet transport tag.
     */
    public TransportTag(AttendanceEntry attendanceEntry) {
        super(getTagDetails(attendanceEntry));
        super.setStyle(getTagStyle(attendanceEntry));
    }

    /**
     * Retrieves the details of the transport arrangements, if any, to be displayed in the tag.
     *
     * @param attendanceEntry the pet's attendance entry.
     * @return pick-up and drop-off times of the transport arrangements if available, "No Arrangement" otherwise.
     */
    private static String getTagDetails(AttendanceEntry attendanceEntry) {
        requireNonNull(attendanceEntry);
        LocalDate attendanceDate = attendanceEntry.getAttendanceDate();

        if (!attendanceEntry.hasTransportArrangement()) {
            return String.format(TRANSPORT_TAG_NO_ARRANGEMENT, dateToDay(attendanceDate));
        }

        // has transport arrangements
        return String.format(TRANSPORT_TAG_ARRANGEMENT, dateToDay(attendanceDate),
                attendanceEntry.getPickUpTime().get(),
                attendanceEntry.getDropOffTime().get());
    }

    /**
     * Retrieves the style to be applied to the transport tag.
     *
     * @param attendanceEntry the pet's attendance entry
     * @return the style of the transport tag to be applied.
     */
    private static String getTagStyle(AttendanceEntry attendanceEntry) {
        requireNonNull(attendanceEntry);
        if (!attendanceEntry.hasTransportArrangement()) {
            return NO_TRANSPORT_ARRANGEMENT_TAG_STYLE;
        }

        // has transport arrangements
        LocalDate attendanceDate = attendanceEntry.getAttendanceDate();
        if (attendanceDate.isEqual(LocalDate.now())) {
            return (TODAY_TRANSPORT_ARRANGEMENT_TAG_STYLE);
        } else if (attendanceDate.isEqual(LocalDate.now().plusDays(1))) {
            return (TOMORROW_TRANSPORT_ARRANGEMENT_TAG_STYLE);
        }

        assert false; // should not reach this point
        return ""; // should not reach this point
    }

    /**
     * Converts a given date into a string relative to today.
     *
     * @param date the given date.
     * @return a {@code String} representing the date, relative to today.
     */
    private static String dateToDay(LocalDate date) {
        requireNonNull(date);
        if (date.isEqual(LocalDate.now())) {
            return "Today";
        } else if (date.isEqual(LocalDate.now().plusDays(1))) {
            return "Tomorrow";
        }

        assert false; // should not reach this point
        return ""; // should not reach this point
    }
}
