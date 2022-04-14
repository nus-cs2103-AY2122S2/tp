package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the effect of the most recently executed command (except another {@code UndoCommand}).
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_NO_COMMAND_TO_UNDO = "There are no commands to undo.";
    public static final String MESSAGE_SUCCESS = "Command undone:\n\"%1$s\"";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isCommandHistoryEmpty() || model.getCommandHistory() == null) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_UNDO);
        }
        String previousCommand = model.getPreviousCommandText();

        if (model.isAddressBookHistoryEmpty() || model.getAddressBookHistory() == null) {
            throw new CommandException(MESSAGE_NO_COMMAND_TO_UNDO);
        }

        model.undoAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, previousCommand),
                false, false, false, true);
    }
}
