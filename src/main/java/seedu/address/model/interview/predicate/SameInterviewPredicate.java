package seedu.address.model.interview.predicate;

import java.util.function.Predicate;

import seedu.address.model.interview.Interview;

/**
 * Represents a predicate of whether the (@code Interview) is within a specific time period.
 */
public class SameInterviewPredicate implements Predicate<Interview> {
    private final Interview interview;

    public SameInterviewPredicate(Interview interview) {
        this.interview = interview;
    }

    @Override
    public boolean test(Interview interview) {
        return interview.equals(this.interview);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SameInterviewPredicate // instanceof handles nulls
                && interview.equals(((SameInterviewPredicate) other).interview)); // state check
    }
}
