package seedu.contax.ui.appointment.cardfactory;

import java.util.function.Function;

import javafx.scene.Node;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.ui.RecyclableCard;

/**
 * Represents a row in the {@code ScheduleItemListPanel}, which can be either an {@code AppointmentCard}
 * or {@code AppointmentSlotCard}. This is a helper UI class to support row recycling.
 * This class is package private to enforce that only {@code ScheduleItemCardFactory} can create instances
 * of it.
 */
class ScheduleItemCard implements RecyclableCard<ScheduleItem> {
    private final AppointmentCard appointmentCard;
    private final AppointmentSlotCard appointmentSlotCard;
    private final Function<Integer, Integer> displayedIndexMapper;

    /**
     * Constructs a new {@code ScheduleItemCard}.
     *
     * @param displayedIndexMapper A function to map the 1-based list index of this item to the actual index
     *                             that should be displayed.
     */
    ScheduleItemCard(Function<Integer, Integer> displayedIndexMapper) {
        appointmentCard = new AppointmentCard();
        appointmentSlotCard = new AppointmentSlotCard();
        this.displayedIndexMapper = displayedIndexMapper;
    }

    /**
     * Updates the data displayed in this row with the supplied {@code ScheduleItem} model.
     *
     * @param scheduleItem The new model to display in this row.
     * @param displayedIndex The new displayed index of this row.
     * @return A rendered {@code Node} object that displays the information of the supplied model.
     */
    public Node updateModel(ScheduleItem scheduleItem, int displayedIndex) {
        if (scheduleItem instanceof Appointment) {
            int actualIndex = displayedIndexMapper.apply(displayedIndex);
            this.appointmentCard.updateModel((Appointment) scheduleItem, actualIndex);
            return this.appointmentCard.getRoot();
        } else if (scheduleItem instanceof AppointmentSlot) {
            this.appointmentSlotCard.updateModel((AppointmentSlot) scheduleItem);
            return this.appointmentSlotCard.getRoot();
        } else {
            return null;
        }
    }
}
