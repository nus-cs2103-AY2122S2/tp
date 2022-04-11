package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";

    public RedoCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Redid command", false, false);
    }

    @Override
    public CommandResult unExecute(Model model) throws CommandException {
        throw new CommandException("Redo command should not be un-executed.");
    }

    @Override
    public boolean isRedo() {
        return true;
    }
}
