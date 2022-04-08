package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

/**
 * Tests that a {@code Show}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicateTest {

    /**
     * Tests whether two NameContainsKeywordsPredicates are equal.
     */
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different name -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /**
     * Tests whether NameContainsKeywordsPredicate contains show name keywords.
     */
    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("Another"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another Erased").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Another", "Erased"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another Erased").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Erased", "Oreimo"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another Oreimo").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aNOTHER", "eRASED"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another Erased").build()));
    }

    /**
     * Tests whether NameContainsKeywordsPredicate does not contain show name keywords.
     */
    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ShowBuilder().withName("Another").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Oreimo"));
        assertFalse(predicate.test(new ShowBuilder().withName("Another Erased").build()));

        // Non-matching keyword + other field
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Another", "Erased"));
        assertFalse(predicate.test(new ShowBuilder().withName("Another").withName("Oreimo").build()));
    }
}
