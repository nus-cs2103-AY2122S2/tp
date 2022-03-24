package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TemporaryLessonBuilder;

public class LessonNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LessonNameContainsKeywordsPredicate firstPredicate =
                new LessonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        LessonNameContainsKeywordsPredicate secondPredicate =
                new LessonNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonNameContainsKeywordsPredicate firstPredicateCopy =
                new LessonNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different lesson -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        LessonNameContainsKeywordsPredicate predicate =
                new LessonNameContainsKeywordsPredicate(Collections.singletonList("Biology"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology").build()));

        // Multiple keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("Biology", "Lesson"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Lesson").build()));

        // Only one matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("Biology", "Lesson"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Class").build()));

        // Mixed-case keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("bIoLoGy", "lEsSoN"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Lesson").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LessonNameContainsKeywordsPredicate predicate =
                new LessonNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TemporaryLessonBuilder().withName("Biology Lesson").build()));

        // Non-matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("Biology"));
        assertFalse(predicate.test(new TemporaryLessonBuilder().withName("History Lesson").build()));

        // Keywords match subject and address, but does not match name
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("Biology", "Toa", "Payoh"));
        assertFalse(predicate.test(new TemporaryLessonBuilder()
                .withName("History Lesson")
                .withSubject("Biology")
                .withAddress("Block 235 Toa Payoh #20-111")
                .build())
        );
    }
}
