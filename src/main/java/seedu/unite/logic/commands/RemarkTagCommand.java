package seedu.unite.logic.commands;

import static seedu.unite.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.tag.Remark;
import seedu.unite.model.tag.Tag;

/**
 * Modifies a remark on an existing tag
 */
public class RemarkTagCommand extends Command {

    public static final String COMMAND_WORD = "remark_tag";
    public static final String MESSAGE_SUCCESS = "Modify remark success on tag %1$s";
    public static final String MESSAGE_NO_SUCH_TAG = "Tag does not exist";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the tag identified by\n"
            + " it's tag name. Existing remark will be overwritten by the input.\n"
            + "Parameters: t/TAG_NAME r/[REMARK]\n"
            + "Example: " + COMMAND_WORD + " labGroup1 r/meeting on Wed 8pm";

    private final Tag tag;
    private final Remark remark;

    /**
     * @param tag which it's remark to be changed
     * @param remark of the person to be updated to
     */
    public RemarkTagCommand(Tag tag, Remark remark) {
        requireAllNonNull(tag, remark);

        this.tag = tag;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (!model.hasTag(tag)) {
            throw new CommandException(MESSAGE_NO_SUCH_TAG);
        }

        ObservableList<Tag> allTags = model.getTagList();
        for (Tag allTag : allTags) {
            if (allTag.equals(tag)) {
                allTag.changeRemark(remark);
            }
        }
        model.showTagList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, tag));
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkTagCommand)) {
            return false;
        }

        // state check
        RemarkTagCommand e = (RemarkTagCommand) other;
        return tag.isSameTag(e.tag)
                && remark.equals(e.remark);
    }

}
