package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Deletes a student group from ArchDuke.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "delgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a group from ArchDuke."
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "CS2103-W16-3 \n";

    public static final String MESSAGE_NON_EXISTENT_GROUP = "This group does not exist in the list";

    public static final String MESSAGE_DELETE_GROUP_SUCCESS = "Deleted group: %1$s";

    private final Group group;

    /**
     * Creates a DeleteGroupCommand to delete the specified {@code Group}
     */
    public DeleteGroupCommand(Group group) {
        requireNonNull(group);
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownList = model.getFilteredGroupList();
        assert lastShownList != null : "lastShownList should not be null";

        if (!lastShownList.contains(group)) {
            throw new CommandException(MESSAGE_NON_EXISTENT_GROUP);
        }

        Group groupToDelete = group;
        model.deleteGroup(groupToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GROUP_SUCCESS, groupToDelete));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof DeleteGroupCommand)) {
            return false;
        }

        // state check
        DeleteGroupCommand e = (DeleteGroupCommand) other;
        return group.equals(e.group);
    }
}
