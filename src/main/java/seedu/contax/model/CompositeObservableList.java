package seedu.contax.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Aggregates the contents of 2 sorted {@code ObservableLists} into a single sorted {@code ObservableList}.
 * The backing lists must be sorted, or the aggregated list will not contain the correct sorting order.
 */
public class CompositeObservableList<T extends Comparable<? super T>> {

    private final ObservableList<? extends T> backingList1;
    private final ObservableList<? extends T> backingList2;
    private final ObservableList<T> mergedList;
    private final ObservableList<T> unmodifiableMergedList;

    /**
     * Constructs a {@code CompositeObservableList}.
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
        this.mergedList = FXCollections.observableArrayList();
        this.unmodifiableMergedList = FXCollections.unmodifiableObservableList(mergedList);

        refreshCombinedList();
        attachListeners();
    }

    /**
     * Returns the aggregated ObservableList.
     */
    public ObservableList<T> getUnmodifiableResultList() {
        return this.unmodifiableMergedList;
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
        int list1Index = 0;
        int list2Index = 0;
        ArrayList<T> newList = new ArrayList<>(backingList1.size() + backingList2.size());

        try {
            while (list1Index < backingList1.size() && list2Index < backingList2.size()) {
                if (backingList1.get(list1Index).compareTo(backingList2.get(list2Index)) < 0) {
                    newList.add(backingList1.get(list1Index));
                    list1Index++;
                } else {
                    newList.add(backingList2.get(list2Index));
                    list2Index++;
                }
            }
            for (; list1Index < backingList1.size(); list1Index++) {
                newList.add(backingList1.get(list1Index));
            }
            for (; list2Index < backingList2.size(); list2Index++) {
                newList.add(backingList2.get(list2Index));
            }

            this.mergedList.setAll(newList);
        } catch (IndexOutOfBoundsException ignored) {
            // List changed during merging, abort the merge. A new call to this function is on the way
            // since the list has changed.
        }
    }
}
