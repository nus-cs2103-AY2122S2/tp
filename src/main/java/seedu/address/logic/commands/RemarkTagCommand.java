package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Remark;
import seedu.address.model.tag.Tag;

/**
 * Modifies a remark on an existing tag
 */
public class RemarkTagCommand extends Command {

    public static final String COMMAND_WORD = "remark_tag";
    public static final String MESSAGE_SUCCESS = "Modify remark success on tag %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the tag identified by\n"
            + " it's tag name. Existing remark will be overwritten by the input.\n"
            + "Parameters: t/TAG_NAME r/REMARK\n"
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
            throw new CommandException("tag does not exist");
        }

        ObservableList<Tag> allTags = model.getTagList();
        for (Tag allTag : allTags) {
            if (allTag.equals(tag)) {
                allTag.changeRemark(remark);
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, tag),
                false, true, false, null);
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
