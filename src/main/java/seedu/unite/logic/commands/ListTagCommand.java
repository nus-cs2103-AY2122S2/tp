package seedu.unite.logic.commands;


import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.unite.model.Model;
import seedu.unite.model.tag.Tag;

/**
 * Lists all tags in the unite to the user.
 */
public class ListTagCommand extends Command {

    public static final String COMMAND_WORD = "list_tag";

    public static final String MESSAGE_SUCCESS = "Listed all tags : [";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        // todo: implement relevant UI
        // model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Tag> tags = model.getTagList();
        StringBuilder result = new StringBuilder(MESSAGE_SUCCESS);
        for (Tag tag : tags) {
            result.append(" ").append(tag.getTagName());
        }
        result.append(" ]");

        return new CommandResult(result.toString(),
                false, true, false, null);
    }
}
