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

    public void setMedical(Medical target, Medical editedPrescription) {
        requireAllNonNull(target, editedPrescription);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MedicalNotFoundException();
        }

        if (!target.equals(editedPrescription) && contains(editedPrescription)) {
            throw new DuplicateMedicalException();
        }

        internalList.set(index, editedPrescription);
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

    public void setMedicals(UniqueMedicalList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setMedicals(List<Medical> prescriptions) {
        requireAllNonNull(prescriptions);
        if (!prescriptionAreUnique(prescriptions)) {
            throw new DuplicateMedicalException();
        }

        internalList.setAll(prescriptions);
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
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean prescriptionAreUnique(List<Medical> prescriptions) {
        for (int i = 0; i < prescriptions.size() - 1; i++) {
            for (int j = i + 1; j < prescriptions.size(); j++) {
                if (prescriptions.get(i).equals(prescriptions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
