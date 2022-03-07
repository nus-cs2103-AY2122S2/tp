package seedu.address.model.person.exceptions;

public class LabAlreadyGradedException extends RuntimeException {

    public LabAlreadyGradedException(int labNumber) {
        super("Lab " + labNumber + " has already been graded.");
    }
}
