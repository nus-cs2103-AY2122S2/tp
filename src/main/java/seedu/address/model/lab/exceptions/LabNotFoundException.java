package seedu.address.model.lab.exceptions;

public class LabNotFoundException extends RuntimeException {

    public LabNotFoundException(int target) {
        super("Lab " + target + " does not exist yet.");
    }
}
