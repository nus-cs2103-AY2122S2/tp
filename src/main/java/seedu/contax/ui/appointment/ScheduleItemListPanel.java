package seedu.contax.ui.appointment;

import javafx.collections.ObservableList;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.ui.ListPanel;
import seedu.contax.ui.appointment.cardfactory.ScheduleItemCardFactory;

/**
 * Creates a panel containing a list of appointments.
 * This class is used as a helper to create the {@code AppointmentListRowFactory} required, the actual
 * ListPanel logic is implemented in {@link ListPanel}.
 */
public class ScheduleItemListPanel extends ListPanel<ScheduleItem> {
    /**
     * Creates a {@code AppointmentListPanel} with the given appointment {@code ObservableList}.
     */
    public ScheduleItemListPanel(ObservableList<ScheduleItem> appointmentList) {
        super(appointmentList, new ScheduleItemCardFactory(appointmentList));
    }
}
