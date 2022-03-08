package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class LessonTest {
    private final LocalDateTime d = LocalDateTime.of(2022, 1, 5, 17, 50, 0);

    @Test
    public void makeTemporaryLesson_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Lesson.makeTemporaryLesson(
            null, "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, 1, 0));
    }

    @Test
    public void makeTemporaryLesson_nullSubject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Lesson.makeTemporaryLesson(
            "Test Lesson", null,
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, 1, 0));
    }

    @Test
    public void makeTemporaryLesson_negativeHours_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, -1, 0));
    }

    @Test
    public void makeTemporaryLesson_negativeMinutes_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Lesson.makeTemporaryLesson("Test Lesson", "Biology",
                        "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, -50));
    }

    @Test
    public void makeTemporaryLesson_zeroHoursAndZeroMinutes_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Lesson.makeTemporaryLesson("Test Lesson", "Biology",
                        "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, 0));
    }

    @Test
    public void makeTemporaryLesson_minutesMoreThan60_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Lesson.makeTemporaryLesson("Test Lesson", "Biology",
                        "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, 61));
    }

    @Test
    public void makeTemporaryLesson_zeroHoursAndPositiveMinutes_instantiatesSuccessfully() {
        assertNotNull(Lesson.makeTemporaryLesson("Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, 50));
    }

    @Test
    public void isConflictingWithLesson_lessonsWithSameStartingDateTime_areConflicting() {
        Lesson sameStartingDateTimeOne = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, 50);
        Lesson sameStartingDateTimeTwo = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d, 0, 50);

        assertTrue(sameStartingDateTimeOne.isConflictingWithLesson(sameStartingDateTimeTwo));
        assertTrue(sameStartingDateTimeTwo.isConflictingWithLesson(sameStartingDateTimeOne));
    }

    @Test
    public void isConflictingWithLesson_lessonsWithOverlappingTimeSlots_areConflicting() {
        LocalDateTime d1 = LocalDateTime.of(2022, 1, 20, 18, 00, 0);
        LocalDateTime d2 = LocalDateTime.of(2022, 1, 20, 18, 30, 0);

        Lesson overlappingDateTimeOne = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d1, 0, 50);
        Lesson overlappingDateTimeTwo = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d2, 0, 50);

        assertTrue(overlappingDateTimeOne.isConflictingWithLesson(overlappingDateTimeTwo));
        assertTrue(overlappingDateTimeTwo.isConflictingWithLesson(overlappingDateTimeOne));
    }
}
