package seedu.address.model.candidate.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CandidateBuilder;

public class RemarkContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        RemarkContainsKeywordsPredicate firstPredicate = new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        RemarkContainsKeywordsPredicate secondPredicate =
                new RemarkContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RemarkContainsKeywordsPredicate firstPredicateCopy =
                new RemarkContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        RemarkContainsKeywordsPredicate predicate = new RemarkContainsKeywordsPredicate(Collections.singletonList(
                "a goo"));
        assertTrue(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));

        // Multiple keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("a good", "Quite a go"));
        assertTrue(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));

        // Only one matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("Quite a ", "11111"));
        assertTrue(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RemarkContainsKeywordsPredicate predicate = new RemarkContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));

        // Non-matching keyword
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("92352678"));
        assertFalse(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));

        // Spaces in keywords
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("Qu ite"));
        assertFalse(predicate.test(new CandidateBuilder().withRemark("Quite a good candidate").build()));

        // Keywords match other fields, but does/do not match phone
        predicate = new RemarkContainsKeywordsPredicate(Arrays.asList("Business Analytics", "alice@email.com", "Main",
                "Street"));
        assertFalse(predicate.test(new CandidateBuilder().withName("Alice").withRemark("Quite a good candidate")
                .withEmail("E0123456@u.nus.edu").withCourse("Business Analytics").withStudentId("A0188565L").build()));
    }
}
