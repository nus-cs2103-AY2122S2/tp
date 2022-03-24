package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;


public abstract class UndoableCommand extends Command {
    private ReadOnlyAddressBook<Person> previousPersonBook;

    private String successMessage = "";

    protected abstract CommandResult executeUndoableCommand(Model model, CommandHistory commandHistory,
                                                            StackUndoRedo undoRedoStack) throws CommandException;

    private void saveAddressBookSnapshot(Model model) {
        requireNonNull(model);
        this.previousPersonBook = new AddressBook(model.getAddressBook());
    }

    protected final void undo(Model model) {
        requireAllNonNull(model, previousPersonBook);

        model.setAddressBook(previousPersonBook);

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
    }

    protected final void redo(Model model) {
        requireNonNull(model);

        undo(model);
    }


    /**
     * Saves state of model.
     */
    public void save(Model model) {
        saveAddressBookSnapshot(model);
    }

    public void saveSuccessMessage(String feedbackToUser) {
        this.successMessage = feedbackToUser;
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory,
                                 StackUndoRedo undoRedoStack) throws CommandException {

        saveAddressBookSnapshot(model);

        return executeUndoableCommand(model, commandHistory, undoRedoStack);
    }

}
