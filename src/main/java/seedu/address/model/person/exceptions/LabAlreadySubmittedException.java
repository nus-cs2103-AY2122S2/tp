package seedu.address.model.person.exceptions;

public class LabAlreadySubmittedException extends RuntimeException {

    public LabAlreadySubmittedException(int labNumber) {
        super("Lab " + labNumber + " has already been submitted.");
    }
}
