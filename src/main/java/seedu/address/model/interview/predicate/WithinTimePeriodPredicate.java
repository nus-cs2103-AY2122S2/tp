package seedu.address.model.interview.predicate;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Represents a predicate of whether the {@code Interview} is within a specific time period.
 */
public abstract class WithinTimePeriodPredicate implements Predicate<Interview> {
    protected final LocalDateTime endDateTime;

    /**
     * Creates a new {@link WithinTimePeriodPredicate} object with the
     * {@link WithinTimePeriodPredicate#endDateTime} initialised.
     * @param currentDateTime contains the current date and time when the constructor is called
     */
    public WithinTimePeriodPredicate(LocalDateTime currentDateTime) {
        requireNonNull(currentDateTime);

        this.endDateTime = currentDateTime;
    }

    /**
     * Tests if any of the interviews scheduled falls within the specified time period
     * @param interview object to retrieve the ending date and time of the interview
     * @return true if the condition is fulfilled and false otherwise
     */
    @Override
    public boolean test(Interview interview) {
        requireNonNull(interview);

        return interview.getInterviewEndDateTime().isBefore(endDateTime);
    }

    /**
     * Checks if another object are instances of the same class and contains the same
     * {@link WithinTimePeriodPredicate#endDateTime}.
     * @param other object to compare against.
     * @return true if both objects are instances of {@link WithinTimePeriodPredicate} with the same
     * {@link WithinTimePeriodPredicate#endDateTime}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WithinTimePeriodPredicate // instanceof handles nulls
                && endDateTime.equals(((WithinTimePeriodPredicate) other).endDateTime)); // state check
    }
}
