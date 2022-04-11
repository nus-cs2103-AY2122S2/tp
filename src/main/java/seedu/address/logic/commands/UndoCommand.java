package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the data of the HustleBook to the previous state.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the last command \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_UNDO_SUCCESS = "Undo Successful!";
    public static final String MESSAGE_NO_UNDO = "There are no commands to undo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.undoHustleBook();
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_NO_UNDO);
        }
        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }
}
