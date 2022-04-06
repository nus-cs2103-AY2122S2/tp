package seedu.trackermon.model.show;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackermon.testutil.ShowBuilder;

public class ShowContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        ShowContainsKeywordsPredicate firstPredicate = new
                ShowContainsKeywordsPredicate(firstPredicateKeywordList);
        ShowContainsKeywordsPredicate secondPredicate = new
                ShowContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ShowContainsKeywordsPredicate firstPredicateCopy = new
                ShowContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different endpoint -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_showContainsKeywords_returnsTrue() {
        // One keyword
        ShowContainsKeywordsPredicate predicate = new
                ShowContainsKeywordsPredicate(Collections.singletonList("Another"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another").build()));

        // Only one matching keyword
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("Another", "Erased"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another").build()));

        // Mixed-case keywords
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("aNother", "completed"));
        assertTrue(predicate.test(new ShowBuilder().withName("erased").build()));

        // In Name field
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("Another"));
        assertTrue(predicate.test(new ShowBuilder().withName("Another").build()));

        // In Status field
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("completed"));
        assertTrue(predicate.test(new ShowBuilder().withStatus("completed").build()));

        // In Tag field
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("Anime"));
        assertTrue(predicate.test(new ShowBuilder().withTags("Anime").build()));

        // In Rating field
        predicate = new ShowContainsKeywordsPredicate(Arrays.asList("4"));
        assertTrue(predicate.test(new ShowBuilder().withRating("4").build()));
    }
}
