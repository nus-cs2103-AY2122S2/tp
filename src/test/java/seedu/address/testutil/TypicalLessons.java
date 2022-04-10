package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.lesson.Lesson;

//@@author jxt00
/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    public static final int TOTAL_LESSONS = 6;
    public static final Lesson LESSON1 = getLesson(0);
    public static final Lesson LESSON2 = getLesson(1);
    public static final Lesson LESSON3 = getLesson(2);
    public static final Lesson LESSON11 = getLesson(3);
    public static final Lesson LESSON12 = getLesson(4);
    public static final Lesson LESSON13 = getLesson(5);

    private TypicalLessons() {} // prevents instantiation

    public static List<Lesson> getTypicalLessons() {
        List<Lesson> lessonList = new ArrayList<>();
        for (int i = 0; i < TOTAL_LESSONS; i++) {
            lessonList.add(getLesson(i));
        }
        return lessonList;
    }

    public static Lesson getLesson(int i) {
        switch (i) {
        case 0:
            return new LessonBuilder()
                    .withStudentAttendances(TypicalStudentAttendances.ALICE_ATTENDANCE)
                    .withWeekId("1").build();
        case 1:
            return new LessonBuilder()
                    .withStudentAttendances(TypicalStudentAttendances.BENSON_ATTENDANCE)
                    .withWeekId("2").build();
        case 2:
            return new LessonBuilder()
                .withStudentAttendances(TypicalStudentAttendances.CARL_ATTENDANCE)
                .withWeekId("3").build();
        case 3:
            return new LessonBuilder()
                    .withStudentAttendances(TypicalStudentAttendances.DANIEL_ATTENDANCE)
                    .withWeekId("11").build();
        case 4:
            return new LessonBuilder()
                    .withStudentAttendances(TypicalStudentAttendances.CARL_ATTENDANCE,
                            TypicalStudentAttendances.DANIEL_ATTENDANCE)
                    .withWeekId("12").build();
        case 5:
            return new LessonBuilder()
                    .withStudentAttendances(TypicalStudentAttendances.ELLE_ATTENDANCE,
                            TypicalStudentAttendances.FIONA_ATTENDANCE)
                    .withWeekId("13").build();
        default:
            return new LessonBuilder().build();
        }
    }
}
