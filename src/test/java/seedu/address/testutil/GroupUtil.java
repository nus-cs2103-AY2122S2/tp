package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;

public class GroupUtil {

    /**
     * Returns an addgroup command string for adding the {@code group}.
     */
    public static String getAddGroupCommand(Group group) {
        return AddGroupCommand.COMMAND_WORD + " " + getGroupDetails(group);
    }

    /**
     * Returns the part of command string for the given {@code group}'s details.
     */
    public static String getGroupDetails(Group group) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_GROUP_NAME + group.getGroupName().groupName + " ");
        return sb.toString();
    }
}
