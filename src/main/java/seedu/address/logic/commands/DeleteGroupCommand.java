package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a student group from ArchDuke.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "delgroup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task from a group."
            + "Parameters: "
            + PREFIX_GROUP_NAME + "TASK_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "CS2103-W16-3 \n";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from delgroup command");
    }
}
