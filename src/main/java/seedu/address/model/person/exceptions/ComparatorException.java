package seedu.address.model.person.exceptions;

/**
 * Signals that there has been an error when comparing objects
 */
public class ComparatorException extends RuntimeException {
    public ComparatorException() {
        super("Objects could not be compared");
    }
}
