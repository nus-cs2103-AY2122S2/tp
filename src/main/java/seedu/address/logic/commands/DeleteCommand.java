package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MassOpsParser;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Deletes a task identified using it's displayed index from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tasks at the given indexes of the displayed task list.\n"
            + "Parameters: INDEX... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    private final List<Index> targetIndexes;

    public DeleteCommand(List<Index> targetIndexes) {
        this.targetIndexes = MassOpsParser.sortInDesc(targetIndexes);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        List<Task> lastShownList = model.getSortedTaskList();
        List<Index> deletedTaskIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();

        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);
            if (!isValidIndex(index, lastShownList)) {
                outOfBoundsIndexes.add(index);
            } else {
                deletedTaskIndexes.add(index);
            }
        }
        return result(model, lastShownList, deletedTaskIndexes, outOfBoundsIndexes);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndexes.equals(((DeleteCommand) other).targetIndexes)); // state check
    }

    /**
     * Converts the list of successfully deleted tasks into a string to be returned to the user
     *
     * @param deletedTasks a {@code List<Task>} containing an array of all the successfully deleted tasks.
     * @param deletedTasksIndexes a {@code List<Index} containing the array of indexes of tasks that were successfully
     * deleted.
     * @return a {@code String} listings all the successfully deleted tasks.
     */
    private String deletedTasksToString(List<Task> deletedTasks, List<Index> deletedTasksIndexes) {
        String str = "Successfully Deleted Tasks: \n";
        for (int i = deletedTasks.size() - 1; i >= 0; i--) {
            str += deletedTasksIndexes.get(i).getOneBased() + ". " + deletedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Converts a list of indexes to a string to be returned to the user.
     *
     * @param indexes a {@code List<Index>} containing the list of indexes that are to be processed and returned to the
     * user.
     * @return a {@code String} of the list of indexes that are passed into the function.
     */
    private String indexesToString(List<Index> indexes) {
        StringBuilder str = new StringBuilder();
        if (indexes.size() > 1) {
            for (int i = indexes.size() - 1; i >= 1; i--) {
                str.append(indexes.get(i).getOneBased());
                str.append(i == 1 ? " and " : ", ");
            }
        }
        str.append(indexes.get(0).getOneBased());
        return str.toString();
    }

    /**
     * Checks if index is a valid index to delete.
     * If index > size of targetIndexes, return false.
     * If index is a valid index, return true.
     *
     * @param index the index which validity is to be checked.
     * @param taskList the task list which size is to be checked against.
     * @return a boolean representing if the index is valid.
     */
    private boolean isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return false;
        } else { //valid index
            return true;
        }
    }

    /**
     * Processes the lists containing the results of the deleting of indexes and returns the result to the user.
     *
     * @param model the current model
     * @param lastShownList the last shown list which contains the tasks to be marked.
     * @param outOfBoundsIndexes the array of indexes inputted by the user that are outOfBounds.
     * @return CommandResult of deleting the inputted indexes.
     * @throws CommandException
     */
    private CommandResult result(Model model, List<Task> lastShownList, List<Index> deleteTaskIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();
        List<Task> deletedTasks = new ArrayList<>();

        if (!outOfBoundsIndexes.isEmpty()) {
            errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                    + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
        }

        if (errorString.length() != 0) { //throw error if any
            throw new CommandException(errorString.toString());
        }

        if (!deleteTaskIndexes.isEmpty()) {
            for (int i = 0; i < targetIndexes.size(); i++) {
                Index index = deleteTaskIndexes.get(i);
                Task taskToDelete = lastShownList.get(index.getZeroBased());
                model.deleteTask(taskToDelete);
                deletedTasks.add(taskToDelete);
            }
        }

        return new CommandResult(deletedTasksToString(deletedTasks, deleteTaskIndexes));

    }
}
