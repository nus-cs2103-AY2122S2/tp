package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TemporaryLessonBuilder;

public class LessonTest {
    @Test
    public void makeTemporaryLesson_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TemporaryLessonBuilder().withName(null).build());
    }

    @Test
    public void makeTemporaryLesson_nullSubject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TemporaryLessonBuilder().withSubject(null).build());
    }

    @Test
    public void isConflictingWithLesson_lessonsWithSameStartingDateTime_areConflicting() {
        LocalDateTime lessonDateTime = LocalDateTime.of(2022, 1, 5, 17, 50, 0);
        Lesson sameStartingDateTimeOne = new TemporaryLessonBuilder()
                .withDateTimeSlot(lessonDateTime, 1, 30)
                .build();
        Lesson sameStartingDateTimeTwo = new TemporaryLessonBuilder()
                .withDateTimeSlot(lessonDateTime, 1, 30)
                .build();

        assertTrue(sameStartingDateTimeOne.isConflictingWithLesson(sameStartingDateTimeTwo));
        assertTrue(sameStartingDateTimeTwo.isConflictingWithLesson(sameStartingDateTimeOne));
    }

    @Test
    public void isConflictingWithLesson_lessonsWithOverlappingTimeSlots_areConflicting() {
        LocalDateTime d1 = LocalDateTime.of(2022, 1, 20, 18, 00, 0);
        LocalDateTime d2 = LocalDateTime.of(2022, 1, 20, 18, 30, 0);

        Lesson overlappingDateTimeOne = new TemporaryLessonBuilder()
                .withDateTimeSlot(d1, 1, 30)
                .build();
        Lesson overlappingDateTimeTwo = new TemporaryLessonBuilder()
                .withDateTimeSlot(d2, 1, 30)
                .build();

        assertTrue(overlappingDateTimeOne.isConflictingWithLesson(overlappingDateTimeTwo));
        assertTrue(overlappingDateTimeTwo.isConflictingWithLesson(overlappingDateTimeOne));
    }

    @Test
    public void isConflictingWithLesson_consecutiveLessons_areNotConflicting() {
        LocalDateTime d1 = LocalDateTime.of(2022, 1, 20, 18, 00, 0);
        LocalDateTime d2 = LocalDateTime.of(2022, 1, 20, 19, 00, 0);

        Lesson overlappingDateTimeOne = new TemporaryLessonBuilder()
                .withDateTimeSlot(d1, 1, 0)
                .build();
        Lesson overlappingDateTimeTwo = new TemporaryLessonBuilder()
                .withDateTimeSlot(d2, 1, 0)
                .build();

        assertFalse(overlappingDateTimeOne.isConflictingWithLesson(overlappingDateTimeTwo));
        assertFalse(overlappingDateTimeTwo.isConflictingWithLesson(overlappingDateTimeOne));
    }
}
