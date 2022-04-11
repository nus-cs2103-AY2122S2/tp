package seedu.address.model.buyer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BuyerBuilder;

public class BuyerPhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BuyerPhoneContainsKeywordsPredicate firstPredicate = new BuyerPhoneContainsKeywordsPredicate(
                firstPredicateKeywordList);
        BuyerPhoneContainsKeywordsPredicate secondPredicate = new BuyerPhoneContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        BuyerPhoneContainsKeywordsPredicate firstPredicateCopy = new BuyerPhoneContainsKeywordsPredicate(
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
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        BuyerPhoneContainsKeywordsPredicate predicate = new BuyerPhoneContainsKeywordsPredicate(
                Collections.singletonList("123123"));
        assertTrue(predicate.test(new BuyerBuilder().withPhone("123123").build()));

        // Multiple keywords
        predicate = new BuyerPhoneContainsKeywordsPredicate(Arrays.asList("123", "69"));
        assertTrue(predicate.test(new BuyerBuilder().withPhone("12369").build()));

        // Only one matching keyword
        predicate = new BuyerPhoneContainsKeywordsPredicate(Arrays.asList("123", "999"));
        assertTrue(predicate.test(new BuyerBuilder().withPhone("999696969").build()));

    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BuyerPhoneContainsKeywordsPredicate predicate = new BuyerPhoneContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new BuyerBuilder().withPhone("69696969").build()));

        // Non-matching keyword
        predicate = new BuyerPhoneContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new BuyerBuilder().withPhone("123123").build()));

        // Keywords match phone, email and address, but does not match phone
        predicate = new BuyerPhoneContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new BuyerBuilder().withName("Alice").withPhone("999")
                .build()));

    }
}
