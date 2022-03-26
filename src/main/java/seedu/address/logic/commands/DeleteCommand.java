package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list "
            + "or matriculation number.\n"
            + "Parameters: INDEX (must be a positive integer) or STUDENT_ID\n"
            + "Example: " + COMMAND_WORD + " 1"
            + " or " + COMMAND_WORD + " " + PREFIX_ID + "A0123456Z\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS = "%s students deleted";

    private final Index[] targetIndices;
    private final StudentId targetId;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person} using the specified index.
     */
    public DeleteCommand(Index[] targetIndices) {
        this.targetIndices = targetIndices;
        this.targetId = null;
    }

    /**
     * Creates a DeleteCommand to delete the specified {@code Person} using the specified student id.
     */
    public DeleteCommand(StudentId targetId) {
        this.targetId = targetId;
        this.targetIndices = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (targetIndices != null) { // indices were used for the command
            int numberOfDeletions = 0;
            ArrayList<Person> personsToDelete = new ArrayList<>();
            for (Index currIndex : targetIndices) {
                if (currIndex.getZeroBased() >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
                Person personToDelete = lastShownList.get(currIndex.getZeroBased());
                personsToDelete.add(personToDelete);
            }
            for (Person person : personsToDelete) {
                model.deletePerson(person);
                numberOfDeletions++;
            }
            if (numberOfDeletions == 1) {
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personsToDelete.get(0)));
            } else {
                return new CommandResult(String.format(MESSAGE_DELETE_MULTIPLE_PERSONS_SUCCESS, numberOfDeletions));
            }
        } else { // student id was used for the command
            assert targetId != null;
            StudentIdContainsKeywordsPredicate pred =
                    new StudentIdContainsKeywordsPredicate(Collections.singletonList(targetId.toString()));
            model.updateFilteredPersonList(pred);
            if (model.getFilteredPersonList().size() > 0) { // person with specified id exists
                Person personToDelete = lastShownList.get(Index.fromZeroBased(0).getZeroBased());
                model.deletePerson(personToDelete);
                model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            } else {
                throw new CommandException(Messages.MESSAGE_NONEXISTENT_STUDENTID);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) { // short circuit if same object
            return true;
        } else {
            boolean isInstanceOf = other instanceof DeleteCommand;
            if (!isInstanceOf) { // instanceof handles nulls
                return false;
            }
            DeleteCommand commandToCompare = (DeleteCommand) other;
            if (this.targetId == null && this.targetIndices != null) { // only targetIndices present
                return Arrays.equals(targetIndices, commandToCompare.targetIndices); // state check
            } else if (this.targetIndices == null && this.targetId != null) { // only targetId present
                return targetId.equals(commandToCompare.targetId); // state check
            } else {
                return false;
            }
        }
    }
}
