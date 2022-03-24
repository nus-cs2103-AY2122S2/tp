package unibook.model.module.exceptions;

/**
 * Signals that a group does not exist in the list of groups in a module.
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("Group not found in list of groups of module");
    }
}
