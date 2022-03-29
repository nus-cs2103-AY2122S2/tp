package seedu.address.ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.Label;
import seedu.address.model.attendance.AttendanceEntry;

/**
 * A class that processes and produces transport arrangement tags for the GUI.
 */
public class TransportTag {
    private static final String TODAY_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #f9c74f";
    private static final String TOMORROW_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #f9844a";
    private static final String NO_TRANSPORT_ARRANGEMENT_TAG_STYLE = "-fx-background-color: #c4c4c4";
    private static final String TRANSPORT_TAG_ARRANGEMENT = "%1$s | Pick-up: %2$s, Drop-off: %3$s";
    private static final String TRANSPORT_TAG_NO_ARRANGEMENT = "%1$s | no arrangement";

    /**
     * Private constructor to prevent creation.
     */
    private TransportTag() {
    };

    /**
     * Produces a transport arrangement tag to be added to {@code PetCard}.
     *
     * @param attendanceEntry the attendance to be converted to a transport tag.
     * @return a yellow label for today's arrangements,
     * an orange label for tomorrow's arrangements,
     * and a grey label if no transport arrangements were made that day.
     */
    public static Label createTransportTag(AttendanceEntry attendanceEntry) {
        requireNonNull(attendanceEntry);
        LocalDate tagDate = attendanceEntry.getAttendanceDate();

        if (!attendanceEntry.hasTransportArrangement()) {
            return createTagWithNoArrangement(tagDate);
        }

        return createTagWithArrangement(tagDate,
            attendanceEntry.getPickUpTime().get(),
            attendanceEntry.getDropOffTime().get());
    }

    /**
     * Converts a given date into a string relative to today.
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
        return ""; // should not reach this point
    }

    /**
     * Creates a transport tag in the context with no transport arrangements.
     * @param date the date of the transport arrangement.
     * @return a tag without any transport arrangement details.
     */
    private static Label createTagWithNoArrangement(LocalDate date) {
        requireNonNull(date);
        Label transportTag = new Label(
            String.format(TRANSPORT_TAG_NO_ARRANGEMENT, dateToDay(date))
        );

        transportTag.setStyle(NO_TRANSPORT_ARRANGEMENT_TAG_STYLE);

        return transportTag;
    }

    /**
     * Creates a transport tag in the context with transport arrangements.
     * @param date the date of the transport arrangement.
     * @param pickUpTime the pick-up time of the transport arrangement.
     * @param dropOffTime the drop-off time of the transport arrangement.
     * @return a tag with the details of the transport arrangement.
     */
    private static Label createTagWithArrangement(LocalDate date, LocalTime pickUpTime, LocalTime dropOffTime) {
        requireAllNonNull(date, pickUpTime, dropOffTime);
        String style = date.isEqual(LocalDate.now())
            ? TODAY_TRANSPORT_ARRANGEMENT_TAG_STYLE
            : TOMORROW_TRANSPORT_ARRANGEMENT_TAG_STYLE;

        Label transportTag = new Label(
            String.format(TRANSPORT_TAG_ARRANGEMENT, dateToDay(date),
                pickUpTime, dropOffTime)
        );

        transportTag.setStyle(style);

        return transportTag;
    }
}
