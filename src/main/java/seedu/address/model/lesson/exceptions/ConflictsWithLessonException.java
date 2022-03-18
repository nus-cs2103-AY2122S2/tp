package seedu.address.model.lesson.exceptions;

import seedu.address.model.lesson.Lesson;

/**
 * Signals that the operation will result in two lessons with conflicting timeslots.
 */
public class ConflictsWithLessonException extends RuntimeException {
    public static final String ERROR_MESSAGE = "Cannot add this lesson as it conflicts with this"
            + " lesson in your schedule:";

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

        message.append(ERROR_MESSAGE)
                .append(System.getProperty("line.separator")).append(System.getProperty("line.separator"))
                .append(conflictingLesson.toString());

        return message.toString();
    }
}
