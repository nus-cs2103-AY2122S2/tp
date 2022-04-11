package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Person;

public class DeleteDeadlineCommand extends Command implements DetailedViewExecutable {

    public static final String COMMAND_WORD = "deldl";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the deadline identified by its index number in the list of"
            + " deadlines of this person.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_DEADLINE_SUCCESS = "Deleted deadline";

    private final Index index;

    /**
     * @param index index of the deadline in the list to delete.
     */
    public DeleteDeadlineCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(Messages.MESSAGE_INCOMPATIBLE_VIEW_MODE);
    }

    @Override
    public CommandResult executeInDetailedView(Model model) throws CommandException {
        requireNonNull(model);
        Person personToEdit = model.getDetailedContactViewPerson();
        if (index.getZeroBased() >= personToEdit.getDeadlines().size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        DeadlineList newDeadlineList = personToEdit.getDeadlines().delete(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), newDeadlineList, personToEdit.getNotes(),
                personToEdit.getTags(), personToEdit.getFavouriteStatus(),
                personToEdit.getHighImportanceStatus(), personToEdit.getImageDetailsList());

        model.setPerson(personToEdit, editedPerson);
        model.setDetailedContactView(editedPerson);
        return new CommandResult(MESSAGE_DELETE_DEADLINE_SUCCESS,
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }
}
