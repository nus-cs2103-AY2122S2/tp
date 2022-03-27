package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undo the last command entered and restores that version of AddressBook.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the last command and revert to previous version of HackNet.\n";

    public static final String MESSAGE_UNDO_SUCCESS = "Last Command undone";
    public static final String MESSAGE_UNDO_FAILED = "Already at last command, no commands left to undo!";

    public UndoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.canUndoAddressBook()) {
            model.undoAddressBook();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        }

        throw new CommandException(MESSAGE_UNDO_FAILED);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UndoCommand); // instanceof handles nulls
    }
}
