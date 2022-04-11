package seedu.contax.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.getTypicalTags;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.model.tag.Tag;

public class TagNameContainsKeywordsPredicateTest {

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // complete words
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("friends");
        assertTrue(predicate.test(ALICE));

        // lower case adjustment
        predicate = new TagNameContainsKeywordsPredicate("Friends");
        assertTrue(predicate.test(ALICE));

        predicate = new TagNameContainsKeywordsPredicate("FRIENDS");
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("not the same");
        assertFalse(predicate.test(ALICE));
    }

    @Test
    public void test_partialKeywords_returnsFalse() {
        // partial words
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("fri");
        assertFalse(predicate.test(ALICE));

        // extra characters of an existing valid keyword
        predicate = new TagNameContainsKeywordsPredicate("ssssfriendsssss");
        assertFalse(predicate.test(ALICE));
    }

    @Test
    public void existsInTagList_exactKeyword_returnsTrue() {
        List<Tag> tags = getTypicalTags();
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("friends");

        assertTrue(predicate.existsInTagList(tags));
    }

    @Test
    public void existsInTagList_keywordNotExist_returnsFalse() {
        List<Tag> tags = getTypicalTags();

        // Partial keywords
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("fri");

        assertFalse(predicate.existsInTagList(tags));

        // Unrelated keywords
        predicate = new TagNameContainsKeywordsPredicate("does not exist");
        assertFalse(predicate.existsInTagList(tags));
    }

    @Test
    public void equals() {
        String firstKeyword = "friends";
        String secondKeyword = "colleagues";

        TagNameContainsKeywordsPredicate firstPredicate = new TagNameContainsKeywordsPredicate(firstKeyword);
        TagNameContainsKeywordsPredicate secondPredicate = new TagNameContainsKeywordsPredicate(secondKeyword);

        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(firstPredicate.equals(new TagNameContainsKeywordsPredicate(firstKeyword)));

        assertFalse(firstPredicate.equals(secondPredicate));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(0));
    }
}
