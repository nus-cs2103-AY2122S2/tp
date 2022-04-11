package seedu.address.model.dish;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.dish.exception.DishNotFoundException;
import seedu.address.model.dish.exception.DuplicateDishException;

/**
 * A list of dishes that enforces uniqueness between its elements and does not allow nulls.
 * A dish is considered unique by comparing using {@code Dish#isSameDish(Dish)}. As such, adding and updating of
 * dishes uses Dish#isSameDish(Dish) for equality so as to ensure that the dish being added or updated is
 * unique in terms of identity in the UniqueDishList. However, the removal of a dish uses Dish#equals(Object) so
 * as to ensure that the dish with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Dish#isSameDish(Dish)
 */
public class UniqueDishList implements Iterable<Dish> {

    private final ObservableList<Dish> internalList = FXCollections.observableArrayList();
    private final ObservableList<Dish> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent dish as the given argument.
     */
    public boolean contains(Dish toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDish);
    }

    /**
     * Adds a dish to the list.
     * The dish must not already exist in the list.
     */
    public void add(Dish toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDishException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the dish {@code target} in the list with {@code editedDish}.
     * {@code target} must exist in the list.
     * The dish identity of {@code editedDish} must not be the same as another existing dish in the list.
     */
    public void setDish(Dish target, Dish editedDish) {
        requireAllNonNull(target, editedDish);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new DishNotFoundException();
        }

        if (!target.isSameDish(editedDish) && contains(editedDish)) {
            throw new DuplicateDishException();
        }

        internalList.set(index, editedDish);
    }

    /**
     * Removes the equivalent dish from the list.
     * The dish must exist in the list.
     */
    public void remove(Dish toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new DishNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code dishes}.
     * {@code dishes} must not contain duplicate dishes.
     */
    public void setDishes(List<Dish> dishes) {
        requireAllNonNull(dishes);
        if (!dishesAreUnique(dishes)) {
            throw new DuplicateDishException();
        }

        internalList.setAll(dishes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Dish> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Dish> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDishList // instanceof handles nulls
                && internalList.equals(((UniqueDishList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code dishes} contains only unique dishes.
     */
    private boolean dishesAreUnique(List<Dish> dishes) {
        for (int i = 0; i < dishes.size() - 1; i++) {
            for (int j = i + 1; j < dishes.size(); j++) {
                if (dishes.get(i).isSameDish(dishes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

