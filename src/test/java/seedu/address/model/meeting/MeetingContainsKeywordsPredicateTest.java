package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.meeting.MeetingBuilder;

public class MeetingContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("test1");
        List<String> secondPredicateKeywordList = Arrays.asList("test1", "test2");

        MeetingContainsKeywordsPredicate firstPredicate =
                new MeetingContainsKeywordsPredicate(firstPredicateKeywordList);
        MeetingContainsKeywordsPredicate secondPredicate =
                new MeetingContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingContainsKeywordsPredicate firstPredicateCopy =
                new MeetingContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_meetingContainsKeywords_returnsTrue() {
        // One keyword matching meeting name
        MeetingContainsKeywordsPredicate nameTest =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("Lecture"));
        assertTrue(nameTest.test(new MeetingBuilder().withName("Lecture").build()));

        // One keyword matching meeting module
        MeetingContainsKeywordsPredicate moduleTest =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("CS2102"));
        assertTrue(moduleTest.test(new MeetingBuilder().withModule("CS2102").build()));

        // One keyword matching meeting tag
        MeetingContainsKeywordsPredicate tagTest =
                new MeetingContainsKeywordsPredicate(Collections.singletonList("Test"));
        assertTrue(tagTest.test(new MeetingBuilder().withTags("Test").build()));

        // Multiple keywords in same field
        MeetingContainsKeywordsPredicate predicate =
                new MeetingContainsKeywordsPredicate(Arrays.asList("Lecture", "Test"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Only one matching keyword in same field
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Lecture", "Dud"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Mixed letter cases
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("lEcTURe", "tESt"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Multiple field with matching keyword
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Lecture", "Test"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture").withTags("Test").build()));

        // Multiple fields with matching keyword and some invalid keyword
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Lecture", "Test", "Dud"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture").withTags("Test").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingContainsKeywordsPredicate predicate = new MeetingContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().build()));

        // Non-matching keyword
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("Dud"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture 101").build()));

        // Keyword matches url and datetime but not the valid fields
        predicate = new MeetingContainsKeywordsPredicate(Arrays.asList("www.zoom.com", "18-03-2022 1500"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture")
                .withUrl("www.zoom.com").withDateTime("18-03-2022 1500").build()));
    }
}
