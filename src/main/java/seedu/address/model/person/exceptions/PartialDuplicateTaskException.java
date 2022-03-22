package seedu.address.model.person.exceptions;

public class PartialDuplicateTaskException extends RuntimeException {
    public PartialDuplicateTaskException() {
        super("Operation would result in duplicate tasks assigned to all students");
    }
}
