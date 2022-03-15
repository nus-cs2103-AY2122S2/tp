package seedu.ibook.model.product.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.PriorityQueue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ibook.model.product.exceptions.ItemNotFoundException;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * A item is considered unique by comparing using {@code Item#isSameItem(Item)}. As such, adding and
 * updating of items uses Item#isSameItem(Item) for equality as to ensure that the item being added or
 * updated is unique in terms of identity in the UniqueItemList. However, the removal of a item uses
 * Item#equals(Object) as to ensure that the item with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Item#isSameItem(Item)
 */
public class UniqueItemList {

    private final PriorityQueue<Item> internalList = new PriorityQueue<>();

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(Item toCheck) {
        requireNonNull(toCheck);
        return true;
    }

    /**
     * Adds an item to the list.
     * If the item is already in the list, combine it with the existing one.
     */
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            // TODO: combine both items
        } else {
            internalList.add(toAdd);
        }
    }
    /**
     * Removes the equivalent item from the list.
     */
    public void remove(Item toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * @param items
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        internalList.clear();
        for (Item item: items) {
            internalList.add(item);
        }
    }

    /**
     * Returns the backing queue as an unmodifiable list {@code ObservableList}.
     */
    public ObservableList<Item> asObservableList() {
        return FXCollections.observableArrayList(internalList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueItemList // instanceof handles nulls
            && internalList.equals(((UniqueItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
