package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ClassCodeContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("4A");
        List<String> secondPredicateKeywordList = Arrays.asList("4A", "4B");

        ClassCodeContainsKeywordsPredicate firstPredicate = new
                ClassCodeContainsKeywordsPredicate(firstPredicateKeywordList);
        ClassCodeContainsKeywordsPredicate secondPredicate = new
                ClassCodeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClassCodeContainsKeywordsPredicate firstPredicateCopy = new
                ClassCodeContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different class code -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_classCodeContainsKeywords_returnsTrue() {
        // One keyword
        ClassCodeContainsKeywordsPredicate predicate =
                new ClassCodeContainsKeywordsPredicate(Collections.singletonList("4A"));
        assertTrue(predicate.test(new PersonBuilder().withClassCode("4A").build()));
    }

    @Test
    public void test_classCodeDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ClassCodeContainsKeywordsPredicate predicate = new ClassCodeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withClassCode("4A").build()));

        // Non-matching keyword
        predicate = new ClassCodeContainsKeywordsPredicate(Arrays.asList("4C"));
        assertFalse(predicate.test(new PersonBuilder().withClassCode("4A").build()));
    }
}
