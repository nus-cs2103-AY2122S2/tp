package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;

public class DeleteNoteCommand extends Command implements DetailedViewExecutable {
    public static final String COMMAND_WORD = "delnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the note identified by its index number in the list of"
            + " notes of this person.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted note";

    private final Index index;

    /**
     * @param index index of the note in the notes list to delete.
     */
    public DeleteNoteCommand(Index index) {
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
        if (index.getZeroBased() >= personToEdit.getNotes().value.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_OUT_OF_BOUND);
        }
        Person editedPerson = updateNotes(personToEdit, index.getZeroBased());

        model.setPerson(personToEdit, editedPerson);
        model.setDetailedContactView(editedPerson);
        return new CommandResult(MESSAGE_DELETE_NOTE_SUCCESS,
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }

    private static Person updateNotes(Person personToEdit, int index) {
        Notes oldNotes = personToEdit.getNotes();
        Notes newNotes = oldNotes.delete(index);
        return new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getDeadlines(), newNotes,
                personToEdit.getTags(), personToEdit.getFavouriteStatus(), personToEdit.getHighImportanceStatus(),
                personToEdit.getImageDetailsList());
    }
}
