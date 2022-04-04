package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.model.LessonBook;
import seedu.address.model.lesson.Lesson;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    public static final Lesson TEMPORARY_LESSON = new TemporaryLessonBuilder().build();
    public static final Lesson RECURRING_LESSON = new RecurringLessonBuilder().build();

    public static final Lesson TEMPORARY_BIOLOGY_LESSON = new TemporaryLessonBuilder()
            .withName("Sec 2 Biology Group Tuition")
            .withSubject("Biology")
            .withDateTimeSlot(LocalDateTime.of(2022, 12, 19, 18, 0), 2, 15)
            .withAddress("Blk 11 Ang Mo Kio Street 74, #11-04")
            .build();

    public static final Lesson TEMPORARY_HISTORY_LESSON = new TemporaryLessonBuilder()
            .withName("Sec 3 History Tuition")
            .withSubject("History")
            .withDateTimeSlot(LocalDateTime.of(2022, 12, 21, 8, 0), 2, 15)
            .withAddress("Blk 11 Ang Mo Kio Street 74, #11-04")
            .build();

    public static final Lesson RECURRING_CHEMISTRY_LESSON = new RecurringLessonBuilder()
            .withName("Sec 2 Chemistry Group Tuition")
            .withSubject("Chemistry")
            .withDateTimeSlot(LocalDateTime.of(2022, 12, 20, 15, 0), 2, 15)
            .withAddress("Blk 11 Ang Mo Kio Street 74, #11-04")
            .build();

    private TypicalLessons() {} // prevents instantiation

    public static List<Lesson> getTypicalLessons() {
        return List.of(TEMPORARY_BIOLOGY_LESSON, RECURRING_CHEMISTRY_LESSON, TEMPORARY_HISTORY_LESSON);
    }

    public static LessonBook getTypicalLessonBook() {
        LessonBook lb = new LessonBook();

        for (Lesson l : getTypicalLessons()) {
            lb.addLesson(l);
        }

        return lb;
    }

}
