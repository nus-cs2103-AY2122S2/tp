package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookBuilder;

public class BookNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        BookNameContainsKeywordsPredicate firstPredicate =
                new BookNameContainsKeywordsPredicate(firstPredicateKeywordList);
        BookNameContainsKeywordsPredicate secondPredicate =
                new BookNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        BookNameContainsKeywordsPredicate firstPredicateCopy =
                new BookNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different patron -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        BookNameContainsKeywordsPredicate predicate =
                new BookNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new BookBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new BookBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new BookBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new BookBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        BookNameContainsKeywordsPredicate predicate = new BookNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new BookBuilder().withName("Harry Potter").build()));

        // Non-matching keyword
        predicate = new BookNameContainsKeywordsPredicate(Arrays.asList("Harry", "Potter"));
        assertFalse(predicate.test(new BookBuilder().withName("Hunger Games").build()));

        // Keywords match isbn, but does not match name
        predicate = new BookNameContainsKeywordsPredicate(List.of("978-71617-018-8-5"));
        assertFalse(predicate.test(new BookBuilder().withName("Harry Potter")
            .withIsbn("978-71617-018-8-5").build()));
    }

}
