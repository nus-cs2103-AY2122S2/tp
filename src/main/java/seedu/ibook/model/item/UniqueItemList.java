package seedu.ibook.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import seedu.ibook.commons.core.UniqueList;
import seedu.ibook.commons.core.exceptions.ElementNotFoundException;

/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * A item is considered unique by comparing using {@code Item#isSameItem(Item)}. As such, adding and
 * updating of items uses Item#isSameItem(Item) for equality as to ensure that the item being added or
 * updated is unique in terms of identity in the UniqueItemList. However, the removal of a item uses
 * Item#equals(Object) as to ensure that the item with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Item#isSame(Item)
 */
public class UniqueItemList extends UniqueList<Item> {

    /**
     * Gets the item in the list that is similar to the item as the given argument.
     */
    public Item getExisting(Item toAdd) {
        for (Item item: asObservableList()) {
            if (toAdd.isSame(item)) {
                return item;
            }
        }
        return null;
    }

    public Integer getTotalQuantity() {
        return stream().mapToInt(o -> o.getQuantity().getQuantity()).sum();
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        setAll(replacement.asObservableList());
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * @param items
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        asObservableList().clear();
        for (Item item: items) {
            add(item);
        }
    }

    /**
     * Adds an item to the list.
     * If the item is already in the list, combine it with the existing one.
     */
    @Override
    public void add(Item toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            Item existingItem = getExisting(toAdd);
            remove(existingItem);
            Item newItem = existingItem.add(toAdd);
            super.add(newItem);
        } else {
            super.add(toAdd);
        }
        FXCollections.sort(asObservableList());
    }

    /**
     * Removes the equivalent item from the list.
     */
    public void remove(Item toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
            throw new ElementNotFoundException();
        }
        Item existingItem = getExisting(toRemove);
        super.remove(existingItem);
        Item newItem = existingItem.subtract(toRemove);
        if (!newItem.getQuantity().isEmpty()) {
            super.add(newItem);
        }
        FXCollections.sort(asObservableList());
    }
}
