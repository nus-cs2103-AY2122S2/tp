package seedu.address.model.interview.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Tests that a {@code Interview}'s date and time is within the same day.
 */
public class TodayWithinTimePeriodPredicate extends WithinTimePeriodPredicate implements Predicate<Interview> {
    private final LocalDateTime endDateTime;

    /**
     * Creates a new {@link TodayWithinTimePeriodPredicate} object with the
     * {@link TodayWithinTimePeriodPredicate#endDateTime} initialised.
     * @param currentDateTime contains the current date and time at the point this constructor is called.
     */
    public TodayWithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        super(currentDateTime);
        this.endDateTime = currentDateTime;
    }

    /**
     * Tests if {@code Interview}'s date and time is within the same day.
     * @param interview object to retrieve the date and time.
     * @return true if within the same day of the current date and time.
     */
    @Override
    public boolean test(Interview interview) {
        return interview.getInterviewDate().isEqual(endDateTime.toLocalDate());
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link TodayWithinTimePeriodPredicate#endDateTime}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link TodayWithinTimePeriodPredicate} with the same
     * {@link TodayWithinTimePeriodPredicate#endDateTime}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TodayWithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((TodayWithinTimePeriodPredicate) other).endDateTime)); // state check
    }

}
