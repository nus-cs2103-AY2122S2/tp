package seedu.address.model.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SellerBuilder;

public class SellerTagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerTagsContainsKeywordsPredicate firstPredicate = new SellerTagsContainsKeywordsPredicate(
                firstPredicateKeywordList);
        SellerTagsContainsKeywordsPredicate secondPredicate = new SellerTagsContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerTagsContainsKeywordsPredicate firstPredicateCopy = new SellerTagsContainsKeywordsPredicate(
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
        SellerTagsContainsKeywordsPredicate predicate = new SellerTagsContainsKeywordsPredicate(
                Collections.singletonList("Rich"));
        assertTrue(predicate.test(new SellerBuilder().withTags("Rich").build()));

        // Multiple keywords
        predicate = new SellerTagsContainsKeywordsPredicate(Arrays.asList("r", "ich"));
        assertTrue(predicate.test(new SellerBuilder().withTags("Rich").build()));

        // Only one matching keyword
        predicate = new SellerTagsContainsKeywordsPredicate(Arrays.asList("rich", "poor"));
        assertTrue(predicate.test(new SellerBuilder().withTags("richardpoor").build()));

        // Mixed-case keywords
        predicate = new SellerTagsContainsKeywordsPredicate(Arrays.asList("rIcH", "pOoR"));
        assertTrue(predicate.test(new SellerBuilder().withTags("Richard").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerTagsContainsKeywordsPredicate predicate = new SellerTagsContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new SellerBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new SellerTagsContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new SellerBuilder().withTags("AliceBob").build()));

        // Keywords match phone, email and address, but does not match Tags
        predicate = new SellerTagsContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new SellerBuilder().withName("Alice").withTags("LovesToSleep")
                .build()));
    }
}
