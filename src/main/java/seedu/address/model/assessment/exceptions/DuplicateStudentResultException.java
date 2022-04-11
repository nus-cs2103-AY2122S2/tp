package seedu.address.model.assessment.exceptions;

public class DuplicateStudentResultException extends RuntimeException {
    public DuplicateStudentResultException() {
        super("Operation would result in a repeated entry of the student's result for this assessment");
    }
}
