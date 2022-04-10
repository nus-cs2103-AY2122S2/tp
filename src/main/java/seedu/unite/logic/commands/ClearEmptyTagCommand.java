package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.unite.model.Model;
import seedu.unite.model.tag.Tag;

/**
 * Clears all empty tag in UNite.
 */
public class ClearEmptyTagCommand extends Command {

    public static final String COMMAND_WORD = "clear_emptytag";

    public static final String MESSAGE_SUCCESS = "Cleared %d empty tags";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Tag> tagList = model.getTagList();
        List<Tag> tagsToDelete = new ArrayList<>();
        int emptyTagCount = 0;
        for (Tag t : tagList) {
            if (model.countPersonsInTag(t) == 0) {
                tagsToDelete.add(t);
                emptyTagCount++;
            }
        }
        for (Tag t : tagsToDelete) {
            model.deleteTag(t);
        }
        model.showTagList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, emptyTagCount));
    }
}
