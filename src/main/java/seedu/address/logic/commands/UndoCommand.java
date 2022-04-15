package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the previous command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Erases the previous change to the address book and restores the older state.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_UNDO_SUCCESS = "Undid last change.";
    public static final String MESSAGE_UNDO_FAILURE = "No previous changes.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.checkHistory() == false) {
            throw new CommandException(MESSAGE_UNDO_FAILURE);
        }

        model.restoreHistory();

        return new CommandResult(MESSAGE_UNDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand); // instanceof handles nulls
    }
}
