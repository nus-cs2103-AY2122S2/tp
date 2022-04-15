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
 * Adds a miscellaneous note to a person in the address book.
 */
public class AddMiscCommand extends Command {
    public static final String COMMAND_WORD = "misc-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a miscellaneous note to a selected person from our contact list. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + "NOTE_DESCRIPTION (must be non-empty and not more than 50 characters)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + "Surgery Scheduled for tomorrow";

    public static final String MESSAGE_SUCCESS = "New miscellaneous note added: %1$s";

    private final Index index;
    private final Note misc;

    /**
     * @param index of the person in the filtered person list to add the misc
     * @param misc note of the person to be added
     */
    public AddMiscCommand(Index index, Note misc) {
        requireAllNonNull(index, misc);

        this.index = index;
        this.misc = misc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        List<Note> newMiscList = new ArrayList<>(personToEdit.getMiscellaneous());

        if (newMiscList.contains(misc)) { //makes sure note does not already exist for given person
            throw new CommandException(Messages.MESSAGE_DUPLICATE_MISC);
        }

        newMiscList.add(misc);

        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getStrengths(),
                personToEdit.getWeaknesses(), newMiscList);

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
        return other == this // short circuit if same object
                || (other instanceof AddMiscCommand // instanceof handles nulls
                && (index.equals(((AddMiscCommand) other).index)
                    && misc.equals(((AddMiscCommand) other).misc)));
    }
}
