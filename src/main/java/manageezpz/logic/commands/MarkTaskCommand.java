package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import java.util.List;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Task;

/**
 * Marks a task identified using its displayed index from the address book as done.
 */
public class MarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "markTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARK_TASK_SUCCESS = "Task set as done: %1$s";

    private final Index targetIndex;

    /**
     * Constructor to initialize an instance of MarkTaskCommand class
     * with the given targetIndex.
     *
     * @param targetIndex Index of the Task to be marked as done
     */
    public MarkTaskCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        Task markedTask = model.markTask(taskToMark);

        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((MarkTaskCommand) other).targetIndex)); // state check
    }
}
