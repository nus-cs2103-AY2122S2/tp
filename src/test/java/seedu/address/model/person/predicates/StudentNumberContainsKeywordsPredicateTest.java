package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StudentNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StudentNumberContainsKeywordsPredicate firstPredicate =
                new StudentNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        StudentNumberContainsKeywordsPredicate secondPredicate =
                new StudentNumberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StudentNumberContainsKeywordsPredicate firstPredicateCopy =
                new StudentNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentNumberContainsKeywords_returnsTrue() {
        // One keyword
        StudentNumberContainsKeywordsPredicate predicate =
                new StudentNumberContainsKeywordsPredicate(Collections.singletonList("02468"));
        assertTrue(predicate.test(new PersonBuilder().withStudentNumber("A0246813G").build()));

        // Multiple keywords
        predicate = new StudentNumberContainsKeywordsPredicate(Arrays.asList("024", "813G"));
        assertTrue(predicate.test(new PersonBuilder().withStudentNumber("A0246813G").build()));

        // Only one matching keyword
        predicate = new StudentNumberContainsKeywordsPredicate(Arrays.asList("123", "0246"));
        assertTrue(predicate.test(new PersonBuilder().withStudentNumber("A0246813G").build()));
    }

    @Test
    public void test_studentNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentNumberContainsKeywordsPredicate predicate =
                new StudentNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withStudentNumber("A0246813G").build()));

        // Non-matching keyword
        predicate = new StudentNumberContainsKeywordsPredicate(Arrays.asList("23456"));
        assertFalse(predicate.test(new PersonBuilder().withStudentNumber("A0246813G").build()));

        // Keywords match name, email and mod, but does not match student number
        predicate = new StudentNumberContainsKeywordsPredicate(Arrays.asList("CS2105", "alice@email.com", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withStudentNumber("A0246813G")
                .withEmail("alice@email.com").withMod("CS2105").build()));
    }
}
