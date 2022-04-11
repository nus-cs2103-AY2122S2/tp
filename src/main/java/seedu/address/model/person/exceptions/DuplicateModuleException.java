package seedu.address.model.person.exceptions;

public class DuplicateModuleException extends RuntimeException {
    public DuplicateModuleException() {
        super("Operation would result in duplicate modules");
    }
}
