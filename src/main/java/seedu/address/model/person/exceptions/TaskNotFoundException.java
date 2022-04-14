package seedu.address.model.person.exceptions;

/**
 * Signals that task with the name given by user does not exist.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super("Task with given name does not exist!");
    }
}
