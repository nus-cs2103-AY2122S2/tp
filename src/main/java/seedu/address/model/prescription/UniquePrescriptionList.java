package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.PrescriptionNotFoundException;

public class UniquePrescriptionList implements Iterable<Prescription> {

    private final ObservableList<Prescription> internalList = FXCollections.observableArrayList();
    private final ObservableList<Prescription> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains same prescription as the given argument.
     */
    public boolean contains(Prescription toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a prescription to the list.
     * The prescription must not already exist in the list.
     */
    public void add(Prescription toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePrescriptionException();
        }
        internalList.add(toAdd);
    }

    public void setPrescription(Prescription target, Prescription editedPrescription) {
        requireAllNonNull(target, editedPrescription);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PrescriptionNotFoundException();
        }

        if (!target.equals(editedPrescription) && contains(editedPrescription)) {
            throw new DuplicatePrescriptionException();
        }

        internalList.set(index, editedPrescription);
    }

    /**
     * Removes the equivalent prescription from the list.
     * The prescription must exist in the list.
     */
    public void remove(Prescription toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PrescriptionNotFoundException();
        }
    }

    public void setPrescriptions(UniquePrescriptionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        requireAllNonNull(prescriptions);
        if (!prescriptionAreUnique(prescriptions)) {
            throw new DuplicatePrescriptionException();
        }

        internalList.setAll(prescriptions);
    }

    public ObservableList<Prescription> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Prescription> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePrescriptionList // instanceof handles nulls
                && internalList.equals(((UniquePrescriptionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code prescriptions} contains only unique prescriptions.
     */
    private boolean prescriptionAreUnique(List<Prescription> prescriptions) {
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
