package seedu.address.model.lesson.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class LessonNotFoundException extends RuntimeException {
    public LessonNotFoundException() {
        super("Specified lesson could not be found!");
    }
}
