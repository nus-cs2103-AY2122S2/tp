package seedu.contax.ui;

import javafx.scene.Node;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;

/**
 * Creates elements for the {@code AppointmentListPanel}.
 */
public class AppointmentListCardFactory {

    /**
     * Creates UI {@code Node} objects for use in the {@code AppointmentListPanel}.
     *
     * @param scheduleItem The ScheduleItem for which a row in the list should be created.
     * @param displayedIndex The index of this the created row.
     * @return A row that can be added as a child to {@code AppointmentListPanel}.
     */
    public Node createCard(ScheduleItem scheduleItem, int displayedIndex) {
        if (scheduleItem instanceof Appointment) {
            return new AppointmentCard((Appointment) scheduleItem, displayedIndex).getRoot();
        } else if (scheduleItem instanceof AppointmentSlot) {
            return new AppointmentSlotCard((AppointmentSlot) scheduleItem).getRoot();
        } else {
            return null;
        }
    }
}
