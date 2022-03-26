package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * Deletes an existing task from an existing group in ArchDuke.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deltask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an existing task from an existing group."
            + "Parameters: "
            + PREFIX_TASK + "TASK_NAME "
            + PREFIX_GROUP_NAME + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK + "v1.2 user guide "
            + PREFIX_GROUP_NAME + "CS2103-W16-3 \n";

    public static final String MESSAGE_NON_EXISTENT_TASK = "This task does not exist in the specified group.";

    public static final String MESSAGE_NON_EXISTENT_GROUP = "This group does not exist in the list";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted task: %1$s";

    private final Task task;
    private final Group group;

    /**
     * Creates a DeleteTaskCommand to delete the specified {@code Task}
     * in the specified {@code Group}
     *
     * @param task Task to be deleted.
     * @param group Group in which the task to be deleted exists.
     */
    public DeleteTaskCommand(Task task, Group group) {
        requireAllNonNull(task, group);

        this.task = task;
        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroup(group)) { //check whether the specified group exists
            if (!model.hasTask(task, group)) {
                throw new CommandException(MESSAGE_NON_EXISTENT_TASK);
            }

            Group groupToDeleteTask = group;
            Group groupDeletedTask = createDeletedTaskGroup(groupToDeleteTask, task, model);

            model.setGroup(group, groupDeletedTask);

            return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
        } else {
            throw new CommandException(MESSAGE_NON_EXISTENT_GROUP);
        }
    }

    /**
     * Creates and returns a {@code Group} with the details of {@code groupToDeleteTask}
     */
    private static Group createDeletedTaskGroup(Group groupToDeleteTask, Task taskToDelete, Model model) {
        assert groupToDeleteTask != null;

        model.deleteTask(taskToDelete, groupToDeleteTask);
        Group groupDeletedTask = model.getGroup(groupToDeleteTask);

        return groupDeletedTask;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return task.equals(e.task) && group.equals(e.group);
    }
}
