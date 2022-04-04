package seedu.address.model.interview.exceptions;

public class NonPassableInterviewException extends RuntimeException {
    public NonPassableInterviewException() {
        super("Operation would result in number of extended offers greater than number of position openings");
    }
}
