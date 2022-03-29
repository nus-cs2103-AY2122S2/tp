package seedu.address.model.testresult;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;

/**
 * Tests that a {@code Person}'s {@code TestResult} matches any of the keywords given.
 */
public class TestResultContainsKeywordsPredicate implements Predicate<TestResult> {
    private final List<String> keywords;

    public TestResultContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TestResultContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TestResultContainsKeywordsPredicate) other).keywords)); // state check
    }

}
