package seedu.address.model.position.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class PositionNotFoundException extends RuntimeException {
    public PositionNotFoundException() {
        super("Position is not found in the position list");
    }
}
