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
 * Undo the previous {@code UndoableCommand}.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE_SUCCESS = "Successfully Undo";
    public static final String MESSAGE_USAGE_FAILURE = "There is no more command left in stack to undo";


    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory,
                                 StackUndoRedo undoRedoStack) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        //Check if whether there is a command exist in the stack
        if (undoRedoStack.canUndo()) {
            ReadOnlyAddressBook previousAddressBook = new AddressBook(model.getAddressBook());
            Model prevModel = new ModelManager(previousAddressBook, new UserPrefs());

            RedoableCommand undoCommand = undoRedoStack.popUndo();
            undoCommand.undo(model);

            undoCommand.save(prevModel);
            return new CommandResult(String.format(MESSAGE_USAGE_SUCCESS, undoCommand.getSuccessMessage()));
        }

        throw new CommandException(MESSAGE_USAGE_FAILURE);
    }

}
