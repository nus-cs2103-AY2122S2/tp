package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class BackCommand extends Command {

    public static final String COMMAND_WORD = "back";
    public static final String MESSAGE_SUCCESS = "Returned to all contacts!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, false, false,
                true, false, false, false, null);
    }
}
