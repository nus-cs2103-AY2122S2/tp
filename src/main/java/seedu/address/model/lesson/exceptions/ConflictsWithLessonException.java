package seedu.address.model.lesson.exceptions;

import seedu.address.model.lesson.Lesson;

import java.util.List;

/**
 * Signals that the operation will result in two lessons with conflicting timeslots.
 */
public class ConflictsWithLessonException extends RuntimeException {
    public static final String ERROR_MESSAGE_FIRST_PART = "This lesson:";
    public static final String ERROR_MESSAGE_SECOND_PART = "clashes with the following lesson in your schedule:";
    public static final String PADDING = "   -> ";

    private final Lesson lessonToAdd;
    private final Lesson conflictingLesson;

    /**
     * Creates an exception specifying which two lessons would conflict with each other.
     * @param lessonToAdd
     * @param conflictingLesson
     */
    public ConflictsWithLessonException(Lesson lessonToAdd, Lesson conflictingLesson) {
        super();

        this.lessonToAdd = lessonToAdd;
        this.conflictingLesson = conflictingLesson;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();

        message.append(ERROR_MESSAGE_FIRST_PART)
                .append(System.getProperty("line.separator"))
                .append(PADDING).append(lessonToAdd.toString())
                .append(System.getProperty("line.separator"))
                .append(ERROR_MESSAGE_SECOND_PART)
                .append(System.getProperty("line.separator"))
                .append(PADDING).append(lessonToAdd.toString());

        return message.toString();
    }
}
