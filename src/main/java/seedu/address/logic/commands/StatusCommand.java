package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Changes the status of an existing person in the address book.
 */
public class StatusCommand extends RedoableCommand {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_ADD_STATUS_SUCCESS = "Added status to Person: %1$s";
    public static final String MESSAGE_DELETE_STATUS_SUCCESS = "Removed status from Person: %1$s";
    public static final String MESSAGE_ADD_STATUS_FAILURE = "Modules should be either 'blacklist' or 'favourite'";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the status of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing status will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_STATUS + "[STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STATUS + "blacklist";

    private final Index index;
    private final Status status;

    /**
     * @param index of the person in the filtered person list to edit the status
     * @param status of the person to be updated to
     */
    public StatusCommand(Index index, Status status) {
        requireAllNonNull(index, status);

        this.index = index;
        this.status = status;
    }

    /**
     * Generates a command execution success message based on whether the status is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !status.value.isEmpty() ? MESSAGE_ADD_STATUS_SUCCESS : MESSAGE_DELETE_STATUS_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusCommand)) {
            return false;
        }

        // state check
        StatusCommand e = (StatusCommand) other;
        return index.equals(e.index)
                && status.equals(e.status);
    }

    @Override
    protected CommandResult executeUndoableCommand(Model model,
                                                   CommandHistory commandHistory,
                                                   StackUndoRedo undoRedoStack) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), status, personToEdit.getModules(), personToEdit.getComment());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }
}
