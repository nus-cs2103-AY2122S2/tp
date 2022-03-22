package seedu.address.model.person.exceptions;


public class ModuleCodeNotFoundException extends RuntimeException {
    public ModuleCodeNotFoundException() {
        super("There is no one in the list with this module code!");
    }
}
