package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.Model;
import seedu.contax.model.tag.Name;
import seedu.contax.model.tag.Tag;

/**
 * Edits the details of an existing tag in the address book.
 */
public class EditTagCommand extends Command {
    public static final String COMMAND_WORD = "edittag";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Edits the details of the specified tag.**\n"
            + "Parameters: *INDEX t/NEW_TAGNAME*\n"
            + "Example: `" + COMMAND_WORD + " 1 " + "t/Prospective Clients`";

    public static final String MESSAGE_EDIT_TAG_SUCCESS = "Edited Tag: %s";
    public static final String MESSAGE_TAG_EXISTS = "Specified tag name already exists in the address book.";
    private final Index index;
    private final EditTagDescriptor editTagDescriptor;

    /**
     * @param index The specified index to update the tag
     * @param editTagDescriptor Details to edit the tag with
     */
    public EditTagCommand(Index index, EditTagDescriptor editTagDescriptor) {
        requireNonNull(index);
        requireNonNull(editTagDescriptor);

        this.index = index;
        this.editTagDescriptor = editTagDescriptor;
    }

    /**
     * Creates and returns a {@code Tag} with the details of {@code tagToEdit} edited with {@code editTagDescriptor}.
     * Depends on the execute method to check that editTagDescriptor's tag index is valid.
     */
    private static Tag createEditedTag(Tag tagToEdit, EditTagDescriptor editTagDescriptor) {
        assert tagToEdit != null;

        Name newTagName = editTagDescriptor.getTagName().orElse(tagToEdit.getTagName());

        // If the names are the name, no need to create a new tag
        if (newTagName.equals(tagToEdit.getTagNameString())) {
            return tagToEdit;
        }

        return new Tag(newTagName);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tag> tagList = model.getFilteredTagList();

        if (index.getZeroBased() >= tagList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToEdit = tagList.get(index.getZeroBased());
        Tag editedTag = createEditedTag(tagToEdit, editTagDescriptor);

        // Checks if the specified tag name already exists in the address book.
        if (model.hasTag(editedTag)) {
            throw new CommandException(MESSAGE_TAG_EXISTS);
        }

        model.setTag(tagToEdit, editedTag);
        return new CommandResult(String.format(MESSAGE_EDIT_TAG_SUCCESS, editedTag));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof EditTagCommand)) {
            return false;
        }

        EditTagCommand e = ((EditTagCommand) o);
        return e.index.equals(index) && e.editTagDescriptor.equals(editTagDescriptor);
    }

    /**
     * Stores the details to edit the tag with.
     */
    public static class EditTagDescriptor {
        private Name tagName;

        public EditTagDescriptor() {

        }

        public Optional<Name> getTagName() {
            return Optional.ofNullable(tagName);
        }

        public void setTagName(String tagName) {
            this.tagName = new Name(tagName);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof EditTagDescriptor)) {
                return false;
            }

            EditTagDescriptor e = (EditTagDescriptor) o;

            return e.getTagName().equals(getTagName());
        }
    }
}
