package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in marking a task that is already completed (Task index are considered as
 * completed if it is already marked as done.).
 */

public class TaskAlreadyCompleteException extends RuntimeException {
    public TaskAlreadyCompleteException() {
        super("Task is already complete!");
    }
}
