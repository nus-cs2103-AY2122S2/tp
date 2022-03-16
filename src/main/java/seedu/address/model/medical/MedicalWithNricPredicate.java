package seedu.address.model.medical;

import java.util.function.Predicate;

import seedu.address.model.patient.Nric;

public class MedicalWithNricPredicate implements Predicate<Medical> {
    private final Nric nric;

    public MedicalWithNricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Medical medical) {
        return medical.getPatientNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalWithNricPredicate // instanceof handles nulls
                && nric.equals(((MedicalWithNricPredicate) other).nric)); // state check
    }
}
