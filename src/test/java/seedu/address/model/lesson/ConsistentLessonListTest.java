package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.lesson.exceptions.ConflictsWithLessonsException;
import seedu.address.model.lesson.exceptions.ContainsConflictingLessonsException;
import seedu.address.testutil.TemporaryLessonBuilder;

public class ConsistentLessonListTest {
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
            nonConflictingLessonOne, nonConflictingLessonTwo
    );

    @Test
    public void addLessons_addingConflictingLesson_throwsConflictsWithLessonsException() {
        LocalDateTime conflictingLessonDateTime = differentLessonDateTimeOne;
        Lesson conflictsWithTwoLessons = new TemporaryLessonBuilder()
                .withDateTimeSlot(conflictingLessonDateTime, 10, 0)
                .build();

        ConsistentLessonList l = new ConsistentLessonList();
        l.setLessons(listWithNonConflictingLessons);

        // adding a conflicting lesson should throw an exception
        assertThrows(ConflictsWithLessonsException.class, () -> l.add(conflictsWithTwoLessons));

        // also, exception thrown should contain the correct list of lessons that conflict with the lesson to be added
        List<Lesson> conflictingLessons = new ArrayList<>();
        try {
            l.add(conflictsWithTwoLessons);
        } catch (ConflictsWithLessonsException e) {
            conflictingLessons = e.getConflictingLessons();
        }

        for (Lesson lesson : listWithNonConflictingLessons) {
            assertTrue(conflictingLessons.contains(lesson));
        }
    }

    @Test
    public void findLessonConflictingWith_nonConflictingLesson_returnsNull() {
        ConsistentLessonList l = new ConsistentLessonList();
        l.setLessons(listWithNonConflictingLessons);

        LocalDateTime differentLessonDateTime = LocalDateTime.of(
                2022, 1, 26, 19, 0, 0);
        Lesson nonConflictingLesson = new TemporaryLessonBuilder()
                .withDateTimeSlot(differentLessonDateTime, 0, 50)
                .build();

        assertNull(l.findLessonConflictingWith(nonConflictingLesson));
    }

    @Test
    public void setLessons_settingConflictingLessons_throwsContainsConflictingLessonsException() {
        ConsistentLessonList l = new ConsistentLessonList();

        LocalDateTime sameLessonDateTime = LocalDateTime.of(
                2022, 1, 25, 18, 0, 0);

        Lesson sameStartingDateTimeOne = new TemporaryLessonBuilder()
                .withDateTimeSlot(sameLessonDateTime, 1, 0)
                .build();
        Lesson sameStartingDateTimeTwo = new TemporaryLessonBuilder()
                .withDateTimeSlot(sameLessonDateTime, 30, 0)
                .build();

        List<Lesson> listWithConflictingLessons = List.of(sameStartingDateTimeOne, sameStartingDateTimeTwo);

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
