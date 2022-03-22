package seedu.trackermon.logic.commands;

import static seedu.trackermon.commons.util.CollectionUtil.requireAllNonNull;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Comment;

/**
 * Allows users to comment on the show.
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";
    public static final String COMMENT_ADD_SUCCESS = "Adeded comment to show: %1$s";

    private final Index index;
    private final Comment comment;

    /**
     * Adds Comments at.
     * @param index
     * @param comment
     */
    public CommentCommand(Index index, Comment comment) {
        requireAllNonNull(index, comment);
        this.index = index;
        this.comment = comment;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Comment");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        CommentCommand e = (CommentCommand) other;
        return index.equals(e.index)
                && comment.equals(e.comment);
    }
}
