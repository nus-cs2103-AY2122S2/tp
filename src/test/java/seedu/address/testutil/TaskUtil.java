package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;

public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getDeleteTaskCommand(Task task, Group group) {
        return DeleteTaskCommand.COMMAND_WORD + " " + getTaskDetails(task) + getGroupDetails(group);
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
