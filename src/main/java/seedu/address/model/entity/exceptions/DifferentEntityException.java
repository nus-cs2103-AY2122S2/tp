package seedu.address.model.entity.exceptions;

import seedu.address.model.entity.EntityType;

/**
 * Signals that the operation will result in setting one entity type as another entity type.
 */
public class DifferentEntityException extends RuntimeException {
    public DifferentEntityException(EntityType type, EntityType otherType) {
        super(String.format("Attempting to set %s as %s", type.toString(), otherType.toString()));
    }
}
