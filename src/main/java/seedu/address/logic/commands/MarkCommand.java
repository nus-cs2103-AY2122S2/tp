package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MassOpsParser;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CompletionStatus;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * Marks a task identified using it's displayed index from the task list as completed.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the tasks at the given indexes of the displayed task list as complete.\n"
            + "Parameters: INDEX... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_COMPLETED = "The completion status of this task is already set"
            + " to complete.";
    public static final String MESSAGE_MULTIPLE_TASKS_ALREADY_COMPLETED = "The completion status of these tasks are"
            + " already set to complete.";

    private final List<Index> targetIndexes;

    /**
     * Constructor for marking multiple indexes.
     *
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be marked
     */
    public MarkCommand(List<Index> targetIndexes) {
        assert(targetIndexes != null && targetIndexes.size() != 0);
        this.targetIndexes = MassOpsParser.sortInAsc(targetIndexes);
    }

    /**
     * Creates a marked iteration of the Task provided.
     *
     * @param task task to be copied.
     * @return marked task.
     */
    private static Task createMarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("true");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task markedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return markedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();
        List<Index> markTaskIndexes = new ArrayList<>();
        List<Index> alreadyMarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to mark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) {
                alreadyMarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) {
                outOfBoundsIndexes.add(index);
            } else {
                markTaskIndexes.add(index);
            }
        }
        return result(model, lastShownList, markTaskIndexes, alreadyMarkedIndexes, outOfBoundsIndexes);
    }

    /**
     * Converts the list of successfully marked tasks into a string to be returned to the user
     *
     * @param markedTasks a {@code List<Task>} containing an array of all the successfully marked tasks.
     * @param markedTasksIndexes a {@code List<Index>} containing the array of indexes that were successfully marked.
     * @return a {@code String} listings all the successfully marked tasks.
     */
    private String markedTasksToString(List<Task> markedTasks, List<Index> markedTasksIndexes) {
        String str = "Successfully Marked Task(s): \n";
        for (int i = 0; i < markedTasks.size(); i++) {
            str += markedTasksIndexes.get(i).getOneBased() + ". " + markedTasks.get(i) + "\n";
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && targetIndexes.equals(((MarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to mark.
     * If index > size of targetIndexes, return -1.
     * If index is already marked, return 1.
     * If index is a valid index, return 0.
     *
     * @param index the inde which validity is to be checked.
     * @param taskList the task list which size is to be checked against.
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("true")) {
            //already marked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the marking of indexes and returns the result to the user.
     *
     * @param model the current model
     * @param lastShownList the last shown list which contains the tasks to be marked.
     * @param markTaskIndexes the array of indexes inputted by the user that are to be marked.
     * @param alreadyMarkedIndexes the array of indexes inputted by the user that are already marked.
     * @param outOfBoundsIndexes the array of indexes inputted by the user that are outOfBounds.
     * @return CommandResult of the marking of the inputted indexes.
     * @throws CommandException
     */
    private CommandResult result(Model model, List<Task> lastShownList, List<Index> markTaskIndexes,
                                 List<Index> alreadyMarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();
        List<Task> markedTasks = new ArrayList<>();

        if (!alreadyMarkedIndexes.isEmpty()) {
            if (alreadyMarkedIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(alreadyMarkedIndexes) + ": "
                    + MESSAGE_TASK_ALREADY_COMPLETED + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(alreadyMarkedIndexes) + ": "
                        + MESSAGE_MULTIPLE_TASKS_ALREADY_COMPLETED + "\n");
            }
        }

        if (!outOfBoundsIndexes.isEmpty()) {
            if (outOfBoundsIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_MULTIPLE_TASK_DISPLAYED_INDEX + "\n");
            }
        }

        if (errorString.length() != 0) { //throw error if any
            throw new CommandException(errorString.toString());
        }

        if (!markTaskIndexes.isEmpty()) {
            for (int i = 0; i < markTaskIndexes.size(); i++) {
                Index index = markTaskIndexes.get(i);
                Task taskToMark = lastShownList.get(index.getZeroBased());
                Task markedTask = createMarkedTask(taskToMark);
                markedTasks.add(markedTask);
                model.strictSetTask(taskToMark, markedTask);
            }
        }

        return new CommandResult(markedTasksToString(markedTasks, markTaskIndexes));
    }
}
