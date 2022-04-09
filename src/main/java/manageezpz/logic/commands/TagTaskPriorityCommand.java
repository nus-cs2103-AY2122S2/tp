package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.List;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.task.Priority;
import manageezpz.model.task.Task;

public class TagTaskPriorityCommand extends Command {

    public static final String COMMAND_WORD = "tagPriority";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Tags the specified priority to the task identified by the "
            + "index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_PRIORITY + "PRIORITY_VALUE " + "(must be either NONE/LOW/MEDIUM/HIGH)\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_PRIORITY + "HIGH";

    public static final String MESSAGE_TAG_PRIORITY_SUCCESS = "Task tagged with %1$s priority: ";

    private final Index targetIndex;
    private final Priority priority;

    /**
     * Constructor to initialize a TagTaskPriorityCommand class with the given
     * targetIndex and priority.
     *
     * @param targetIndex Index of the Task to tag the priority level
     * @param priority Priority level of the Task
     */
    public TagTaskPriorityCommand(Index targetIndex, Priority priority) {
        requireAllNonNull(targetIndex, priority);
        this.targetIndex = targetIndex;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Task taskToTagPriority = lastShownList.get(targetIndex.getZeroBased());
        Task taggedPriorityTask = model.tagPriorityToTask(taskToTagPriority, priority);

        return new CommandResult(String.format(MESSAGE_TAG_PRIORITY_SUCCESS, priority.name())
                + taggedPriorityTask);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagTaskPriorityCommand// instanceof handles nulls
                && targetIndex.equals(((TagTaskPriorityCommand) other).targetIndex)
                && priority.equals(((TagTaskPriorityCommand) other).priority));
    }
}
