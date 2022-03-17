package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes an existing task from ArchDuke.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deltask";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from deltask");
    }
}
