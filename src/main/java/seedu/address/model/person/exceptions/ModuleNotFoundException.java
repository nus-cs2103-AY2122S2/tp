package seedu.address.model.person.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("Error 404, module not found!");
    }
}
