package seedu.contax.ui;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;

/**
 * Creates elements for the {@code AppointmentListPanel}.
 */
public class AppointmentListCardFactory {

    private final ObservableList<ScheduleItem> scheduleItemList;
    private final ArrayList<Integer> indexMap;

    public AppointmentListCardFactory(ObservableList<ScheduleItem> scheduleItemList) {
        this.scheduleItemList = scheduleItemList;
        this.indexMap = new ArrayList<>(scheduleItemList.size());
        populateIndexMap();
        attachChangeListener();
    }

    /**
     * Creates UI {@code Node} objects for use in the {@code AppointmentListPanel}.
     *
     * @param scheduleItem The ScheduleItem for which a row in the list should be created.
     * @param index The 0-based index of this row in the ListView.
     * @return A row that can be added as a child to {@code AppointmentListPanel}.
     */
    public Node createCard(ScheduleItem scheduleItem, int index) {
        if (scheduleItem instanceof Appointment) {
            return new AppointmentCard((Appointment) scheduleItem, indexMap.get(index) + 1).getRoot();
        } else if (scheduleItem instanceof AppointmentSlot) {
            return new AppointmentSlotCard((AppointmentSlot) scheduleItem).getRoot();
        } else {
            return null;
        }
    }

    /**
     * Constructs a list with the mapping of the {@code scheduleItemList} to the displayed index.
     */
    private void populateIndexMap() {
        indexMap.clear();
        int slotCount = 0;
        for (int i = 0; i < scheduleItemList.size(); i++) {
            indexMap.add(i - slotCount);
            if (scheduleItemList.get(i) instanceof AppointmentSlot) {
                slotCount++;
            }
        }
    }

    /**
     * Attaches a listener to the backing ScheduleItemList to watch for changes for maintenance of the
     * indexMap.
     */
    private void attachChangeListener() {
        scheduleItemList.addListener((InvalidationListener) (ignored) -> {
            populateIndexMap();
        });
    }
}
