package seedu.ibook.commons.core;

import static java.util.Objects.requireNonNull;
import static seedu.ibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.ibook.commons.core.exceptions.DuplicateElementException;
import seedu.ibook.commons.core.exceptions.ElementNotFoundException;

/**
 * A list of elements that enforces uniqueness between its elements and does not allow nulls.
 * An element is considered unique by comparing using {@code T#isSame(T)}. As such, adding and
 * updating of element uses T#isSame(T) for equality as to ensure that the element being added or
 * updated is unique in terms of identity in the UniqueList. However, the removal of an element uses
 * T#equals(Object) as to ensure that the element with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @param <T> Type of element in the list
 * @see Distinguishable#isSame
 */
public class UniqueList<T extends Distinguishable<T>> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the index of {@code item} in the list.
     * @throws ElementNotFoundException if {@code item} is not in the list.
     */
    public int getIndexOf(T item) throws ElementNotFoundException {
        int index = internalList.indexOf(item);

        if (index < 0) {
            throw new ElementNotFoundException();
        }

        return index;
    }

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSame);
    }

    /**
     * Adds an element to the list.
     * The element must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateElementException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent element from the list.
     * The element must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Throws {@code DuplicateElementException} if the update will not break the property of {@code UniqueList}
     * of containing unique items only.
     */
    public void canUpdateItem(T original, T updated) throws DuplicateElementException {
        requireAllNonNull(original, updated);
        if (!original.isSame(updated) && contains(updated)) {
            throw new DuplicateElementException();
        }
    }

    /**
     * Gets the size of the list.
     */
    public int size() {
        return internalList.size();
    }

    public ObservableList<T> asObservableList() {
        return internalList;
    }

    /**
     * Returns the backing queue as an unmodifiable list {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    public Stream<T> stream() {
        return internalList.stream();
    }

    /**
     * Returns the backing queue. This method returns the same as {@code asObservableList()}
     * but is intended for subclasses to get {@code internalList} with a more intuitive name.
     */
    public ObservableList<T> internalList() {
        return internalList;
    }

    public T set(int index, T element) {
        return internalList.set(index, element);
    }

    public boolean setAll(Collection<? extends T> elements) {
        requireNonNull(elements);
        return internalList.setAll(elements);
    }

    public boolean setAll(UniqueList<? extends T> other) {
        requireNonNull(other);
        return internalList.setAll(other.internalList);
    }

    @Override
    public boolean equals(Object other) {
        try {
            return other == this // short circuit if same object
                    || (other instanceof UniqueList // instanceof handles nulls
                    && internalList.equals(((UniqueList<?>) other).internalList));
        } catch (ClassCastException e) {
            return false; // Differing generic type implies that they are not equal
        }
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
