package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

class NameJobContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameJobContainsKeywordsPredicate firstPredicate =
                new NameJobContainsKeywordsPredicate(firstPredicateKeywordList);
        NameJobContainsKeywordsPredicate secondPredicate =
                new NameJobContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameJobContainsKeywordsPredicate firstPredicateCopy =
                new NameJobContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameJobContainsKeywords_returnsTrue() {
        // One keyword
        NameJobContainsKeywordsPredicate predicate =
                new NameJobContainsKeywordsPredicate(Collections.singletonList("Developer"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Software Developer").build()));

        // Multiple keywords
        predicate = new NameJobContainsKeywordsPredicate(Arrays.asList("Software", "Developer"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Software Developer").build()));

        // Only one matching keyword
        predicate = new NameJobContainsKeywordsPredicate(Arrays.asList("Software", "Developer"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("UI Developer").build()));

        // Mixed-case keywords
        predicate = new NameJobContainsKeywordsPredicate(Arrays.asList("sOFTwarE", "DeVEloPeR"));
        assertTrue(predicate.test(new JobBuilder().withJobTitle("Software Developer").build()));
    }

    @Test
    public void test_nameJobDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameJobContainsKeywordsPredicate predicate = new NameJobContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Developer").build()));

        // Non-matching keyword
        predicate = new NameJobContainsKeywordsPredicate(Arrays.asList("Admin"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Software Developer").build()));

        // Keywords match company name, qualification and address, but does not match job title
        predicate = new NameJobContainsKeywordsPredicate(Arrays.asList("Google", "Computer", "Science", "Degree",
                "Main", "Street"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Developer").withCompanyName("Google")
                .withJobQualification("Computer Science Degree").withJobAddress("Main Street").build()));
    }
}
