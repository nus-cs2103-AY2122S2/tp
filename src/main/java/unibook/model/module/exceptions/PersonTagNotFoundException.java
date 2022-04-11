package unibook.model.module.exceptions;

/**
 * Signals that the operation will result in duplicate modules (modules are considered duplicates if they have the same
 * module code and name).
 */
public class PersonTagNotFoundException extends RuntimeException {
    public PersonTagNotFoundException() {
        super("Person should have professor/student tag.");
    }
}

