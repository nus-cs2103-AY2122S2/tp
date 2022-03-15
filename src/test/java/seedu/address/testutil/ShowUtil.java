package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.model.show.Show;
import seedu.address.model.tag.Tag;

import seedu.address.logic.commands.AddCommand;

/**
 * A utility class for show.
 */
public class ShowUtil {

    /**
     * Returns an add command string for adding the {@code show}.
     */
    public static String getAddCommand(Show show) {
        return AddCommand.COMMAND_WORD + " " + getShowDetails(show);
    }

    /**
     * Returns the part of command string for the given {@code show}'s details.
     */
    public static String getShowDetails(Show show) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + show.getName().fullName + " ");
        sb.append(PREFIX_STATUS + show.getStatus().name() + " ");
        show.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
