package seedu.address.model.job.exception;

/**
 * Signals that the operation will result in duplicate Jobs
 * (Jobs are considered duplicates if they have the same Company Name and Job Title).
 */
public class DuplicateJobException extends RuntimeException {
    public DuplicateJobException() {
        super("Operation would result in duplicate jobs");
    }
}
