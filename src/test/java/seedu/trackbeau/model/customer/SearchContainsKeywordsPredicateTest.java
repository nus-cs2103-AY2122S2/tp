package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.testutil.CustomerBuilder;

public class SearchContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SearchContainsKeywordsPredicate firstPredicate =
                new SearchContainsKeywordsPredicate("getName", 0, firstPredicateKeywordList);
        SearchContainsKeywordsPredicate secondPredicate =
                new SearchContainsKeywordsPredicate("getName", 0, secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SearchContainsKeywordsPredicate firstPredicateCopy =
                new SearchContainsKeywordsPredicate("getName", 0, firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        SearchContainsKeywordsPredicate predicate =
                new SearchContainsKeywordsPredicate("getName", 0,
                        Collections.singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new SearchContainsKeywordsPredicate("getName", 0, Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new SearchContainsKeywordsPredicate("getName", 0, Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new SearchContainsKeywordsPredicate("getName", 0, Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SearchContainsKeywordsPredicate predicate =
                new SearchContainsKeywordsPredicate("getName", 0, Collections.emptyList());
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new SearchContainsKeywordsPredicate("getName", 0, Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and trackbeau, but does not match name
        predicate = new SearchContainsKeywordsPredicate("getName",
                0, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
