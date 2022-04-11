package seedu.address.model.expense.exceptions;

/**
 * Signals that the operation will result in duplicate Expenses Category
 * (Expenses Categories are considered duplicates if
 * they have the same identity).
 */
public class DuplicateExpenseCategoryException extends RuntimeException {
    public DuplicateExpenseCategoryException() {
        super("Operation would result in duplicate expense Category");
    }
}
