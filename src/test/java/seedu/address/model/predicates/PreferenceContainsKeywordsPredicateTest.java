package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PreferenceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PreferenceContainsKeywordsPredicate firstPredicate =
                new PreferenceContainsKeywordsPredicate(firstPredicateKeywordList);
        PreferenceContainsKeywordsPredicate secondPredicate =
                new PreferenceContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PreferenceContainsKeywordsPredicate firstPredicateCopy =
                new PreferenceContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_preferenceContainsKeywords_returnsTrue() {
        // One keyword
        PreferenceContainsKeywordsPredicate predicate =
                new PreferenceContainsKeywordsPredicate(Collections.singletonList("West"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("4-room"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("$100000"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("$200000"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Multiple keywords
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("West", "4-room"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Only one matching keyword
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("East", "4-room"));
        assertTrue(predicate.test(new PersonBuilder().build()));

        // Mixed-case keywords
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("wESt", "4-rOoM"));
        assertTrue(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void test_preferenceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PreferenceContainsKeywordsPredicate predicate =
                new PreferenceContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().build()));

        // Non-matching keyword
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("east", "1-room", "$50", "Alice"));
        assertFalse(predicate.test(new PersonBuilder().build()));

        // Keywords match name, phone, email and address, but does not match preference
        predicate = new PreferenceContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
