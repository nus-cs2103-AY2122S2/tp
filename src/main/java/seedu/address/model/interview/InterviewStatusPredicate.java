package seedu.address.model.interview;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Interview}'s {@code Status} matches the status given.
 */
public class InterviewStatusPredicate implements Predicate<Interview> {
    private final String status;

    public InterviewStatusPredicate(String status) {
        this.status = status;
    }

    @Override
    public boolean test(Interview interview) {
        assert status.equals("pending") || status.equals("passed") || status.equals("failed")
                || status.equals("accepted") || status.equals("rejected");
        return StringUtil.containsWordIgnoreCase(interview.getStatus().toString(), status);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InterviewStatusPredicate // instanceof handles nulls
                && status.equals(((InterviewStatusPredicate) other).status)); // state check
    }
}
