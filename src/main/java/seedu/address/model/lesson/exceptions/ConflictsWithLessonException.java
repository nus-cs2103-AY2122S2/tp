package seedu.address.model.lesson.exceptions;

import seedu.address.model.lesson.Lesson;

/**
 * Signals that the operation will result in two lessons with conflicting timeslots.
 */
public class ConflictsWithLessonException extends RuntimeException {
    /**
     * Creates an exception specifying which two lessons would conflict with each other.
     * @param firstLesson
     * @param secondLesson
     */
    public ConflictsWithLessonException(Lesson firstLesson, Lesson secondLesson) {
        super(
                String.format("Operation would result in two lessons with clashing timeslots:\n   %s\n   %s",
                        firstLesson, secondLesson)
        );
    }
}
