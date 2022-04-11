package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.ViewTaskCommand;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add task command string for adding the {@code task}.
     */
    public static String getAddTaskCommand(Task task, Group group) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task) + getGroupDetails(group);
    }

    /**
     * Returns a delete task command string for deleting the {@code task}.
     */
    public static String getDeleteTaskCommand(Task task, Group group) {
        return DeleteTaskCommand.COMMAND_WORD + " " + getTaskDetails(task) + getGroupDetails(group);
    }

    /**
     * Returns an view task command string for view the {@code task}.
     */
    public static String getViewTaskCommand(Group group) {
        return ViewTaskCommand.COMMAND_WORD + " " + getGroupDetails(group);
    }

    /**
     * Returns the part of command string for the given {@code group}'s details.
     */
    public static String getGroupDetails(Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_GROUP_NAME + group.getGroupName().groupName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK + task.getTaskName().taskName + " ");
        return sb.toString();
    }
}
