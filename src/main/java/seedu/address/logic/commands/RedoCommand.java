package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the data of the HustleBook to the recent state.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo the last command \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_REDO_SUCCESS = "Redo Successful!";
    public static final String MESSAGE_NO_REDO = "There are no commands to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.redoHustleBook();
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_NO_REDO);
        }
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }
}
