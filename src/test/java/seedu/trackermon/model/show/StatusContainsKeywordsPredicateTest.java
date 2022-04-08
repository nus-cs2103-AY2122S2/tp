package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

/**
 * Tests that a {@code Show}'s {@code Status} matches any of the keywords given.
 */
public class StatusContainsKeywordsPredicateTest {

    /**
     * Tests whether two StatusContainsKeywordsPredicates are equal.
     */
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        StatusContainsKeywordsPredicate firstPredicate = new
                StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate = new
                StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy = new
                StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different status -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /**
     * Tests whether StatusContainsKeywordsPredicate contains show status keywords.
     */
    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        StatusContainsKeywordsPredicate predicate = new
                StatusContainsKeywordsPredicate(Collections.singletonList("completed"));
        assertTrue(predicate.test(new ShowBuilder().withStatus("completed").build()));

        // Only one matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("completed", "WATCHING"));
        assertTrue(predicate.test(new ShowBuilder().withStatus("completed").build()));

        // Mixed-case keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("COmpleted", "WaTching"));
        assertTrue(predicate.test(new ShowBuilder().withStatus("completed").build()));
    }

    /**
     * Tests whether StatusContainsKeywordsPredicate does not contain show status keywords.
     */
    @Test
    public void test_statusDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate = new
                StatusContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ShowBuilder().withStatus("completed").build()));

        // Non-matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("plan-to-watch"));
        assertFalse(predicate.test(new ShowBuilder().withStatus("completed").build()));

        // Non-matching keyword + other field
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("watching"));
        assertFalse(predicate.test(new ShowBuilder().withStatus("completed").withTags("Anime").build()));
    }
}
