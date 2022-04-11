package seedu.ibook.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import seedu.ibook.commons.core.UniqueList;

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
        for (Item item: internalList()) {
            if (toAdd.isSame(item)) {
                return item;
            }
        }
        return null;
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
        FXCollections.sort(internalList());
    }

    /**
     * Sets the target to the updated item.
     */
    public void setItem(Item target, Item updatedItem) {
        requireAllNonNull(target, updatedItem);
        canUpdateItem(target, updatedItem);

        int index = getIndexOf(target);

        if (updatedItem.isEmpty()) {
            internalList().remove(index);
        } else {
            internalList().set(index, updatedItem);
            FXCollections.sort(internalList());
        }
    }

    public Quantity getTotalQuantity() {
        return stream().map(Item::getQuantity).reduce(new Quantity(0), Quantity::add);
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        setAll(replacement);
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * @param items The item list.
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        internalList().clear();
        for (Item item: items) {
            add(item);
        }
    }
}
