package seedu.address.model.driver;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DriverBuilder;

public class StatusContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StatusContainsKeywordsPredicate firstPredicate =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate =
                new StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(Collections
                .singletonList("free"));
        assertTrue(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));

        // Multiple keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("free", "busy"));
        assertTrue(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));

        // Only one matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("haha", "free"));
        assertTrue(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));

        // Mixed-case keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("FrEE", "AbSent"));
        assertTrue(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(Collections
                .emptyList());
        assertFalse(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));

        // Non-matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("busy"));
        assertFalse(predicate.test(new DriverBuilder().withStatus(DriverStatus.FREE).build()));
    }
}

