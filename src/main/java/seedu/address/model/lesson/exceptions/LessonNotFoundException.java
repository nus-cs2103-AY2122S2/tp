package seedu.address.model.lesson.exceptions;

/**
 * Signals that the operation is unable to find the specified student.
 */
public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
        super("Specified lesson could not be found!");
    }
}
