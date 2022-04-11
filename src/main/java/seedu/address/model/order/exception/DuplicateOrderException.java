package seedu.address.model.order.exception;

/**
 * Signals that the operation will result in duplicate Orders (Orders are considered duplicates if they have the same
 * order number).
 */
public class DuplicateOrderException extends RuntimeException {
    public DuplicateOrderException() {
        super("Operation would result in duplicate orders");
    }
}