package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BuyerNameContainsKeywordsPredicate firstPredicate = new BuyerNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        BuyerNameContainsKeywordsPredicate secondPredicate = new BuyerNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BuyerNameContainsKeywordsPredicate firstPredicateCopy = new BuyerNameContainsKeywordsPredicate(
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
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        BuyerNameContainsKeywordsPredicate predicate = new BuyerNameContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new BuyerNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new BuyerNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new BuyerNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BuyerNameContainsKeywordsPredicate predicate = new BuyerNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new BuyerNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new BuyerNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").withPhone("12345")
                .build()));
    }
}
