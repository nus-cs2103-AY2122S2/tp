package seedu.address.model.interview.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Represents a predicate of whether the (@code Interview) is within a specific time period.
 */
public abstract class WithinTimePeriodPredicate implements Predicate<Interview> {
    private final LocalDateTime endDateTime;

    public WithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        this.endDateTime = currentDateTime;
    }

    @Override
    public boolean test(Interview interview) {
        return interview.getInterviewEndDateTime().isBefore(endDateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((WithinTimePeriodPredicate) other).endDateTime)); // state check
    }
}
