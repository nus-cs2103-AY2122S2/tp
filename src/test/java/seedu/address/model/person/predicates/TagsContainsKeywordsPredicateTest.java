package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFERENT_PRIO_MULTI_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIFFERENT_PRIO_ONE_TAG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MULTIPLE_TAGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;

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

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword match
        TagsContainsKeywordsPredicate predicate =
                new TagsContainsKeywordsPredicate(Collections.singletonList("Friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_MULTIPLE_TAGS).build()));

        // One keyword match with different priorities
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_DIFFERENT_PRIO_ONE_TAG).build()));

        // Multiple keywords match multiple tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Husband", "Friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_MULTIPLE_TAGS).build()));

        // Multiple keywords match multiple tags with different priorities
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Husband", "Friend"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_DIFFERENT_PRIO_MULTI_TAG).build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friend", "Church"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_MULTIPLE_TAGS).build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("hUsBaNd", "fRiEnD"));
        assertTrue(predicate.test(new PersonBuilder().withTags(VALID_MULTIPLE_TAGS).build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags(VALID_TAG_FRIEND).build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Church"));
        assertFalse(predicate.test(new PersonBuilder().withTags(VALID_TAG_FRIEND).build()));

        // Multiple non-matching keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Church", "Dance"));
        assertFalse(predicate.test(new PersonBuilder().withTags(VALID_TAG_FRIEND).build()));

        // Keywords match phone, email and address, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Main", "Street", "Church"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags(VALID_TAG_FRIEND).build()));
    }
}
