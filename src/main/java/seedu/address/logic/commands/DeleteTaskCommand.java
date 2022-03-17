package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * Deletes an existing task from ArchDuke.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deltask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing task from an existing group."
            + "Parameters: "
            + PREFIX_TASK + "TASK_NAME "
            + PREFIX_GROUP_NAME + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + "v1.2 user guide "
            + PREFIX_GROUP_NAME + "CS2103-W16-3 \n";

    public static final String MESSAGE_ARGUMENTS = "Task: %1$s, Group: %2$s";

    private final Task task;
    private final Group group;

    public DeleteTaskCommand(Task task, Group group) {
        requireAllNonNull(task, group);

        this.task = task;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, task, group));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return task.equals(e.task) && group.equals(e.group);
    }
}
