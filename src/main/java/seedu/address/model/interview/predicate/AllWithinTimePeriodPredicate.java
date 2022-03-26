package seedu.address.model.interview.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Returns true for all interview dates and times.
 */
public class AllWithinTimePeriodPredicate extends WithinTimePeriodPredicate implements Predicate<Interview> {
    private final LocalDateTime endDateTime;

    /**
     * Creates a new {@link AllWithinTimePeriodPredicate} object with the
     * {@link AllWithinTimePeriodPredicate#endDateTime} initialised.
     * @param currentDateTime contains the current date and time at the point this constructor is called.
     */
    public AllWithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        super(currentDateTime);
        this.endDateTime = currentDateTime;
    }

    /**
     * Returns true for all interview dates and times.
     * @param interview object to retrieve the date and time.
     * @return true.
     */
    @Override
    public boolean test(Interview interview) {
        return true;
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link AllWithinTimePeriodPredicate#endDateTime}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link AllWithinTimePeriodPredicate} with the same
     * {@link AllWithinTimePeriodPredicate#endDateTime}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AllWithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((AllWithinTimePeriodPredicate) other).endDateTime)); // state check
    }

}
