package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerTagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BuyerTagsContainsKeywordsPredicate firstPredicate = new BuyerTagsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        BuyerTagsContainsKeywordsPredicate secondPredicate = new BuyerTagsContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BuyerTagsContainsKeywordsPredicate firstPredicateCopy = new BuyerTagsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        BuyerTagsContainsKeywordsPredicate predicate = new BuyerTagsContainsKeywordsPredicate(
                Collections.singletonList("Rich"));
        assertTrue(predicate.test(new BuyerBuilder().withTags("Rich").build()));

        // Multiple keywords
        predicate = new BuyerTagsContainsKeywordsPredicate(Arrays.asList("r", "ich"));
        assertTrue(predicate.test(new BuyerBuilder().withTags("Rich").build()));

        // Only one matching keyword
        predicate = new BuyerTagsContainsKeywordsPredicate(Arrays.asList("rich", "poor"));
        assertTrue(predicate.test(new BuyerBuilder().withTags("richardpoor").build()));

        // Mixed-case keywords
        predicate = new BuyerTagsContainsKeywordsPredicate(Arrays.asList("rIcH", "pOoR"));
        assertTrue(predicate.test(new BuyerBuilder().withTags("Richard").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BuyerTagsContainsKeywordsPredicate predicate = new BuyerTagsContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new BuyerBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new BuyerTagsContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new BuyerBuilder().withTags("AliceBob").build()));

        // Keywords match phone, email and address, but does not match Tags
        predicate = new BuyerTagsContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").withTags("LovesToSleep")
                .build()));
    }
}
