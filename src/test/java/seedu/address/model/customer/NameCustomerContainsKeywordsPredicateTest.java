package seedu.address.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CustomerBuilder;

public class NameCustomerContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameCustomerContainsKeywordsPredicate firstPredicate =
                new NameCustomerContainsKeywordsPredicate(firstPredicateKeywordList);
        NameCustomerContainsKeywordsPredicate secondPredicate =
                new NameCustomerContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameCustomerContainsKeywordsPredicate firstPredicateCopy =
                new NameCustomerContainsKeywordsPredicate(firstPredicateKeywordList);
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
        NameCustomerContainsKeywordsPredicate predicate = new NameCustomerContainsKeywordsPredicate(Collections
                .singletonList("Alice"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameCustomerContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameCustomerContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameCustomerContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameCustomerContainsKeywordsPredicate predicate = new NameCustomerContainsKeywordsPredicate(Collections
                .emptyList());
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameCustomerContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameCustomerContainsKeywordsPredicate(Arrays
                .asList("81234567", "Main", "Street"));
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").withPhone("81234567")
                .withAddress("Main " + "Street").build()));
    }
}
