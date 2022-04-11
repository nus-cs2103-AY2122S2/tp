package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StatusContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("Positive");
        List<String> secondPredicateKeywordList = Arrays.asList("Positive", "Negative");

        StatusContainsKeywordsPredicate firstPredicate = new
                StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate = new
                StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy = new
                StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_statusContainsKeywords_returnsTrue() {
        // One keyword
        StatusContainsKeywordsPredicate predicate = new
                StatusContainsKeywordsPredicate(Collections.singletonList("Positive"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Positive").build()));

        // Exception thrown for >1 word in status
        try {
            new PersonBuilder().withStatus("Positive Negative").build();
        } catch (Exception e) {
            assertTrue(e.getMessage() == Status.MESSAGE_CONSTRAINTS);
        }

        // Exception thrown for non-conforming syntax in status
        try {
            new PersonBuilder().withStatus("Pos").build();
        } catch (Exception e) {
            assertTrue(e.getMessage() == Status.MESSAGE_CONSTRAINTS);
        }
    }
}
