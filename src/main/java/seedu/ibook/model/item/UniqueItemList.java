package seedu.ibook.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import seedu.ibook.commons.core.UniqueList;
import seedu.ibook.commons.core.exceptions.DuplicateElementException;
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

        int index = asObservableList().indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        if (!target.isSame(updatedItem) && contains(updatedItem)) {
            throw new DuplicateElementException();
        }

        set(index, updatedItem);
    }

    /**
     * Set the quantity of the specified item.
     */
    public void setItemCount(Item target, Quantity quantity) {
        requireAllNonNull(target, quantity);

        int index = internalList().indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        Item newItem = target.setQuantity(quantity);
        internalList().set(index, newItem);
    }

    /**
     * Increase the quantity of the specified item.
     */
    public void incrementItemCount(Item target, Quantity quantity) {
        requireAllNonNull(target, quantity);

        int index = internalList().indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        Item newItem = target.increment(quantity);
        internalList().set(index, newItem);
    }

    /**
     * Increase the quantity of the specified item by 1.
     */
    public void incrementItemCount(Item target) {
        incrementItemCount(target, new Quantity(1));
    }

    /**
     * Decrease the quantity of the specified item.
     */
    public void decrementItemCount(Item target, Quantity quantity) {
        requireAllNonNull(target, quantity);

        int index = internalList().indexOf(target);
        if (index == -1) {
            throw new ElementNotFoundException();
        }

        Item newItem = target.decrement(quantity);
        if (newItem.isEmpty()) {
            internalList().remove(index);
        } else {
            internalList().set(index, newItem);
        }
    }

    /**
     * Decrease the quantity of the specified item by 1.
     */
    public void decrementItemCount(Item target) {
        decrementItemCount(target, new Quantity(1));
    }

    public Quantity getTotalQuantity() {
        return stream().map(Item::getQuantity).reduce(new Quantity(0), Quantity::add);
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        internalList().setAll(replacement.internalList());
    }

    /**
     * Replaces the contents of this list with {@code items}.
     * @param items
     */
    public void setItems(List<Item> items) {
        requireAllNonNull(items);
        internalList().clear();
        for (Item item: items) {
            add(item);
        }
    }
}
