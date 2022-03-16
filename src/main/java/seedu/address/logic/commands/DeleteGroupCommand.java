package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a student group from ArchDuke.
 */
public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "delgroup";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from delgroup command");
    }
}
