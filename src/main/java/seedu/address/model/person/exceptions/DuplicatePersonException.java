package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicatePersonException extends RuntimeException {
    public DuplicatePersonException(String duplicateField) {
        super("Error: Operation would result in persons with same " + duplicateField + ".");
    }

    public DuplicatePersonException() {
        super("ERROR: This operation will result in duplicate persons.");
    }
}
