package seedu.address.model.prescription;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.exceptions.DuplicatePersonException;
import seedu.address.model.patient.exceptions.PersonNotFoundException;
import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.PrescriptionNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniquePrescriptionList implements Iterable<Prescription>{

    private final ObservableList<Prescription> internalList = FXCollections.observableArrayList();
    private final ObservableList<Prescription> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Prescription toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

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
            throw new PersonNotFoundException();
        }

        if (!target.equals(editedPrescription) && contains(editedPrescription)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedPrescription);
    }

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

    public void setPrescription(List<Prescription> prescriptions) {
        requireAllNonNull(prescriptions);
        if (!prescriptionAreUnique(prescriptions)) {
            throw new DuplicatePersonException();
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
     * Returns true if {@code persons} contains only unique persons.
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
