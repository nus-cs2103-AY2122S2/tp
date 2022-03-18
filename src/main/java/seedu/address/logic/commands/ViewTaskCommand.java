package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views tasks in the specific group in ArchDuke.
 */
public class ViewTaskCommand extends Command {

    public static final String COMMAND_WORD = "viewtask";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from viewtask");
    }
}
