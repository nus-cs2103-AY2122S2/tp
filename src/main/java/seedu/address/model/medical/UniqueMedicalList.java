package seedu.address.model.medical;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.medical.exceptions.DuplicateMedicalException;
import seedu.address.model.medical.exceptions.MedicalNotFoundException;

public class UniqueMedicalList implements Iterable<Medical> {

    private final ObservableList<Medical> internalList = FXCollections.observableArrayList();
    private final ObservableList<Medical> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains same medical as the given argument.
     */
    public boolean contains(Medical toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds medical information to the list.
     * The medical information must not already exist in the list.
     */
    public void add(Medical toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMedicalException();
        }
        internalList.add(toAdd);
    }

    public void setMedical(Medical target, Medical editedMedical) {
        requireAllNonNull(target, editedMedical);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MedicalNotFoundException();
        }

        if (!target.equals(editedMedical) && contains(editedMedical)) {
            throw new DuplicateMedicalException();
        }

        internalList.set(index, editedMedical);
    }

    /**
     * Removes the equivalent medical from the list.
     * The medical must exist in the list.
     */
    public void remove(Medical toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MedicalNotFoundException();
        }
    }

    public ObservableList<Medical> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Medical> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueMedicalList // instanceof handles nulls
                && internalList.equals(((UniqueMedicalList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code medicals} contains only unique medical information records.
     */
    private boolean medicalAreUnique(List<Medical> medicals) {
        for (int i = 0; i < medicals.size() - 1; i++) {
            for (int j = i + 1; j < medicals.size(); j++) {
                if (medicals.get(i).equals(medicals.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Replaces the contents of this list with {@code medicals}.
     * {@code medicals} must not contain duplicate medicals.
     */
    public void setMedicals(List<Medical> medicals) {
        requireAllNonNull(medicals);
        if (!medicalAreUnique(medicals)) {
            throw new DuplicateMedicalException();
        }

        internalList.setAll(medicals);
    }
}
