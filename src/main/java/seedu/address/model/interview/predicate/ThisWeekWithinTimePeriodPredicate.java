package seedu.address.model.interview.predicate;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Tests that a {@code Interview}'s date and time is within the next 7 days.
 */
public class ThisWeekWithinTimePeriodPredicate extends WithinTimePeriodPredicate implements Predicate<Interview> {
    private final LocalDateTime currentDateTime;

    /**
     * Creates a new {@link ThisWeekWithinTimePeriodPredicate} object with the
     * {@link ThisWeekWithinTimePeriodPredicate#endDateTime} initialised.
     * @param currentDateTime contains the current date and time at the point this constructor is called.
     */
    public ThisWeekWithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        super(currentDateTime.plusDays(7));
        this.currentDateTime = currentDateTime;
    }

    /**
     * Tests if {@code Interview}'s date and time is within the next 7 days.
     * @param interview object to retrieve the date and time.
     * @return true if within 7 days of the current date and time.
     */
    @Override
    public boolean test(Interview interview) {
        return interview.getInterviewDateTime().isBefore(endDateTime)
                && interview.getInterviewDateTime().isAfter(currentDateTime);
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link ThisWeekWithinTimePeriodPredicate#endDateTime}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link ThisWeekWithinTimePeriodPredicate} with the same
     * {@link ThisWeekWithinTimePeriodPredicate#endDateTime}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThisWeekWithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((ThisWeekWithinTimePeriodPredicate) other).endDateTime)); // state check
    }

}
