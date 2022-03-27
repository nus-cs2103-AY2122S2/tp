package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redo the last undone command and restores that version of AddressBook.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Redo the last command that changed HackNet.\n";

    public static final String MESSAGE_REDO_SUCCESS = "Last Command restored!";
    public static final String MESSAGE_REDO_FAILED = "No more commands left to redo!";

    public RedoCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.canRedoAddressBook()) {
            model.redoAddressBook();
            return new CommandResult(MESSAGE_REDO_SUCCESS);
        }

        throw new CommandException(MESSAGE_REDO_FAILED);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RedoCommand); // instanceof handles nulls
    }
}
