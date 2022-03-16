package seedu.address.model.consultation;

import seedu.address.model.patient.Nric;

import java.util.function.Predicate;

public class NricDateTimePredicate implements Predicate<Consultation> {
    private final Nric nric;
    private final Date date;
    private final Time time;

    public NricDateTimePredicate(Nric nric, Date date, Time time) {
        this.nric = nric;
        this.date = date;
        this.time = time;
    }

    @Override
    public boolean test(Consultation consultation) {
        return consultation.getNric().equals(nric) &&
                consultation.getDate().equals(date) &&
                consultation.getTime().equals(time);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricDateTimePredicate // instanceof handles nulls
                && nric.equals(((NricDateTimePredicate) other).nric)
                && date.equals(((NricDateTimePredicate) other).date)
                && time.equals(((NricDateTimePredicate) other).time)); // state check
    }
}