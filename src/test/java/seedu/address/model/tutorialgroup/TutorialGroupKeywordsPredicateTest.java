package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class TutorialGroupKeywordsPredicateTest {
    @Test
    public void equals() {
        String firstPredicateKeyword = "CS2101 G08";
        String secondPredicateKeyword = "CS2103 W15-3";

        TutorialGroupKeywordsPredicate firstPredicate = new TutorialGroupKeywordsPredicate(firstPredicateKeyword);
        TutorialGroupKeywordsPredicate secondPredicate = new TutorialGroupKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialGroupKeywordsPredicate firstPredicateCopy = new TutorialGroupKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialGroupFound_returnsTrue() {
        // Matching keyword
        TutorialGroupKeywordsPredicate predicate = new TutorialGroupKeywordsPredicate("CS2101 G08");
        assertTrue(predicate.test(new StudentBuilder().withTutorialGroup("CS2103T W15-3", "CS2101 G08").build()));

        // Mixed-case keyword
        predicate = new TutorialGroupKeywordsPredicate("cS2101 g08");
        assertTrue(predicate.test(new StudentBuilder().withTutorialGroup("CS2101 G08").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keywords
        TutorialGroupKeywordsPredicate predicate = new TutorialGroupKeywordsPredicate("MA1501 T03");
        assertFalse(predicate.test(new StudentBuilder().withTutorialGroup("CS2103T W15-3", "CS2101 G08").build()));

        // Keywords match email, github, and telegram, but does not match tutorial group
        predicate = new TutorialGroupKeywordsPredicate("MA1501_T03");
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withTelegram("MA1501_T03")
                .withEmail("MA1501_T03@gmail.com").withGitHub("MA1501-T03")
                .withTutorialGroup("CS2101 G08").build()));

        // Too many keywords
        predicate = new TutorialGroupKeywordsPredicate("CS2103T W15-3" + "CS2101 G08");
        assertFalse(predicate.test(new StudentBuilder().withTutorialGroup("CS2103T W15-3", "CS2101 G08").build()));

        // Zero keywords
        predicate = new TutorialGroupKeywordsPredicate(" ");
        assertFalse(predicate.test(new StudentBuilder().withTutorialGroup("CS2103T W15-3", "CS2101 G08").build()));
    }
}
