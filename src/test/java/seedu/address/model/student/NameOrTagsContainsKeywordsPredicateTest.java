package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class NameOrTagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameOrTagsContainsKeywordsPredicate firstPredicate = new NameOrTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        NameOrTagsContainsKeywordsPredicate secondPredicate = new NameOrTagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameOrTagsContainsKeywordsPredicate firstPredicateCopy = new NameOrTagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameOrTagsContainsKeywordsPredicate predicate = new NameOrTagsContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrTagsContainsKeywordsPredicate predicate = new NameOrTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        NameOrTagsContainsKeywordsPredicate predicate = new NameOrTagsContainsKeywordsPredicate(Collections.singletonList("New"));
        assertTrue(predicate.test(new StudentBuilder().withTags("New").build()));

        // Multiple keywords
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("New", "Student"));
        assertTrue(predicate.test(new StudentBuilder().withTags("New", "Student").build()));

        // Only one matching keyword
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("Trial", "Student"));
        assertTrue(predicate.test(new StudentBuilder().withTags("New", "Student").build()));

        // Mixed-case keywords
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("nEw", "sTuDenT"));
        assertTrue(predicate.test(new StudentBuilder().withTags("New", "Student").build()));
    }

    @Test
    public void tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameOrTagsContainsKeywordsPredicate predicate = new NameOrTagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withTags("New").build()));

        // Non-matching keyword
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("New"));
        assertFalse(predicate.test(new StudentBuilder().withTags("Old", "Student").build()));

        // Keywords match phone, email and address, but does not match tag
        predicate = new NameOrTagsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder()
                .withName("Alice")
                .withPhone("12345")
                .withEmail("alice@email.com")
                .withAddress("Main Street")
                .withTags("Old")
                .build())
        );
    }
}
