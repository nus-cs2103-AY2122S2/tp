package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.tag.Tag;

/**
 * Deletes a tag identified using it's displayed index from the address book.
 */
public class DeleteTagCommand extends Command {
    public static final String COMMAND_WORD = "deletetag";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " : Deletes the tag identified by tag name (case-insensitive).\n"
            + "Deleting this tag will also remove contacts who have this tag.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " n/friends";

    private static final String MESSAGE_DELETE_TAG_SUCCESS = "Deleted Tag: %s";

    private final Index targetIndex;

    public DeleteTagCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> tagList = model.getTagList();

        if (targetIndex.getZeroBased() >= tagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToDelete = tagList.get(targetIndex.getZeroBased());
        model.deleteTag(tagToDelete);

        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, tagToDelete));
    }
}
