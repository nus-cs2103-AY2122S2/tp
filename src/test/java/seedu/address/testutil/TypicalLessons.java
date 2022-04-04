package seedu.address.testutil;

import seedu.address.model.lesson.Lesson;

/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    public static final Lesson TEMPORARY_LESSON = new TemporaryLessonBuilder().build();
    public static final Lesson RECURRING_LESSON = new RecurringLessonBuilder().build();

    private TypicalLessons() {} // prevents instantiation

}
