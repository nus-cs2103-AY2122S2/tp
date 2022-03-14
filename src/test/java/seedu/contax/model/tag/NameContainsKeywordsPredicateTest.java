package seedu.contax.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.TypicalPersons.FRIENDS;

import org.junit.jupiter.api.Test;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // complete words
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("friends");
        assertTrue(predicate.test(FRIENDS));

        // partial words
        predicate = new NameContainsKeywordsPredicate("fri");
        assertTrue(predicate.test(FRIENDS));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("not the same");
        assertFalse(predicate.test(FRIENDS));

        // extra characters of an existing keyword
        predicate = new NameContainsKeywordsPredicate("ssssfriendsssss");
        assertFalse(predicate.test(FRIENDS));
    }

    @Test
    public void equals() {
        String firstKeyword = "friends";
        String secondKeyword = "colleagues";

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstKeyword);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondKeyword);

        assertTrue(firstPredicate.equals(firstPredicate));
        assertTrue(firstPredicate.equals(new NameContainsKeywordsPredicate(firstKeyword)));

        assertFalse(firstPredicate.equals(secondPredicate));
        assertFalse(firstPredicate.equals(null));
        assertFalse(firstPredicate.equals(0));
    }
}
