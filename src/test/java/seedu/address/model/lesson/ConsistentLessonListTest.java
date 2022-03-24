package seedu.address.model.lesson;

import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.exceptions.ConflictsWithLessonsException;
import seedu.address.model.lesson.exceptions.ContainsConflictingLessonsException;
import seedu.address.testutil.TemporaryLessonBuilder;

public class ConsistentLessonListTest {
    private final LocalDateTime sameLessonDateTime = LocalDateTime.of(
            2022, 1, 25, 18, 0, 0);

    private final Lesson sameStartingDateTimeOne = new TemporaryLessonBuilder()
            .withDateTimeSlot(sameLessonDateTime, 1, 0)
            .build();
    private final Lesson sameStartingDateTimeTwo = new TemporaryLessonBuilder()
            .withDateTimeSlot(sameLessonDateTime, 30, 0)
            .build();

    private final List<Lesson> listWithConflictingLessons = List.of(sameStartingDateTimeOne, sameStartingDateTimeTwo);

    private final LocalDateTime differentLessonDateTimeOne = LocalDateTime.of(
            2022, 1, 25, 18, 0, 0);
    private final LocalDateTime differentLessonDateTimeTwo = LocalDateTime.of(
            2022, 1, 25, 19, 0, 0);

    private final Lesson nonConflictingLessonOne = new TemporaryLessonBuilder()
            .withDateTimeSlot(differentLessonDateTimeOne, 0, 50)
            .build();
    private final Lesson nonConflictingLessonTwo = new TemporaryLessonBuilder()
            .withDateTimeSlot(differentLessonDateTimeTwo, 0, 50)
            .build();

    private final List<Lesson> listWithNonConflictingLessons = List.of(
            nonConflictingLessonOne, nonConflictingLessonTwo);

    @Test
    public void setLessons_settingConflictingLessons_throwsContainsConflictingLessonsException() {
        ConsistentLessonList l = new ConsistentLessonList();

        assertThrows(ContainsConflictingLessonsException.class, () -> l.setLessons(listWithConflictingLessons));
    }

    @Test
    public void setLessons_addingConflictingLesson_throwsConflictsWithLessonException() {
        ConsistentLessonList l = new ConsistentLessonList();
        l.setLessons(listWithNonConflictingLessons);

        LocalDateTime conflictingStartingDateTime = differentLessonDateTimeOne;

        Lesson conflictingLesson = new TemporaryLessonBuilder()
                .withDateTimeSlot(conflictingStartingDateTime, 0, 50)
                        .build();

        assertThrows(ConflictsWithLessonsException.class, () -> l.add(conflictingLesson));
    }
}
