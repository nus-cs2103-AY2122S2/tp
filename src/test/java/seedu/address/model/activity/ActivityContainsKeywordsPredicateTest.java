package seedu.address.model.activity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ActivityContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("4A");
        List<String> secondPredicateKeywordList = Arrays.asList("4A", "4B");

        ActivityContainsKeywordsPredicate firstPredicate = new
                ActivityContainsKeywordsPredicate(firstPredicateKeywordList);
        ActivityContainsKeywordsPredicate secondPredicate = new
                ActivityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ActivityContainsKeywordsPredicate firstPredicateCopy = new
                ActivityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different class code -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_activityContainsKeywords_returnsTrue() {
        // One keyword
        ActivityContainsKeywordsPredicate predicate =
                new ActivityContainsKeywordsPredicate(Collections.singletonList("Choir"));
        assertTrue(predicate.test(new PersonBuilder().withActivity("Choir").build()));
    }

    @Test
    public void test_activityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ActivityContainsKeywordsPredicate predicate = new ActivityContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withActivity("Choir").build()));

        // Non-matching keyword
        predicate = new ActivityContainsKeywordsPredicate(Arrays.asList("Soccer"));
        assertFalse(predicate.test(new PersonBuilder().withActivity("Choir").build()));
    }
}

