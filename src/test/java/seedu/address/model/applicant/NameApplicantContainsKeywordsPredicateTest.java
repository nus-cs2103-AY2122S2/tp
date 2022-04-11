package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ApplicantBuilder;

public class NameApplicantContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameApplicantContainsKeywordsPredicate firstPredicate =
                new NameApplicantContainsKeywordsPredicate(firstPredicateKeywordList);
        NameApplicantContainsKeywordsPredicate secondPredicate =
                new NameApplicantContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameApplicantContainsKeywordsPredicate firstPredicateCopy =
                new NameApplicantContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameApplicantContainsKeywords_returnsTrue() {
        // One keyword
        NameApplicantContainsKeywordsPredicate predicate =
                new NameApplicantContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameApplicantContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameApplicantContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameApplicantContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameApplicantDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameApplicantContainsKeywordsPredicate predicate =
                new NameApplicantContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameApplicantContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameApplicantContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ApplicantBuilder().withName("Alice").withPhone("81824512")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
