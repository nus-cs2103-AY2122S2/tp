package seedu.address.model.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SellerBuilder;

public class SellerNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerNameContainsKeywordsPredicate firstPredicate = new SellerNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        SellerNameContainsKeywordsPredicate secondPredicate = new SellerNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerNameContainsKeywordsPredicate firstPredicateCopy = new SellerNameContainsKeywordsPredicate(
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
        SellerNameContainsKeywordsPredicate predicate = new SellerNameContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new SellerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new SellerNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new SellerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new SellerNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new SellerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new SellerNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new SellerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerNameContainsKeywordsPredicate predicate = new SellerNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new SellerNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new SellerNameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").withPhone("99999")
                .build()));
    }
}
