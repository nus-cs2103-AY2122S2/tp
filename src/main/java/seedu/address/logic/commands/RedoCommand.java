package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


/**
 * Redo the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo Command";
    public static final String MESSAGE_FAILURE = "There is no more command to redo";



    private ReadOnlyAddressBook<Person> previousAddressBook;


    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory,
                                 StackUndoRedo undoRedoStack) throws CommandException {
        requireAllNonNull(model, undoRedoStack);

        if (!undoRedoStack.canRedo()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        previousAddressBook = new AddressBook(model.getAddressBook());

        UndoableCommand toRedoCommand = undoRedoStack.popRedo();
        toRedoCommand.redo(model);

        Model oldModel = new ModelManager(previousAddressBook, new UserPrefs());
        toRedoCommand.save(oldModel);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toRedoCommand.getSuccessMessage()));
    }

}
