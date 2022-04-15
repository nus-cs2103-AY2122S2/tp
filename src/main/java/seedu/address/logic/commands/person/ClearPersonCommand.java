package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.EditTaskDescriptor;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.name.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.EndTime;
import seedu.address.model.task.StartTime;
import seedu.address.model.task.Task;

/**
 * Clears the address book.
 */
public class ClearPersonCommand extends Command {

    public static final String COMMAND_WORD = "clear-p";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Task> unfilteredTaskList = model.getUnfilteredTaskList();

        // update tasks after clearing the address book
        for (Task task: unfilteredTaskList) {
            EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
            Set<Name> persons = new HashSet<>();
            editTaskDescriptor.setPersons(persons);
            Task editedTask = createEditedTask(task, editTaskDescriptor);
            model.setTask(task, editedTask);
        }
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(taskToEdit);

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        StartTime updatedStartTime = editTaskDescriptor.getStartTime().orElse(taskToEdit.getStartTime());
        EndTime updatedEndTime = editTaskDescriptor.getEndTime().orElse(taskToEdit.getEndTime());
        Set<Tag> updatedTags = editTaskDescriptor.getTags().orElse(taskToEdit.getTags());
        Set<Name> updatedPersons = editTaskDescriptor.getPersons().orElse(taskToEdit.getPersons());
        return new Task(updatedName, updatedDate,
                updatedStartTime, updatedEndTime, updatedTags, updatedPersons);
    }
}
