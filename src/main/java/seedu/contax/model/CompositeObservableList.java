package seedu.contax.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Aggregates the contents of 2 {@code ObservableLists} into a single {@code ObservableList}.
 */
public class CompositeObservableList<T extends Comparable<? super T>> {

    private final ObservableList<? extends T> backingList1;
    private final ObservableList<? extends T> backingList2;
    private final ObservableList<T> scheduleItemList;
    private final ObservableList<T> unmodifiableScheduleItemList;

    /**
     * Constructs a CompositeObservableList.
     *
     * @param backingList1 The first backing ObservableList.
     * @param backingList2 The second backing ObservableList.
     */
    public CompositeObservableList(ObservableList<? extends T> backingList1,
                                   ObservableList<? extends T> backingList2) {
        requireNonNull(backingList1);
        requireNonNull(backingList2);
        this.backingList1 = backingList1;
        this.backingList2 = backingList2;
        this.scheduleItemList = FXCollections.observableArrayList();
        this.unmodifiableScheduleItemList = FXCollections.unmodifiableObservableList(scheduleItemList);

        refreshCombinedList();
        attachListeners();
    }

    /**
     * Returns the aggregated ObservableList.
     */
    public ObservableList<T> getUnmodifiableList() {
        return this.unmodifiableScheduleItemList;
    }

    /**
     * Attaches listeners to watch for changes on the backing lists.
     */
    private void attachListeners() {
        this.backingList1.addListener((InvalidationListener) (change) -> {
            this.refreshCombinedList();
        });
        this.backingList2.addListener((InvalidationListener) (change) -> {
            this.refreshCombinedList();
        });
    }

    /**
     * Merges the 2 backing lists into a single resultant list using the Merge operation from MergeSort.
     */
    private void refreshCombinedList() {
        // Merge sorted lists
        int apptListIdx = 0;
        int slotListIdx = 0;
        ArrayList<T> mergedList =
                new ArrayList<>(backingList1.size() + backingList2.size());

        while (apptListIdx < backingList1.size() && slotListIdx < backingList2.size()) {
            if (backingList1.get(apptListIdx).compareTo(backingList2.get(slotListIdx)) < 0) {
                mergedList.add(backingList1.get(apptListIdx));
                apptListIdx++;
            } else {
                mergedList.add(backingList2.get(slotListIdx));
                slotListIdx++;
            }
        }
        while (apptListIdx < backingList1.size()) {
            mergedList.add(backingList1.get(apptListIdx));
            apptListIdx++;
        }
        while (slotListIdx < backingList2.size()) {
            mergedList.add(backingList2.get(slotListIdx));
            slotListIdx++;
        }

        scheduleItemList.setAll(mergedList);
    }
}
