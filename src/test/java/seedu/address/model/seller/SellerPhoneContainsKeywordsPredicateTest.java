package seedu.address.model.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SellerBuilder;

public class SellerPhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerPhoneContainsKeywordsPredicate firstPredicate = new SellerPhoneContainsKeywordsPredicate(
                firstPredicateKeywordList);
        SellerPhoneContainsKeywordsPredicate secondPredicate = new SellerPhoneContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerPhoneContainsKeywordsPredicate firstPredicateCopy = new SellerPhoneContainsKeywordsPredicate(
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
        SellerPhoneContainsKeywordsPredicate predicate = new SellerPhoneContainsKeywordsPredicate(
                Collections.singletonList("12312312"));
        assertTrue(predicate.test(new SellerBuilder().withPhone("12312312").build()));

        // Multiple keywords
        predicate = new SellerPhoneContainsKeywordsPredicate(Arrays.asList("123", "12312"));
        assertTrue(predicate.test(new SellerBuilder().withPhone("12312312").build()));

        // Only one matching keyword
        predicate = new SellerPhoneContainsKeywordsPredicate(Arrays.asList("123", "99999"));
        assertTrue(predicate.test(new SellerBuilder().withPhone("12312312").build()));

        // Mixed-case keywords
        predicate = new SellerPhoneContainsKeywordsPredicate(Arrays.asList("123", "bOB"));
        assertTrue(predicate.test(new SellerBuilder().withPhone("123123").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerPhoneContainsKeywordsPredicate predicate = new SellerPhoneContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new SellerBuilder().withPhone("69696969").build()));

        // Non-matching keyword
        predicate = new SellerPhoneContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new SellerBuilder().withPhone("123123").build()));

        // Keywords match phone, email and address, but does not match phone
        predicate = new SellerPhoneContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").withPhone("999")
                .build()));
    }
}
