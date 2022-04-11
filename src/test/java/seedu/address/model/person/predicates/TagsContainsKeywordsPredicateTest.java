package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

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

        // different tags -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        TagsContainsKeywordsPredicate predicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));

        // Multiple keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("friend", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("friend", "student"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("fRiEnD", "sTuDenT"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("student"));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "colleagues").build()));

        // Keywords match student number, email, name and mod, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Alice", "CS2105"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withStudentNumber("A0246813G")
                .withEmail("alice@email.com").withMod("CS2105").withTags("friend", "colleagues").build()));
    }
}
