package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TemporaryLessonBuilder;

public class ConflictingLessonsPredicateTest {
    @Test
    public void equals() {
        Lesson firstLessonToCheck = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 18, 0), 1, 0)
                .build();
        Lesson secondLessonToCheck = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 2, 18, 0), 1, 0)
                .build();

        ConflictingLessonsPredicate firstPredicate = new ConflictingLessonsPredicate(firstLessonToCheck);
        ConflictingLessonsPredicate secondPredicate = new ConflictingLessonsPredicate(secondLessonToCheck);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ConflictingLessonsPredicate firstPredicateCopy = new ConflictingLessonsPredicate(firstLessonToCheck);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different lesson -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_lessonsAreConflicting_returnsTrue() {
        // lessons with same starting date and time
        Lesson existingLesson = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 18, 0), 1, 0)
                .build();
        Lesson lessonWithSameStartingDateTime = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 18, 0), 1, 0)
                .build();
        ConflictingLessonsPredicate predicate = new ConflictingLessonsPredicate(lessonWithSameStartingDateTime);
        assertTrue(predicate.test(existingLesson));

        // lessons with overlapping time slots
        Lesson lessonThatStartsBeforeOtherLessonEnds = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 18, 30), 1, 0)
                .build();
        predicate = new ConflictingLessonsPredicate(lessonThatStartsBeforeOtherLessonEnds);
        assertTrue(predicate.test(existingLesson));
    }

    @Test
    public void test_lessonsThatAreNotConflicting_returnsFalse() {
        // consecutive lessons
        Lesson existingLesson = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 18, 0), 1, 0)
                .build();
        Lesson lessonThatStartsRightAfterPreviousOneEnds = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 1, 19, 0), 1, 0)
                .build();
        ConflictingLessonsPredicate predicate =
                new ConflictingLessonsPredicate(lessonThatStartsRightAfterPreviousOneEnds);
        assertFalse(predicate.test(existingLesson));

        // lessons on different days but same time
        Lesson lessonOnDifferentDayButSameStartTime = new TemporaryLessonBuilder()
                .withDateTimeSlot(LocalDateTime.of(2022, 1, 2, 18, 0), 1, 0)
                .build();
        predicate = new ConflictingLessonsPredicate(lessonOnDifferentDayButSameStartTime);
        assertFalse(predicate.test(existingLesson));
    }
}
