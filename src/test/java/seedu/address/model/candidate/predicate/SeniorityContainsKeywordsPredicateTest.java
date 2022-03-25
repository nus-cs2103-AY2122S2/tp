package seedu.address.model.candidate.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CandidateBuilder;

public class SeniorityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SeniorityContainsKeywordsPredicate firstPredicate =
                new SeniorityContainsKeywordsPredicate(firstPredicateKeywordList);
        SeniorityContainsKeywordsPredicate secondPredicate =
                new SeniorityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SeniorityContainsKeywordsPredicate firstPredicateCopy =
                new SeniorityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_seniorityContainsKeywords_returnsTrue() {
        // One keyword
        SeniorityContainsKeywordsPredicate predicate =
                new SeniorityContainsKeywordsPredicate(Arrays.asList("2"));
        assertTrue(predicate.test(new CandidateBuilder().withSeniority("2").build()));
    }

    @Test
    public void test_seniorityDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        SeniorityContainsKeywordsPredicate predicate =
                new SeniorityContainsKeywordsPredicate(Arrays.asList("hi"));
        assertFalse(predicate.test(new CandidateBuilder().withSeniority("2").build()));
    }
}
