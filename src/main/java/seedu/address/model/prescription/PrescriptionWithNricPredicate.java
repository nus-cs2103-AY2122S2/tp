package seedu.address.model.prescription;

import seedu.address.model.patient.Nric;

import java.util.function.Predicate;

public class PrescriptionWithNricPredicate implements Predicate<Prescription> {

    private final Nric nric;

    public PrescriptionWithNricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Prescription prescription) {
        return prescription.getPrescriptionTarget().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrescriptionWithNricPredicate // instanceof handles nulls
                && nric.equals(((PrescriptionWithNricPredicate) other).nric)); // state check
    }
}
