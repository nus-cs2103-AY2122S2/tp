package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.testutil.CustomerBuilder;

public class CustomerSearchContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ArrayList<List<String>> firstPrefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        ArrayList<List<String>> secondPrefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        firstPrefixArr.set(0, firstPredicateKeywordList);
        CustomerSearchContainsKeywordsPredicate firstPredicate =
                new CustomerSearchContainsKeywordsPredicate(firstPrefixArr);
        secondPrefixArr.set(0, secondPredicateKeywordList);
        CustomerSearchContainsKeywordsPredicate secondPredicate =
                new CustomerSearchContainsKeywordsPredicate(secondPrefixArr);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CustomerSearchContainsKeywordsPredicate firstPredicateCopy =
                new CustomerSearchContainsKeywordsPredicate(firstPrefixArr);
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
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        prefixArr.set(0, Collections.singletonList("Alice"));
        CustomerSearchContainsKeywordsPredicate predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        prefixArr.set(0, Arrays.asList("Alice", "Bob"));
        predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        prefixArr.set(0, Arrays.asList("Bob", "Carol"));
        predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        prefixArr.set(0, Arrays.asList("aLIce", "bOB"));
        predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));

        // Zero keywords
        CustomerSearchContainsKeywordsPredicate predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        prefixArr.set(0, Arrays.asList("Carol"));
        predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        prefixArr.set(0, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicate = new CustomerSearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
