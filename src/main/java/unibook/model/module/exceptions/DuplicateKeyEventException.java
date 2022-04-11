package unibook.model.module.exceptions;

public class DuplicateKeyEventException extends RuntimeException {
    public DuplicateKeyEventException() {
        super("Operation would result in a duplicate key event");
    }

}
