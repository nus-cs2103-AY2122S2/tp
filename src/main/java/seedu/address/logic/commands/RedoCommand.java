package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverses an executed Undo command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Erases the previous change by undo to the address book and restores the original state, "
            + "only can be used after a undo command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REDO_SUCCESS = "Reverted to original.";
    public static final String MESSAGE_REDO_FAILURE = "You have to use the undo command first.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // this will check if the previous command was undo
        if (model.checkOriginal() == false) {
            throw new CommandException(MESSAGE_REDO_FAILURE);
        }

        model.restoreOriginal();
        // clear original to avoid multiple redo
        return new CommandResult(MESSAGE_REDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand); // instanceof handles nulls
    }
}
