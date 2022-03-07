package seedu.address.model.entity.exceptions;

/**
 * Signals that the entity is unknown (not listed inside {@code EntityType} enum)
 */
public class UnknownEntityException extends RuntimeException {
    public UnknownEntityException() {
        super("The entity type does not exist.");
    }
}
