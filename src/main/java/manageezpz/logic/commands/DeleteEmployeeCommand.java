package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import manageezpz.commons.core.Messages;
import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Person;
import manageezpz.model.task.Task;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteEmployeeCommand extends Command {

    public static final String COMMAND_WORD = "deleteEmployee";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Index targetIndex;

    public DeleteEmployeeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownPersonList = model.getFilteredPersonList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownPersonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownPersonList.get(targetIndex.getZeroBased());
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < lastShownTaskList.size(); i++) {
            Task currentTask = lastShownTaskList.get(i);
            List<Person> assigneeList = currentTask.getAssignees();
            for(int j = 0; j < assigneeList.size(); j++) {
                if (assigneeList.get(j).equals(personToDelete)) {
                    taskList.add(currentTask);
                }
            }
        }

        for (int j = 0; j < taskList.size(); j++) {
            model.untagTask(taskList.get(j), personToDelete);
        }
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEmployeeCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteEmployeeCommand) other).targetIndex)); // state check
    }
}
