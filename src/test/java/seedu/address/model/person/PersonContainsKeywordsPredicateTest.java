package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {

        List<String> first = new ArrayList<>();
        first.add("n/first");

        List<String> second = new ArrayList<>();
        second.add("j/second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(first);
        PersonContainsKeywordsPredicate secondPredicate = new PersonContainsKeywordsPredicate(second);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);
        assertEquals(secondPredicate, secondPredicate);

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy = new PersonContainsKeywordsPredicate(first);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different applicant -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        List<String> keywords = new ArrayList<>();

        // One keyword
        keywords.add("n/alice");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        keywords.add("n/bob");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        keywords.add("n/john");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        keywords.clear();
        keywords.add("n/aLIce bOB");
        predicate = new PersonContainsKeywordsPredicate(keywords);
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {

        List<String> keywords = new ArrayList<>();

        // Non-matching keyword
        keywords.add("n/Carol");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }
}
