package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;

import java.util.List;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Task;

/**
 * Unmarks a task identified using its displayed index from the address book,
 * i.e., changes the status back to not done.
 */
public class UnmarkTaskCommand extends Command {

    public static final String COMMAND_WORD = "unmarkTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the index number used in the displayed task list, "
            + "which changes the status back to not done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Task set as not done yet: %1$s";

    private final Index targetIndex;

    /**
     * Constructor to initialize an instance of UnmarkTaskCommand class
     * with the given targetIndex.
     *
     * @param targetIndex Index of the Task to be unmarked, i.e., changes the
     *                    status back to not done
     */
    public UnmarkTaskCommand(Index targetIndex) {
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

        Task taskToUnmark = lastShownList.get(targetIndex.getZeroBased());
        Task unmarkedTask = model.unmarkTask(taskToUnmark);

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkTaskCommand // instanceof handles nulls
                && targetIndex.equals(((UnmarkTaskCommand) other).targetIndex)); // state check
    }
}
