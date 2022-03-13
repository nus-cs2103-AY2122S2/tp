package seedu.address.model.person.exceptions;

public class LabNotSubmittedException extends RuntimeException {

    public LabNotSubmittedException(int labNumber) {
        super("Lab " + labNumber + " has not yet been submitted.");
    }
}
