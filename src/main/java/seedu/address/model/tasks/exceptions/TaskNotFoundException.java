package seedu.address.model.tasks.exceptions;

/**
 * Signals that the operation is unable to find the specified task.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Error 404, task not found!");
    }
}
