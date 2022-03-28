package seedu.contax.ui.appointment.cardfactory;

import java.util.ArrayList;
import java.util.function.Function;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.ui.CardFactory;
import seedu.contax.ui.RecyclableCard;

/**
 * Creates rows for the {@code ScheduleItemListPanel} and transparently performs the translation from row's
 * ListView index to its displayed index.
 */
public class ScheduleItemCardFactory implements CardFactory<ScheduleItem> {

    private final ObservableList<ScheduleItem> scheduleItemList;
    private final ArrayList<Integer> indexMap;

    /**
     * Constructs an ScheduleItemCardFactory.
     *
     * @param scheduleItemList The list of models that the factory is creating cards for.
     */
    public ScheduleItemCardFactory(ObservableList<ScheduleItem> scheduleItemList) {
        this.scheduleItemList = scheduleItemList;
        this.indexMap = new ArrayList<>(scheduleItemList.size());
        populateIndexMap();
        attachChangeListener();
    }

    @Override
    public RecyclableCard<ScheduleItem> createCard() {
        Function<Integer, Integer> indexMappingFunction = (displayedOneBasedIndex) -> {
            int zeroBasedIndex = displayedOneBasedIndex - 1;
            return indexMap.get(zeroBasedIndex) + 1;
        };
        return new ScheduleItemCard(indexMappingFunction);
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
     * Attaches a listener to the backing ScheduleItem list to watch for changes for maintenance of the
     * indexMap.
     */
    private void attachChangeListener() {
        scheduleItemList.addListener((InvalidationListener) (ignored) -> {
            populateIndexMap();
        });
    }
}
