package seedu.address.model.classgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.classgroup.exceptions.ClassGroupNotFoundException;
import seedu.address.model.classgroup.exceptions.DuplicateClassGroupException;

/**
 * A list of class groups that enforces uniqueness between its elements and does not allow nulls.
 * A class group is considered unique by comparing using {@code ClassGroup#equals(ClassGroup)}.
 * As such, adding and updating of class groups uses ClassGroup#equals(ClassGroup) for equality so as to
 * ensure that the class group being added or updated is unique in terms of identity in the UniqueClassGroupList.
 * However, the removal of a class group uses ClassGroup#equals(Object) so as to ensure that
 * the class group with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see ClassGroup#equals(Object)
 */
public class UniqueClassGroupList implements Iterable<ClassGroup> {

    private final ObservableList<ClassGroup> internalList = FXCollections.observableArrayList();
    private final ObservableList<ClassGroup> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent class group as the given argument.
     */
    public boolean contains(ClassGroup toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a class group to the list.
     * The class group must not already exist in the list.
     */
    public void add(ClassGroup toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassGroupException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the class group {@code target} in the list with {@code editedClassGroup}.
     * {@code target} must exist in the list.
     * The class group identity of {@code editedClassGroup} must not be the same as
     * another existing class group in the list.
     */
    public void setClassGroup(ClassGroup target, ClassGroup editedClassGroup) {
        requireAllNonNull(target, editedClassGroup);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClassGroupNotFoundException();
        }

        if (!target.equals(editedClassGroup) && contains(editedClassGroup)) {
            throw new DuplicateClassGroupException();
        }

        internalList.set(index, editedClassGroup);
    }

    /**
     * Removes the equivalent class group from the list.
     * The class group must exist in the list.
     */
    public void remove(ClassGroup toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClassGroupNotFoundException();
        }
    }

    public void setClassGroups(UniqueClassGroupList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code classGroups}.
     * {@code classGroups} must not contain duplicate class groups.
     */
    public void setClassGroups(List<ClassGroup> classGroups) {
        requireAllNonNull(classGroups);
        if (!lessonsAreUnique(classGroups)) {
            throw new DuplicateClassGroupException();
        }

        internalList.setAll(classGroups);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ClassGroup> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ClassGroup> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueClassGroupList
                && internalList.equals(((UniqueClassGroupList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code classGroups} contains only unique class groups.
     */
    private boolean lessonsAreUnique(List<ClassGroup> classGroups) {
        for (int i = 0; i < classGroups.size() - 1; i++) {
            for (int j = i + 1; j < classGroups.size(); j++) {
                if (classGroups.get(i).equals(classGroups.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
