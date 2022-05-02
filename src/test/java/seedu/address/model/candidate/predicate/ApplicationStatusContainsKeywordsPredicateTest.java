package seedu.address.model.candidate.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CandidateBuilder;

public class ApplicationStatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ApplicationStatusContainsKeywordsPredicate firstPredicate =
                new ApplicationStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        ApplicationStatusContainsKeywordsPredicate secondPredicate =
                new ApplicationStatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ApplicationStatusContainsKeywordsPredicate firstPredicateCopy =
                new ApplicationStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_applicationStatusContainsKeywords_returnsTrue() {
        // One keyword
        ApplicationStatusContainsKeywordsPredicate predicate =
                new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("pend"));
        assertTrue(predicate.test(new CandidateBuilder().build()));
    }

    @Test
    public void test_applicationStatusDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        ApplicationStatusContainsKeywordsPredicate predicate =
                new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("hi"));
        assertFalse(predicate.test(new CandidateBuilder().build()));
    }
}
