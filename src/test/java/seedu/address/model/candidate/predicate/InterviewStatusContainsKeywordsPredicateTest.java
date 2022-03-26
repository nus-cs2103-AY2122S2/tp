package seedu.address.model.candidate.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CandidateBuilder;

public class InterviewStatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        InterviewStatusContainsKeywordsPredicate firstPredicate =
                new InterviewStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        InterviewStatusContainsKeywordsPredicate secondPredicate =
                new InterviewStatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InterviewStatusContainsKeywordsPredicate firstPredicateCopy =
                new InterviewStatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_interviewStatusContainsKeywords_returnsTrue() {
        // One keyword
        InterviewStatusContainsKeywordsPredicate predicate =
                new InterviewStatusContainsKeywordsPredicate(Arrays.asList("not"));
        assertTrue(predicate.test(new CandidateBuilder().build()));
    }

    @Test
    public void test_interviewStatusDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        InterviewStatusContainsKeywordsPredicate predicate =
                new InterviewStatusContainsKeywordsPredicate(Arrays.asList("hi"));
        assertFalse(predicate.test(new CandidateBuilder().build()));
    }
}
