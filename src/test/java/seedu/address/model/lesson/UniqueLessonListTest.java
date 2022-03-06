package seedu.address.model.lesson;

import org.junit.jupiter.api.Test;
import seedu.address.model.lesson.exceptions.ConflictsWithLessonException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static seedu.address.testutil.Assert.assertThrows;

public class UniqueLessonListTest {
    LocalDateTime d1 = LocalDateTime.of(2022, 1, 25, 18, 0, 0);
    LocalDateTime d2 = LocalDateTime.of(2022, 1, 25, 18, 0, 0);
    Lesson sameStartingDateTimeOne = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology", d1, 1, 0);
    Lesson sameStartingDateTimeTwo = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology", d2, 0, 50);
    List<Lesson> listWithConflictingLessons = List.of(sameStartingDateTimeOne, sameStartingDateTimeTwo);

    LocalDateTime d3 = LocalDateTime.of(2022, 1, 25, 18, 0, 0);
    LocalDateTime d4 = LocalDateTime.of(2022, 1, 25, 19, 0, 0);
    Lesson nonConflictingLessonOne = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology", d3, 0, 50);
    Lesson nonConflictingLessonTwo = Lesson.makeTemporaryLesson(
            "Test Lesson", "Biology", d4, 0, 50);

    List<Lesson> listWithNonConflictingLessons = List.of(nonConflictingLessonOne, nonConflictingLessonTwo);

    @Test
    public void setLessons_settingConflictingLessons_throwsConflictsWithLessonException() {
        UniqueLessonList l = new UniqueLessonList();

        assertThrows(ConflictsWithLessonException.class,
                () -> l.setLessons(listWithConflictingLessons));
    }

    @Test
    public void setLessons_addingConflictingLesson_throwsConflictsWithLessonException() {
        UniqueLessonList l = new UniqueLessonList();
        l.setLessons(listWithNonConflictingLessons);

        Lesson conflictingLesson = Lesson.makeTemporaryLesson(
                "Test Lesson", "Biology", d3, 0, 50);

        assertThrows(ConflictsWithLessonException.class,
                () -> l.add(conflictingLesson));
    }
}
