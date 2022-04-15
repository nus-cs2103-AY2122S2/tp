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
 * Marks a task identified using it's displayed index from the task list as uncompleted.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the tasks at the given indexes of the displayed task list as incomplete.\n"
            + "Parameters: INDEX... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_TASK_ALREADY_UNCOMPLETED = "The completion status of this task is already set"
            + " to incomplete.";
    public static final String MESSAGE_MULTIPLE_TASKS_ALREADY_UNCOMPLETED = "The completion status of these tasks are"
            + " already set to incomplete.";

    private final List<Index> targetIndexes;

    /**
     * Constructor for unmarking multiple indexes.
     *
     * @param targetIndexes an {@code ArrayList<Index>} which stores each index to be unmarked
     */
    public UnmarkCommand(List<Index> targetIndexes) {
        assert(targetIndexes != null && targetIndexes.size() != 0);
        this.targetIndexes = MassOpsParser.sortInAsc(targetIndexes);
    }

    /**
     * Creates an unmarked iteration of the Task provided.
     *
     * @param task task to be copied.
     * @return unmarked task.
     */
    private static Task createUnmarkedTask(Task task) {
        Name name = task.getName();
        Description description = task.getDescription();
        CompletionStatus completionStatus = new CompletionStatus("false");
        Deadline deadline = task.getDeadline();
        Priority priority = task.getPriority();
        Set<Tag> tags = task.getTags();

        Task unmarkedTask = new Task(name, description, completionStatus, deadline, priority, tags);
        return unmarkedTask;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getSortedTaskList();
        List<Index> unmarkTasksIndexes = new ArrayList<>();
        List<Index> alreadyUnmarkedIndexes = new ArrayList<>();
        List<Index> outOfBoundsIndexes = new ArrayList<>();


        // to unmark each index in the task list individually.
        for (int i = 0; i < targetIndexes.size(); i++) {
            Index index = targetIndexes.get(i);

            if (isValidIndex(index, lastShownList) == 1) {
                alreadyUnmarkedIndexes.add(index);
            } else if (isValidIndex(index, lastShownList) == -1) {
                outOfBoundsIndexes.add(index);
            } else {
                unmarkTasksIndexes.add(index);
            }
        }
        return result(model, lastShownList, unmarkTasksIndexes, alreadyUnmarkedIndexes, outOfBoundsIndexes);

    }

    /**
     * Converts the list of successfully unmarked tasks into a string to be returned to the user.
     *
     * @param unmarkedTasks a {@code List<Task>} containing an array of all the successfully unmarked tasks.
     * @param unmarkedTasksIndexes a {@code List<Index>} containing the array of indexes that were successfully
     * unmarked.
     * @return a {@code String} listings all the successfully unmarked tasks.
     */
    private String unmarkedTasksToString(List<Task> unmarkedTasks, List<Index> unmarkedTasksIndexes) {
        String str = "Successfully Unmarked Task(s): \n";
        for (int i = 0; i < unmarkedTasks.size(); i++) {
            str += unmarkedTasksIndexes.get(i).getOneBased() + ". " + unmarkedTasks.get(i) + "\n";
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
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndexes.equals(((UnmarkCommand) other).targetIndexes)); // state check
    }

    /**
     * Checks if index is a valid index to unmark.
     * If index > size of targetIndexes, return -1.
     * If index is already unmarked, return 1.
     * If index is a valid index, return 0.
     *
     * @param index the inde which validity is to be checked.
     * @param taskList the task list which size is to be checked against.
     * @return an int representing validity of the index
     */
    private int isValidIndex(Index index, List<Task> taskList) {
        if (index.getZeroBased() > taskList.size() - 1 || index.getZeroBased() < 0) { //index out of bounds
            return -1;
        } else if (taskList.get(index.getZeroBased()).getCompletionStatus().toString().equals("false")) {
            //already unmarked index
            return 1;
        } else { //valid index
            return 0;
        }
    }

    /**
     * Processes the lists containing the results of the unmarking of indexes and returns the result to the user.
     *
     * @param model the current model
     * @param lastShownList the last shown list which contains the tasks to be unmarked.
     * @param unmarkTaskIndexes the array of indexes inputted by the user that are to be unmarked.
     * @param alreadyUnmarkedIndexes the array of indexes inputted by the user that are already unmarked.
     * @param outOfBoundsIndexes the array of indexes inputted by the user that are outOfBounds.
     * @return CommandResult of unmarking the inputted indexes.
     * @throws CommandException
     */
    private CommandResult result(Model model, List<Task> lastShownList, List<Index> unmarkTaskIndexes,
                                 List<Index> alreadyUnmarkedIndexes,
                                 List<Index> outOfBoundsIndexes) throws CommandException {
        StringBuilder errorString = new StringBuilder();
        List<Task> unmarkedTasks = new ArrayList<>();

        if (!alreadyUnmarkedIndexes.isEmpty()) {
            if (alreadyUnmarkedIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + MESSAGE_MULTIPLE_TASKS_ALREADY_UNCOMPLETED + "\n");
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

        if (!unmarkTaskIndexes.isEmpty()) {
            for (int i = 0; i < unmarkTaskIndexes.size(); i++) {
                Index index = unmarkTaskIndexes.get(i);
                Task taskToUnmark = lastShownList.get(index.getZeroBased());
                Task unmarkedTask = createUnmarkedTask(taskToUnmark);
                unmarkedTasks.add(unmarkedTask);
                model.strictSetTask(taskToUnmark, unmarkedTask);
            }
        }

        return new CommandResult(unmarkedTasksToString(unmarkedTasks, unmarkTaskIndexes));
    }
}
