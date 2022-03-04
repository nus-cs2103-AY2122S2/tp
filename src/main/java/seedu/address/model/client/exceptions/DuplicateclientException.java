package seedu.address.model.client.exceptions;

/**
 * Signals that the operation will result in duplicate clients (clients are considered duplicates if they have the same
 * identity).
 */
public class DuplicateclientException extends RuntimeException {
    public DuplicateclientException() {
        super("Operation would result in duplicate clients");
    }
}
