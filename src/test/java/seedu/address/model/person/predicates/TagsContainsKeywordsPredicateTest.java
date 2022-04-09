package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TagListBuilder;

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
                new TagsContainsKeywordsPredicate(Collections.singletonList("Friends"));
        ArrayList<Tag> tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2)).build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // One keyword match with different priorities
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2),
                                        new Tag("Friends", Priority.PRIORITY_4))
                                    .build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Multiple keywords match multiple tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends", "School"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2),
                                        new Tag("School", Priority.PRIORITY_2))
                                    .build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Multiple keywords match multiple tags with different priorities
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends", "School"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2),
                                        new Tag("Friends", Priority.PRIORITY_3),
                                        new Tag("School", Priority.PRIORITY_2),
                                        new Tag("School", Priority.PRIORITY_4))
                                    .build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Only one matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Friends", "Church"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2),
                                        new Tag("Colleague", Priority.PRIORITY_3))
                                    .build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Mixed-case keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("fRiEnDs", "sChOoL"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2),
                                        new Tag("School", Priority.PRIORITY_3))
                                    .build();
        assertTrue(predicate.test(new PersonBuilder().withTags(tagList).build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagsContainsKeywordsPredicate predicate = new TagsContainsKeywordsPredicate(Collections.emptyList());
        ArrayList<Tag> tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2)).build();
        assertFalse(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Non-matching keyword
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Church"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2)).build();
        assertFalse(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Multiple non-matching keywords
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Church", "Dance"));
        tagList = new TagListBuilder(new Tag("Friends", Priority.PRIORITY_2)).build();
        assertFalse(predicate.test(new PersonBuilder().withTags(tagList).build()));

        // Keywords match phone, email and address, but does not match tags
        predicate = new TagsContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com",
                "Main", "Street", "Friends"));
        tagList = new TagListBuilder(new Tag("Colleague", Priority.PRIORITY_2)).build();
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withTags(tagList).build()));
    }
}
