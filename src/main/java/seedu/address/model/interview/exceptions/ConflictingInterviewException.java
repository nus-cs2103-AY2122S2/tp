package seedu.address.model.interview.exceptions;

public class ConflictingInterviewException extends RuntimeException {
    public ConflictingInterviewException() {
        super("Operation would result in conflicting interviews at the same time slot"); }
}
