package seedu.address.model.assessment;

import java.util.function.Predicate;

public class AssessmentNameIsEqualPredicate implements Predicate<Assessment> {
    private final AssessmentName assessmentName;

    public AssessmentNameIsEqualPredicate(AssessmentName assessmentName) {
        this.assessmentName = assessmentName;
    }

    @Override
    public boolean test(Assessment assessment) {
        return assessmentName.equals(assessment.getAssessmentName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AssessmentNameIsEqualPredicate // instanceof handles nulls
                && assessmentName.equals(((AssessmentNameIsEqualPredicate) other).assessmentName)); // state check
    }
}
