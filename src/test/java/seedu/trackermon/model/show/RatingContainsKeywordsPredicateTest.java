package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

/**
 * Tests that a {@code Show}'s {@code Rating} matches any of the keywords given.
 */
public class RatingContainsKeywordsPredicateTest {

    /**
     * Tests whether two RatingContainsKeywordsPredicates are equal.
     */
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        RatingContainsKeywordsPredicate firstPredicate = new
                RatingContainsKeywordsPredicate(firstPredicateKeywordList);
        RatingContainsKeywordsPredicate secondPredicate = new
                RatingContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RatingContainsKeywordsPredicate firstPredicateCopy = new
                RatingContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different rating -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /**
     * Tests whether RatingContainsKeywordsPredicate contains show rating keywords.
     */
    @Test
    public void test_ratingContainsKeywords_returnsTrue() {
        // One keyword
        RatingContainsKeywordsPredicate predicate = new
                RatingContainsKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new ShowBuilder().withRating("1").build()));

        // Only one matching keyword
        predicate = new RatingContainsKeywordsPredicate(Arrays.asList("1", "2"));
        assertTrue(predicate.test(new ShowBuilder().withRating("1").build()));
    }

    /**
     * Tests whether RatingContainsKeywordsPredicate does not contain show rating keywords.
     */
    @Test
    public void test_ratingDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        RatingContainsKeywordsPredicate predicate = new
                RatingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ShowBuilder().withRating("1").build()));


        // Non-matching keyword
        predicate = new RatingContainsKeywordsPredicate(Arrays.asList("1"));
        assertFalse(predicate.test(new ShowBuilder().withRating("2").build()));

        // Non-matching keyword + other field
        predicate = new RatingContainsKeywordsPredicate(Arrays.asList("1"));
        assertFalse(predicate.test(new ShowBuilder().withRating("2").withTags("anime").build()));
    }
}
