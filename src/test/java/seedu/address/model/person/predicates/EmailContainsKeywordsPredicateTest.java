package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy =
                new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different email -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.singletonList(
                "example"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Multiple keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("example", ".com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Only one matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("google.com", "example.com"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("eXaMpLe.cOm", "gOogLe.COm"));
        assertTrue(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Non-matching keyword
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("google.com"));
        assertFalse(predicate.test(new PersonBuilder().withEmail("example@example.com").build()));

        // Keywords match student number, name and mod, but does not match email
        predicate = new EmailContainsKeywordsPredicate(Arrays.asList("12345", "Alice", "CS2105"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withStudentNumber("A0246813G")
                .withEmail("alxce@email.com").withMod("CS2105").build()));
    }
}
