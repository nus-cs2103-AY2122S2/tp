package seedu.address.model.student.exceptions;

public class LabNotFoundException extends RuntimeException {

    public LabNotFoundException(int target) {
        super("Lab " + target + " does not exist yet.");
    }
}
