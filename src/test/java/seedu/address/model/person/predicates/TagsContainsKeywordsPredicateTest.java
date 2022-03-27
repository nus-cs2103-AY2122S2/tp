package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.testutil.PersonBuilder;

public class TagsContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagsContainsKeywordsPredicate firstPredicate = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        TagsContainsKeywordsPredicate secondPredicate = new TagsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagsContainsKeywordsPredicate firstPredicateCopy = new TagsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    /*
    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("Friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags(new Pair<>("Friends", null),
                new Pair<>("School", null)).build()));

        // Multiple keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends", "School"));
        assertTrue(predicate.test(new PersonBuilder().withTags(new Pair<>("Friends", null),
                new Pair<>("School", null)).build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends", "Church"));
        assertTrue(predicate.test(new PersonBuilder().withTags(new Pair<>("Church", null),
                new Pair<>("School", null)).build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("fRiEnDs", "sChOoL"));
        assertTrue(predicate.test(new PersonBuilder().withTags(new Pair<>("Friends", null),
                new Pair<>("School", null)).build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags(new Pair<>("Friends", null)).build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Church"));
        assertFalse(predicate.test(new PersonBuilder().withTags(new Pair<>("Friends", null),
                new Pair<>("School", null)).build()));

        // Keywords match phone, email and address, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Main", "Street", "Friends"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags(new Pair<>("Church", null)).build()));
    }
    */
}
