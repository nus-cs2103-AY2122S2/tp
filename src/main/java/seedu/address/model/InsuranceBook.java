package seedu.address.model;


import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.UniqueInsuranceList;


/**
 * Wraps all data at the insurance-book level
 * Duplicates are not allowed (by .isSameInsurance comparison)
 */
public class InsuranceBook implements ReadOnlyInsuranceBook {

    private final UniqueInsuranceList insurances;

    {
        this.insurances = new UniqueInsuranceList();
    }

    public InsuranceBook() {
    }

    /**
     * Creates an InsuranceBook using the Insurances in the {@code toBeCopied}
     */
    public InsuranceBook(ReadOnlyInsuranceBook toBeCopied) {
        this();
        this.resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the insurance list with {@code insurances}.
     * {@code insurances} must not contain duplicate insurances.
     */
    public void setInsurances(List<Insurance> insurances) {
        this.insurances.setInsurances(insurances);
    }

    /**
     * Resets the existing data of this {@code InsuranceBook} with {@code newData}.
     */
    public void resetData(ReadOnlyInsuranceBook newData) {
        requireNonNull(newData);

        this.setInsurances(newData.getInsuranceList());
    }

    //// insurance-level operations

    /**
     * Returns true if n  insurance with the same identity as {@code insurance} exists in the insurance book.
     */
    public boolean hasInsurance(Insurance insurance) {
        requireNonNull(insurance);
        return this.insurances.contains(insurance);
    }

    /**
     * Adds a insurance to the insurance book.
     * The insurance must not already exist in the insurance book.
     */
    public void addInsurance(Insurance p) {
        this.insurances.add(p);
    }

    /**
     * Replaces the given insurance {@code target} in the list with {@code editedInsurance}.
     * {@code target} must exist in the insurance book.
     * The insurance identity of {@code editedInsurance} must not be the same as another
     * existing insurance in the insurance book.
     */
    public void setInsurance(Insurance target, Insurance editedInsurance) {
        requireNonNull(editedInsurance);

        this.insurances.setInsurance(target, editedInsurance);
    }

    /**
     * Removes {@code key} from this {@code InsuranceBook}.
     * {@code key} must exist in the insurance book.
     */
    public void removeInsurance(Insurance key) {
        this.insurances.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (Iterator<Insurance> it = insurances.iterator(); it.hasNext(); ) {
            Insurance insurance = it.next();
            builder.append(insurance.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public ObservableList<Insurance> getInsuranceList() {
        return this.insurances.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsuranceBook // instanceof handles nulls
                && this.insurances.equals(((InsuranceBook) other).insurances));
    }

    @Override
    public int hashCode() {
        return this.insurances.hashCode();
    }
}
