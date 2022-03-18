package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Views tasks in the specific group in ArchDuke.
 */
public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewtask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views tasks in a specified group in ArchDuke. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "CS2103-W16-3";

    public static final String MESSAGE_ARGUMENTS = "View task in group %1$s";

    private final Group group;

    /**
     * Constructs a {@code ViewTaskCommand} with the specified field.
     *
     * @param group The group in which the tasks would be viewed.
     */
    public ViewTaskCommand(Group group) {
        requireNonNull(group);

        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, group));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles null
        if (!(other instanceof ViewTaskCommand)) {
            return false;
        }

        // state check
        ViewTaskCommand e = (ViewTaskCommand) other;
        return group.equals(e.group);
    }
}
