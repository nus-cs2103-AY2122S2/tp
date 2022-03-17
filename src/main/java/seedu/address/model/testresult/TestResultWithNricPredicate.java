package seedu.address.model.testresult;

import java.util.function.Predicate;

import seedu.address.model.patient.Nric;

public class TestResultWithNricPredicate implements Predicate<TestResult> {
    private final Nric nric;

    public TestResultWithNricPredicate(Nric nric) {
        this.nric = nric;
    }

    @Override
    public boolean test(TestResult testResult) {
        return testResult.getPatientNric().equals(nric);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestResultWithNricPredicate // instanceof handles nulls
                && nric.equals(((TestResultWithNricPredicate) other).nric)); // state check
    }
}
