package seedu.address.model.lesson;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.exceptions.ConflictsWithLessonException;

public class UniqueLessonListTest {
    private final LocalDateTime d1 = LocalDateTime.of(2022, 1, 25, 18, 0, 0);
    private final LocalDateTime d2 = LocalDateTime.of(2022, 1, 25, 18, 0, 0);
    private final Lesson sameStartingDateTimeOne = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology",
            "Blk 11 Ang Mo Kio Street 74, #11-04", d1, 1, 0);
    private final Lesson sameStartingDateTimeTwo = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology",
            "Blk 11 Ang Mo Kio Street 74, #11-04", d2, 0, 50);
    private final List<Lesson> listWithConflictingLessons = List.of(sameStartingDateTimeOne, sameStartingDateTimeTwo);

    private final LocalDateTime d3 = LocalDateTime.of(
            2022, 1, 25, 18, 0, 0);
    private final LocalDateTime d4 = LocalDateTime.of(
            2022, 1, 25, 19, 0, 0);
    private final Lesson nonConflictingLessonOne = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology",
            "Blk 11 Ang Mo Kio Street 74, #11-04", d3, 0, 50);
    private final Lesson nonConflictingLessonTwo = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology",
            "Blk 11 Ang Mo Kio Street 74, #11-04", d4, 0, 50);

    private final List<Lesson> listWithNonConflictingLessons = List.of(
            nonConflictingLessonOne, nonConflictingLessonTwo);

    @Test
    public void setLessons_settingConflictingLessons_throwsConflictsWithLessonException() {
        UniqueLessonList l = new UniqueLessonList();

        assertThrows(ConflictsWithLessonException.class, () -> l.setLessons(listWithConflictingLessons));
    }

    @Test
    public void setLessons_addingConflictingLesson_throwsConflictsWithLessonException() {
        UniqueLessonList l = new UniqueLessonList();
        l.setLessons(listWithNonConflictingLessons);

        Lesson conflictingLesson = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology",
                "Blk 11 Ang Mo Kio Street 74, #11-04", d3, 0, 50);

        assertThrows(ConflictsWithLessonException.class, () -> l.add(conflictingLesson));
    }
}
