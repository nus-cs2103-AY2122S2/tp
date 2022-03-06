package unibook.model.module.exceptions;

/**
 * Signals that the operation will result in duplicate modules (modules are considered duplicates if they have the same
 * module code and name).
 */
public class ModuleNotFoundException extends RuntimeException {
    public ModuleNotFoundException() {
        super("The module is not found in the list.");
    }
}

