package seedu.address.model.person.lab;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateLabException;
import seedu.address.model.person.exceptions.LabNotFoundException;

public class LabList implements Iterable<Lab> {

    private final ObservableList<Lab> internalList = FXCollections.observableArrayList();
    private final ObservableList<Lab> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private final Comparator<Lab> sortByLabNumber = new Comparator<>() {
        @Override
        public int compare(Lab l1, Lab l2) {
            // Ignoring the case when l1.labNumber == l2.labNumber as there shouldn't be duplicate labs in the list.
            return l1.labNumber - l2.labNumber;
        }
    };

    /**
     * Returns true if the list contains an equivalent lab as the given argument.
     *
     * @param toCheck The Lab to be checked
     */
    public boolean contains(Lab toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLab);
    }

    /**
     * Adds a lab to the list.
     * The person must not already exist in the list.
     *
     * @param toAdd The Lab that you want to add.
     */
    public void add(Lab toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLabException();
        }
        internalList.add(toAdd);
        internalList.sort(sortByLabNumber);
    }

    /**
     * Replaces the lab {@code target} in the list with {@code editedLab}.
     * {@code target} must exist in the list.
     * The lab identity of {@code editedLab} must not be the same as another existing lab in the list.
     */
    public void setLab(Lab target, Lab editedLab) {
        requireAllNonNull(target, editedLab);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LabNotFoundException(target.labNumber);
        }

        if (!target.equals(editedLab) && contains(editedLab)) {
            throw new DuplicateLabException();
        }

        internalList.set(index, editedLab);
    }

    /**
     * Removes the equivalent lab from the list.
     * The lab must exist in the list.
     *
     * @param toRemove Lab you want to remove from the list.
     */
    public void remove(Lab toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LabNotFoundException(toRemove.labNumber);
        }
    }

    public void setLabs(LabList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code labs}.
     * {@code labs} must not contain duplicate labs.
     */
    public void setLabs(List<Lab> labs) {
        requireAllNonNull(labs);
        if (!labsAreUnique(labs)) {
            throw new DuplicateLabException();
        }

        internalList.setAll(labs);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Lab> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Lab> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabList // instanceof handles nulls
                && internalList.equals(((LabList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code labs} contains only unique labs.
     */
    private boolean labsAreUnique(List<Lab> labs) {
        for (int i = 0; i < labs.size() - 1; i++) {
            for (int j = i + 1; j < labs.size(); j++) {
                if (labs.get(i).isSameLab(labs.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
