package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in marking a task that is already not completed (Task index are considered as
 * not completed if it is not marked as done.).
 */
public class TaskAlreadyNotCompleteException extends RuntimeException {
    public TaskAlreadyNotCompleteException() {
        super("Task is already unmarked!");
    }
}
