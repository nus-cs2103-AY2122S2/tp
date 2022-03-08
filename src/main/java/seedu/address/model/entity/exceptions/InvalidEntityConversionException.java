package seedu.address.model.entity.exceptions;

import seedu.address.model.entity.EntityType;

/**
 * Signals that the operation will result in invalid entity conversion.
 */
public class InvalidEntityConversionException extends RuntimeException {
    public InvalidEntityConversionException(EntityType expectedType) {
        super("Invalid conversion of Entity to " + expectedType.toString());
    }
}
