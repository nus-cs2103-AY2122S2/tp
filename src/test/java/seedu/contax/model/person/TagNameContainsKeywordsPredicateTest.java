package seedu.contax.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

public class TagNameContainsKeywordsPredicateTest {

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // complete words
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("friends");
        assertTrue(predicate.test(ALICE));

        // partial words
        predicate = new TagNameContainsKeywordsPredicate("fri");
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("not the same");
        assertFalse(predicate.test(ALICE));

        // extra characters of an existing keyword
        predicate = new TagNameContainsKeywordsPredicate("ssssfriendsssss");
        assertFalse(predicate.test(ALICE));
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
