package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;


/**
 * Redo the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_USAGE_SUCCESS = "Successfully Redo";
    public static final String MESSAGE_USAGE_FAILURE = "No command left to redo";

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory,
                                 StackUndoRedo undoRedoStack) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (undoRedoStack.canRedo()) {

            ReadOnlyAddressBook previousAddressBook = new AddressBook(model.getAddressBook());
            Model oldModel = new ModelManager(previousAddressBook, new UserPrefs());

            RedoableCommand redoCommand = undoRedoStack.popRedo();
            redoCommand.redo(model);

            redoCommand.save(oldModel);

            return new CommandResult(String.format(MESSAGE_USAGE_SUCCESS, redoCommand.getSuccessMessage()));
        }

        throw new CommandException(MESSAGE_USAGE_FAILURE);
    }

}
