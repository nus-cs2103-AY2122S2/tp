package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Adds a student group to ArchDuke.
 */
public class AddGroupCommand extends Command {

    public static final String COMMAND_WORD = "addgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student group to ArchDuke. "
            + "Parameters: "
            + "g/" + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + "g/" + "CS2103-W16-3";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addgroup command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Group name: %s";

    private final String toAdd;

    /**
     * Creates an AddGroupCommand to add the specified {@code Group}
     *
     * @param groupName of the group to be added to ArchDuke.
     */
    public AddGroupCommand(String groupName) {
        requireAllNonNull(groupName);
        toAdd = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddGroupCommand)) {
            return false;
        }

        // state check
        AddGroupCommand e = (AddGroupCommand) other;
        return toAdd.equals(e.toAdd);
    }
}
