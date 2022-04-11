package seedu.ibook.model.item;

import java.util.function.Predicate;

import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;

/**
 * A class that holds {@code FilteredList<Item>}
 */
public class FilteredItemList {

    private final FilteredList<Item> internalList;

    private ListChangeListener<Item> listener;

    /**
     * Constructs a new {@code FilteredItemList} with items.
     * @param items A list of items.
     */
    public FilteredItemList(UniqueItemList items) {
        internalList = new FilteredList<>(items.asObservableList());
    }

    /**
     * Sets predicate to filter to list.
     * @param predicate The predicate.
     */
    public void setPredicate(Predicate<Item> predicate) {
        internalList.setPredicate(predicate);
    }

    /**
     * Adds a listener to the list.
     * @param listener The listener.
     */
    public void addListener(ListChangeListener<Item> listener) {
        if (this.listener != null) {
            internalList.removeListener(this.listener);
        }
        internalList.addListener(listener);
        this.listener = listener;
    }

    /**
     * Checks if the list is empty.
     * @return True if the list is empty,
     */
    public boolean isEmpty() {
        return internalList.isEmpty();
    }

    /**
     * Gets the size of the list.
     * @return The size of the list.
     */
    public int size() {
        return internalList.size();
    }

    /**
     * Gets the {@code Item} on i-th position.
     * @param i The index.
     * @return The {@code Item} at i-th position.
     */
    public Item get(int i) {
        return internalList.get(i);
    }

    /**
     * Gets the internal list.
     * @return The internal list.
     */
    public FilteredList<Item> getInternalList() {
        return internalList;
    }
}
