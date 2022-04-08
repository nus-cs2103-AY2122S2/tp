package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

/**
 * Tests that a {@code Show}'s {@code Tag} matches any of the keywords given.
 */
public class TagsContainsKeywordsPredicateTest {

    /**
     * Tests whether two TagsContainsKeywordsPredicates are equal.
     */
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        TagsContainsKeywordsPredicate firstPredicate = new
                TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new
                TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new
                TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /**
     * Tests whether TagsContainsKeywordsPredicate contains show tag keywords.
     */
    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate = new
                TagsContainsKeywordsPredicate(Collections.singletonList("anime"));
        assertTrue(predicate.test(new ShowBuilder().withTags("anime").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("anime", "HORROR"));
        assertTrue(predicate.test(new ShowBuilder().withTags("anime").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("ANime", "AcTion"));
        assertTrue(predicate.test(new ShowBuilder().withTags("anime").build()));
    }

    /**
     * Tests whether TagsContainsKeywordsPredicate does not contain show tag keywords.
     */
    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new
                TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ShowBuilder().withTags("anime").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Erased"));
        assertFalse(predicate.test(new ShowBuilder().withTags("anime").build()));

        // Non-matching keyword (partially matched)
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("anime"));
        assertFalse(predicate.test(new ShowBuilder().withTags("ani").build()));

        // Non-matching keyword + other field
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Horror"));
        assertFalse(predicate.test(new ShowBuilder().withTags("Action").withName("Anime").build()));
    }
}
