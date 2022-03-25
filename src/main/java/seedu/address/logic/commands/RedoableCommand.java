package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * This class enables command to be able to undo / redo
 */
public abstract class RedoableCommand extends Command {
    private ReadOnlyAddressBook previousPersonBook;

    private String message = "";

    protected abstract CommandResult executeUndoableCommand(Model model, CommandHistory commandHistory,
                                                            StackUndoRedo undoRedoStack) throws CommandException;

    /**
     * Save the snapshot of the address book
     * @param model
     */
    private void saveAddressBookSnapshot(Model model) {
        requireNonNull(model);
        this.previousPersonBook = new AddressBook(model.getAddressBook());
    }


    /**
     *
     * @param command
     * Redo the command
     */
    protected final void redo(Model model) {
        requireNonNull(model);
        undo(model);
    }


    /**
     * Undo the command.
     */
    protected final void undo(Model model) {
        requireAllNonNull(model, previousPersonBook);

        model.setAddressBook(previousPersonBook);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }


    /**
     * Saves state of model.
     */
    public void save(Model model) {
        saveAddressBookSnapshot(model);
    }

    /**
     * Print successful message
     * @param feedbackToUser
     */
    public void saveSuccessMessage(String feedbackToUser) {
        this.message = feedbackToUser;
    }

    /**
     * Get successful message
     * @return
     */
    public String getSuccessMessage() {
        return this.message;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory,
                                 StackUndoRedo undoRedoStack) throws CommandException {

        saveAddressBookSnapshot(model);

        return executeUndoableCommand(model, commandHistory, undoRedoStack);
    }

}
