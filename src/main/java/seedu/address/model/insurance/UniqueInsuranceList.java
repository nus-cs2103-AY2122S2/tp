package seedu.address.model.insurance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.insurance.exceptions.DuplicateInsuranceException;
import seedu.address.model.insurance.exceptions.InsuranceNotFoundException;

/**
 * A list of insurances that enforces uniqueness between its elements and does not allow nulls.
 * An insurance is considered unique by comparing using {@code Insurance#isSameInsurance(Insurance)}. As such, adding
 * and updating of insurances uses Insurance#isSameInsurance(Insurance) for equality so as to ensure that the insurance
 * being added or updated is unique in terms of identity in the UniqueInsuranceList. However, the removal of an
 * insurance uses Insurance#equals(Object) so as to ensure that the insurance with exactly the same fields will be
 * removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Insurance#isSameInsurance(Insurance)
 */
public class UniqueInsuranceList implements Iterable<Insurance> {

    private final ObservableList<Insurance> internalList = FXCollections.observableArrayList();
    private final ObservableList<Insurance> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(this.internalList);

    /**
     * Returns true if the list contains an equivalent insurance as the given argument.
     */
    public boolean contains(Insurance toCheck) {
        requireNonNull(toCheck);
        return this.internalList.stream().anyMatch(toCheck::isSameInsurance);
    }

    /**
     * Adds an insurance to the list.
     * The insurance must not already exist in the list.
     */
    public void add(Insurance toAdd) {
        requireNonNull(toAdd);
        if (this.contains(toAdd)) {
            throw new DuplicateInsuranceException();
        }
        this.internalList.add(toAdd);
    }

    /**
     * Replaces the insurance {@code target} in the list with {@code editedInsurance}.
     * {@code target} must exist in the list.
     * The insurance identity of {@code editedInsurance} must not be the same as another existing insurance in the list.
     */
    public void setInsurance(Insurance target, Insurance editedInsurance) {
        requireAllNonNull(target, editedInsurance);

        int index = this.internalList.indexOf(target);
        if (index == -1) {
            throw new InsuranceNotFoundException();
        }

        if (!target.isSameInsurance(editedInsurance) && this.contains(editedInsurance)) {
            throw new DuplicateInsuranceException();
        }

        this.internalList.set(index, editedInsurance);
    }

    /**
     * Removes the equivalent insurance from the list.
     * The insurance must exist in the list.
     */
    public void remove(Insurance toRemove) {
        requireNonNull(toRemove);
        if (!this.internalList.remove(toRemove)) {
            throw new InsuranceNotFoundException();
        }
    }

    public void setInsurances(UniqueInsuranceList replacement) {
        requireNonNull(replacement);
        this.internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code insurances}.
     * {@code insurances} must not contain duplicate insurances.
     */
    public void setInsurances(List<Insurance> insurances) {
        requireAllNonNull(insurances);
        if (!this.insurancesAreUnique(insurances)) {
            throw new DuplicateInsuranceException();
        }

        this.internalList.setAll(insurances);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Insurance> asUnmodifiableObservableList() {
        return this.internalUnmodifiableList;
    }

    @Override
    public Iterator<Insurance> iterator() {
        return this.internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueInsuranceList // instanceof handles nulls
                && this.internalList.equals(((UniqueInsuranceList) other).internalList));
    }

    @Override
    public int hashCode() {
        return this.internalList.hashCode();
    }

    /**
     * Returns true if {@code insurances} contains only unique insurances.
     */
    private boolean insurancesAreUnique(List<Insurance> insurances) {
        for (int i = 0; i < insurances.size() - 1; i++) {
            for (int j = i + 1; j < insurances.size(); j++) {
                if (insurances.get(i).isSameInsurance(insurances.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
