package seedu.address.logic.commands.person.notecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;

/**
 * Delete a weakness from a person in the address book
 */
public class DeleteWeaknessCommand extends Command {
    public static final String COMMAND_WORD = "weakness-del";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a weakness from the weakness-list of a selected person from our contact list. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "NOTE-INDEX (must be a positive)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "2";

    public static final String MESSAGE_SUCCESS = "Weakness has been deleted: %1$s";

    private final Index index;
    private final Index noteIndex;

    /**
     * Constructor of DeleteWeaknessCommand class
     * @param index index of the person in the filtered person list
     * @param noteIndex index of the weakness from the person's weakness-list to be deleted
     */
    public DeleteWeaknessCommand(Index index, Index noteIndex) {
        requireAllNonNull(index, noteIndex);
        this.index = index;
        this.noteIndex = noteIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<Note> newWeaknesses = new ArrayList<>(personToEdit.getWeaknesses());

        if (noteIndex.getZeroBased() >= newWeaknesses.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }
        newWeaknesses.remove(noteIndex.getZeroBased());

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getStrengths(), newWeaknesses, personToEdit.getMiscellaneous());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteWeaknessCommand
                && index.equals(((DeleteWeaknessCommand) other).index)
                && noteIndex.equals(((DeleteWeaknessCommand) other).noteIndex));
    }
}
