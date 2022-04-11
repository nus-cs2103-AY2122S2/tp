package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TemporaryLessonBuilder;

public class LessonNameOrSubjectContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LessonNameOrSubjectContainsKeywordsPredicate firstPredicate =
                new LessonNameOrSubjectContainsKeywordsPredicate(firstPredicateKeywordList);
        LessonNameOrSubjectContainsKeywordsPredicate secondPredicate =
                new LessonNameOrSubjectContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonNameOrSubjectContainsKeywordsPredicate firstPredicateCopy =
                new LessonNameOrSubjectContainsKeywordsPredicate(firstPredicateKeywordList);
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
        LessonNameOrSubjectContainsKeywordsPredicate predicate =
                new LessonNameOrSubjectContainsKeywordsPredicate(Collections.singletonList("Biology"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology").build()));

        // Multiple keywords
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology", "Lesson"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Lesson").build()));

        // Only one matching keyword
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology", "Lesson"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Class").build()));

        // Mixed-case keywords
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("bIoLoGy", "lEsSoN"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withName("Biology Lesson").build()));
    }

    @Test
    public void test_subjectContainsKeywords_returnsTrue() {
        // One keyword
        LessonNameOrSubjectContainsKeywordsPredicate predicate =
                new LessonNameOrSubjectContainsKeywordsPredicate(Collections.singletonList("Biology"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withSubject("Biology").build()));

        // Multiple keywords
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology", "Remedial"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withSubject("Biology Remedial").build()));

        // Only one matching keyword
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology", "Lesson"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withSubject("Biology Remedial").build()));

        // Mixed-case keywords
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("bIoLoGy", "lEsSoN"));
        assertTrue(predicate.test(new TemporaryLessonBuilder().withSubject("Biology Remedial").build()));
    }

    @Test
    public void test_nameAndSubjectDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LessonNameOrSubjectContainsKeywordsPredicate predicate =
                new LessonNameOrSubjectContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TemporaryLessonBuilder()
                .withName("Biology Lesson")
                .withSubject("Biology")
                .build()));

        // Non-matching keyword
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology"));
        assertFalse(predicate.test(new TemporaryLessonBuilder()
                .withName("History Lesson")
                .withSubject("History")
                .build()));

        // Keywords match address, but does not match name nor subject
        predicate = new LessonNameOrSubjectContainsKeywordsPredicate(Arrays.asList("Biology", "Toa", "Payoh"));
        assertFalse(predicate.test(new TemporaryLessonBuilder()
                .withName("History Lesson")
                .withSubject("History")
                .withAddress("Block 235 Toa Payoh #20-111")
                .build())
        );
    }
}
