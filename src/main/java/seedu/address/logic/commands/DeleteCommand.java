package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Displayable;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_INDEX_USAGE = "Try listing a person e.g. list";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Displayable> lastShownList = model.getLastShownList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (!(lastShownList.get(targetIndex.getZeroBased()) instanceof Person)) {
            throw new CommandException(Messages.MESSAGE_INDEX_LIST_MISMATCH + MESSAGE_INDEX_USAGE);
        }

        if (lastShownList.get(targetIndex.getZeroBased()) instanceof Student) {
            Student studentToDelete = (Student) lastShownList.get(targetIndex.getZeroBased());
            model.removeStudentResults(studentToDelete.getStudentId(), studentToDelete.getTutorialName());
            model.removeStudent(studentToDelete);
            Person personToDelete = model.getPersonWithName(studentToDelete.getName());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        } else {
            Person personToDelete = (Person) lastShownList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
