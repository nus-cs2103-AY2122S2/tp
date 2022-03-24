package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.PREF;

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
                new PreferenceContainsKeywordsPredicate(Collections.singletonList("east"));
        assertTrue(predicate.test(PREF));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("2-room"));
        assertTrue(predicate.test(PREF));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("$50000"));
        assertTrue(predicate.test(PREF));

        predicate = new PreferenceContainsKeywordsPredicate(Collections.singletonList("$500000"));
        assertTrue(predicate.test(PREF));

        // Multiple keywords
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("east", "2-room"));
        assertTrue(predicate.test(PREF));

        // Only one matching keyword
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("west", "2-room"));
        assertTrue(predicate.test(PREF));

        // Mixed-case keywords
        predicate = new PreferenceContainsKeywordsPredicate(Arrays.asList("eASt", "2-rOOm"));
        assertTrue(predicate.test(PREF));
    }

    @Test
    public void test_preferenceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
