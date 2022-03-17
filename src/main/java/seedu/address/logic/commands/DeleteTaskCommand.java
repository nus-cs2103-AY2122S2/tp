package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from deltask");
    }
}
