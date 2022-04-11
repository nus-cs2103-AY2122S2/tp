package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for {@code AcademicMajorContainsKeywordsPredicate}.
 */
public class AcademicMajorContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AcademicMajorContainsKeywordsPredicate firstPredicate =
                new AcademicMajorContainsKeywordsPredicate(firstPredicateKeywordList);
        AcademicMajorContainsKeywordsPredicate secondPredicate =
                new AcademicMajorContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AcademicMajorContainsKeywordsPredicate firstPredicateCopy =
                new AcademicMajorContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_academicMajorContainsKeywords_returnsTrue() {
        // One keyword
        AcademicMajorContainsKeywordsPredicate predicate =
                new AcademicMajorContainsKeywordsPredicate(Collections.singletonList("Science"));
        assertTrue(predicate.test(new PersonBuilder().withAcademicMajor("Computer Science").build()));

        // Multiple keywords
        predicate =
                new AcademicMajorContainsKeywordsPredicate(Arrays.asList("Science", "Pharmaceutical"));
        assertTrue(predicate.test(new PersonBuilder().withAcademicMajor("Pharmaceutical Science").build()));

        // Only one matching keyword
        predicate =
                new AcademicMajorContainsKeywordsPredicate(Arrays.asList("Science", "Mathematics"));
        assertTrue(predicate.test(new PersonBuilder().withAcademicMajor("Applied Mathematics").build()));

        // Mixed-case keywords
        predicate =
                new AcademicMajorContainsKeywordsPredicate(Arrays.asList("SciENCe", "pHaRmACEuticAL"));
        assertTrue(predicate.test(new PersonBuilder().withAcademicMajor("Pharmaceutical Science").build()));
    }

    @Test
    public void test_academicMajorDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AcademicMajorContainsKeywordsPredicate predicate =
                new AcademicMajorContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withAcademicMajor("Computer Science").build()));

        // Non-matching keyword
        predicate =
                new AcademicMajorContainsKeywordsPredicate(Arrays.asList("Physics"));
        assertFalse(predicate.test(new PersonBuilder().withAcademicMajor("Computer Science").build()));

        // Keywords match name, phone, email and address, but does not match academic major
        predicate =
                new AcademicMajorContainsKeywordsPredicate(
                        Arrays.asList("12345", "alice@email.com", "Science", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAcademicMajor("Applied Mathematics").build()));
    }
}
