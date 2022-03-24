package seedu.contax.ui.appointment.factory;

import javafx.scene.Node;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.ui.appointment.AppointmentCard;
import seedu.contax.ui.appointment.AppointmentSlotCard;

/**
 * Represents a row in the {@code AppointmentListPanel}, which can be either an {@code AppointmentCard}
 * or {@code AppointmentSlotCard}. This is a helper UI class to support row recycling.
 * This class is package private to enforce that only {@code AppointmentListRowFactory} can create instances
 * of it.
 */
public class AppointmentListRow {
    private final AppointmentCard apptCard;
    private final AppointmentSlotCard apptRowCard;

    /**
     * Constructs a new {@code AppointmentListRow}.
     */
    AppointmentListRow() {
        apptCard = new AppointmentCard();
        apptRowCard = new AppointmentSlotCard();
    }

    /**
     * Updates the data displayed in this row with the supplied {@code ScheduleItem} model.
     *
     * @param scheduleItem The new model to display in this row.
     * @param displayedIndex The new displayed index of this row.
     * @return A rendered {@code Node} object that displays the information of the supplied model.
     */
    Node updateModel(ScheduleItem scheduleItem, int displayedIndex) {
        if (scheduleItem instanceof Appointment) {
            this.apptCard.updateModel((Appointment) scheduleItem, displayedIndex);
            return this.apptCard.getRoot();
        } else if (scheduleItem instanceof AppointmentSlot) {
            this.apptRowCard.updateModel((AppointmentSlot) scheduleItem);
            return this.apptRowCard.getRoot();
        } else {
            return null;
        }
    }
}
