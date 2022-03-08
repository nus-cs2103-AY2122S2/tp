package seedu.contax.testutil;

import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.contax.logic.commands.AddTagCommand;
import seedu.contax.model.tag.Tag;

public class TagUtil {

    public static String getAddTagCommand(Tag tag) {
        return AddTagCommand.COMMAND_WORD + " " + getTagDetails(tag);
    }

    public static String getTagDetails(Tag tag) {
        return PREFIX_NAME + tag.getTagName();
    }
}
