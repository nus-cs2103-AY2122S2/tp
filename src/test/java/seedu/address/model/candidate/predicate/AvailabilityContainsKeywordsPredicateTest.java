package seedu.address.model.candidate.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CandidateBuilder;

public class AvailabilityContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Mon");
        List<String> secondPredicateKeywordList = Arrays.asList("Mon", "Tue");

        AvailabilityContainsKeywordsPredicate firstPredicate =
                new AvailabilityContainsKeywordsPredicate(firstPredicateKeywordList);
        AvailabilityContainsKeywordsPredicate secondPredicate =
                new AvailabilityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AvailabilityContainsKeywordsPredicate firstPredicateCopy =
                new AvailabilityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different candidate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_availabilityContainsKeywords_returnsTrue() {
        // One keyword - Normal case
        AvailabilityContainsKeywordsPredicate predicate =
                new AvailabilityContainsKeywordsPredicate(Arrays.asList("Mon"));
        assertTrue(predicate.test(new CandidateBuilder().withAvailability("1,2").build()));

        // Multiple keywords - Normal case
        predicate = new AvailabilityContainsKeywordsPredicate(Arrays.asList("Mon", "Wed", "Fri"));
        assertTrue(predicate.test(new CandidateBuilder().withAvailability("1,2").build()));

        // One keyword - Odd case
        predicate = new AvailabilityContainsKeywordsPredicate(Arrays.asList("MOn"));
        assertTrue(predicate.test(new CandidateBuilder().withAvailability("1,2").build()));

        // Multiple keywords - Odd case
        predicate = new AvailabilityContainsKeywordsPredicate(Arrays.asList("mOn", "WeD", "FRi"));
        assertTrue(predicate.test(new CandidateBuilder().withAvailability("1,2").build()));
    }

    @Test
    public void test_availabilityDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        AvailabilityContainsKeywordsPredicate predicate =
                new AvailabilityContainsKeywordsPredicate(Arrays.asList("Monday"));
        assertFalse(predicate.test(new CandidateBuilder().withAvailability("1,2").build()));
    }
}
