package seedu.address.model.patient;

import java.util.function.Predicate;
import seedu.address.model.patient.Nric;

public class NricPredicate implements Predicate<Patient> {
    private final Nric nric;

    public NricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(Patient person) {
        return person.getNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricPredicate // instanceof handles nulls
                && nric.equals(((NricPredicate) other).nric)); // state check
    }
}