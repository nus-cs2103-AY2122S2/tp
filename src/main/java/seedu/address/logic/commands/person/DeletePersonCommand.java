package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.EditTaskDescriptor;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "del-p";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> unfilteredTaskList = model.getUnfilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());

        // update tasks after the deletion of person
        for (Task task: unfilteredTaskList) {
            if (task.getPersons().contains(personToDelete.getName())) {
                EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
                Set<Name> persons = new HashSet<>(task.getPersons());
                persons.remove(personToDelete.getName());
                editTaskDescriptor.setPersons(persons);
                Task editedTask = createEditedTask(task, editTaskDescriptor);
                model.setTask(task, editedTask);
            }
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        StartTime updatedStartTime = editTaskDescriptor.getStartTime().orElse(taskToEdit.getStartTime());
        EndTime updatedEndTime = editTaskDescriptor.getEndTime().orElse(taskToEdit.getEndTime());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        Set<Name> updatedPersons = editTaskDescriptor.getPersons().orElse(taskToEdit.getPersons());
        return new Task(updatedName, updatedDate,
                updatedStartTime, updatedEndTime, updatedTags, updatedPersons);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePersonCommand) other).targetIndex)); // state check
    }
}
