package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ClientBuilder;

public class NameContainsKeywordsPredicateTest {

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

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ClientBuilder().withName("Apple Boss").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Apple", "Boss"));
        assertTrue(predicate.test(new ClientBuilder().withName("Apple Boss").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Boss", "Carl's"));
        assertTrue(predicate.test(new ClientBuilder().withName("Apple Carl's").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("ApPle", "boSS"));
        assertTrue(predicate.test(new ClientBuilder().withName("Apple Boss").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withName("Apple").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carls"));
        assertFalse(predicate.test(new ClientBuilder().withName("Apple Boss").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ClientBuilder().withName("Apple").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
