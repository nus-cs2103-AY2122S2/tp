package seedu.address.model.consultation;

import java.util.function.Predicate;

import seedu.address.model.patient.Nric;

public class ConsultationWithPredicates implements Predicate<Consultation> {
    private final Nric nric;

    public ConsultationWithPredicates(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Consultation consultation) {
        return consultation.getNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsultationWithPredicates // instanceof handles nulls
                && nric.equals(((ConsultationWithPredicates) other).nric)); // state check
    }
}
