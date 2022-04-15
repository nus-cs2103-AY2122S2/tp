package seedu.address.model.testresult;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.patient.Nric;

/**
 * Tests that a {@code Person}'s {@code TestResult} matches any of the keywords given.
 */
public class TestResultContainsKeywordsPredicate implements Predicate<TestResult> {
    private final List<String> keywords;

    public TestResultContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the specified {@code TestResult} belonging to this patient matches any of the specified keywords.
     *
     * @param testResult TestResult records belonging to the currently viewed patient
     * @return true if any of the keyword matches
     */
    @Override
    public boolean test(TestResult testResult) {
        return StringUtil.containsWordIgnoreCase(testResult.getPatientNric().toString(),
                    ViewedNric.getViewedNric().toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getTestDate().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getMedicalTest().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getResult().toString(), keyword)));
    }

    /**
     * This overloaded test method facilitates unit testing by passing the currently viewed patient's NRIC as a
     * parameter instead of using the get method.
     *
     * @param testResult TestResult records belonging to the currently viewed patient
     * @param viewedNric Currently viewed patient's NRIC
     * @return true if any of the keyword matches
     */
    public boolean test(TestResult testResult, Nric viewedNric) {
        return StringUtil.containsWordIgnoreCase(testResult.getPatientNric().toString(),
                viewedNric.toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getTestDate().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getMedicalTest().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testResult.getResult().toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestResultContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TestResultContainsKeywordsPredicate) other).keywords)); // state check
    }

}
