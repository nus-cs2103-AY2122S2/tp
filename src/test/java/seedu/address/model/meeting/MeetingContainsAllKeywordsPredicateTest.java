package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.meeting.MeetingBuilder;

public class MeetingContainsAllKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("test1");
        List<String> secondPredicateKeywordList = Arrays.asList("test1", "test2");

        MeetingContainsAllKeywordsPredicate firstPredicate =
                new MeetingContainsAllKeywordsPredicate(firstPredicateKeywordList);
        MeetingContainsAllKeywordsPredicate secondPredicate =
                new MeetingContainsAllKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingContainsAllKeywordsPredicate firstPredicateCopy =
                new MeetingContainsAllKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_meetingContainsAllKeywords_returnsTrue() {
        // One keyword matching meeting name
        MeetingContainsAllKeywordsPredicate nameTest =
                new MeetingContainsAllKeywordsPredicate(Collections.singletonList("Lecture"));
        assertTrue(nameTest.test(new MeetingBuilder().withName("Lecture").build()));

        // One keyword matching meeting module
        MeetingContainsAllKeywordsPredicate moduleTest =
                new MeetingContainsAllKeywordsPredicate(Collections.singletonList("CS2102"));
        assertTrue(moduleTest.test(new MeetingBuilder().withModule("CS2102").build()));

        // One keyword matching meeting tag
        MeetingContainsAllKeywordsPredicate tagTest =
                new MeetingContainsAllKeywordsPredicate(Collections.singletonList("Test"));
        assertTrue(tagTest.test(new MeetingBuilder().withTags("Test").build()));

        // Multiple keywords in same field
        MeetingContainsAllKeywordsPredicate predicate =
                new MeetingContainsAllKeywordsPredicate(Arrays.asList("Lecture", "Test"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Mixed letter cases
        predicate = new MeetingContainsAllKeywordsPredicate(Arrays.asList("lEcTURe", "tESt"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Multiple field with matching keyword
        predicate = new MeetingContainsAllKeywordsPredicate(Arrays.asList("Lecture", "Test"));
        assertTrue(predicate.test(new MeetingBuilder().withName("Lecture").withTags("Test").build()));
    }

    @Test
    public void test_meetingContainsAllKeywords_returnsFalse() {
        // Only one matching keyword in same field
        MeetingContainsAllKeywordsPredicate predicate =
                new MeetingContainsAllKeywordsPredicate(Arrays.asList("Lecture", "Dud"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture Test").build()));

        // Multiple fields with matching keyword and some invalid keyword
        predicate = new MeetingContainsAllKeywordsPredicate(Arrays.asList("Lecture", "Test", "Dud"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture").withTags("Test").build()));

        // Zero keywords
        predicate = new MeetingContainsAllKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().build()));

        // Non-matching keyword
        predicate = new MeetingContainsAllKeywordsPredicate(Arrays.asList("Dud"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture 101").build()));

        // Keyword matches url and datetime but not the valid fields
        predicate = new MeetingContainsAllKeywordsPredicate(Arrays.asList("www.zoom.com", "18-03-2022 1500"));
        assertFalse(predicate.test(new MeetingBuilder().withName("Lecture")
                .withUrl("www.zoom.com").withDateTime("18-03-2022 1500").build()));
    }
}
