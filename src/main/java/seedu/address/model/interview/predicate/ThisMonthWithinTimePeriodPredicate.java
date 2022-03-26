package seedu.address.model.interview.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Tests that a {@code Interview}'s date and time is within the next month.
 */
public class ThisMonthWithinTimePeriodPredicate extends WithinTimePeriodPredicate implements Predicate<Interview> {
    private final LocalDateTime endDateTime;
    private final LocalDateTime currentDateTime;

    /**
     * Creates a new {@link ThisMonthWithinTimePeriodPredicate} object with the
     * {@link ThisMonthWithinTimePeriodPredicate#endDateTime} initialised.
     * @param currentDateTime contains the current date and time at the point this constructor is called.
     */
    public ThisMonthWithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        super(currentDateTime.plusMonths(1));
        this.endDateTime = currentDateTime.plusMonths(1);
        this.currentDateTime = currentDateTime;
    }

    /**
     * Tests if {@code Interview}'s date and time is within the next month.
     * @param interview object to retrieve the date and time.
     * @return true if within 1 month of the current date and time.
     */
    @Override
    public boolean test(Interview interview) {
        return interview.getInterviewDateTime().isBefore(endDateTime)
                && interview.getInterviewDateTime().isAfter(currentDateTime);
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link ThisMonthWithinTimePeriodPredicate#endDateTime}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link ThisMonthWithinTimePeriodPredicate} with the same
     * {@link ThisMonthWithinTimePeriodPredicate#endDateTime}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThisMonthWithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((ThisMonthWithinTimePeriodPredicate) other).endDateTime)); // state check
    }

}
