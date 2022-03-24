package seedu.contax.ui.appointment.factory;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;

/**
 * Creates rows for the {@code AppointmentListPanel} and transparently performs the translation from row's
 * ListView index to its displayed index.
 */
public class AppointmentListRowFactory {

    private final ObservableList<ScheduleItem> scheduleItemList;
    private final ArrayList<Integer> indexMap;

    /**
     * Constructs an AppointmentListRowFactory.
     *
     * @param scheduleItemList The list of models that the factory is creating cards for.
     */
    public AppointmentListRowFactory(ObservableList<ScheduleItem> scheduleItemList) {
        this.scheduleItemList = scheduleItemList;
        this.indexMap = new ArrayList<>(scheduleItemList.size());
        populateIndexMap();
        attachChangeListener();
    }

    /**
     * Creates a new {@code AppointmentListRow} that represents a row in {@code AppointmentListPanel}.
     */
    public AppointmentListRow createRow() {
        return new AppointmentListRow();
    }

    /**
     * Updates the supplied {@code row} with the data in the {@code ScheduleItem} model and the list index.
     *
     * @param row The row to update.
     * @param scheduleItem The ScheduleItem that the supplied row should be updated to.
     * @param index The 0-based index of this row in the ListView.
     * @return A row that can be added as a child to {@code AppointmentListPanel}.
     */
    public Node updateRow(AppointmentListRow row, ScheduleItem scheduleItem, int index) {
        return row.updateModel(scheduleItem, indexMap.get(index) + 1);
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
