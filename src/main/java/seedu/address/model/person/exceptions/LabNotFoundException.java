package seedu.address.model.person.exceptions;

public class LabNotFoundException extends RuntimeException {

    public LabNotFoundException(int target) {
        super("Lab " + target + " does not exist yet.");
    }
}
