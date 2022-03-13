package seedu.address.model.person.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstPredicate = new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Collections.singletonList(
                "Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "Business Analytics"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("aLIce", "e0324"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));
    }

    @Test
    public void test_personDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));

        // Non-matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Carol Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("87654321")
                .withEmail("alice@email.com").withCourse("Business Analytics").withStudentID("E0324444").build()));
    }
}
