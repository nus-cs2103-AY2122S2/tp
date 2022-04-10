
package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.commons.util.CollectionUtil;
import seedu.trackermon.logic.commands.exceptions.CommandException;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.show.Comment;
import seedu.trackermon.model.show.Name;
import seedu.trackermon.model.show.Rating;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.Status;
import seedu.trackermon.model.tag.Tag;

/**
 * Edits the details of an existing show in Trackermon.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_FORMAT = "Parameters: INDEX "
            + "{[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_RATING + "RATING] "
            + "[" + PREFIX_COMMENT + "COMMENT] "
            + "[" + PREFIX_TAG + "TAG]…\u200B}";

    public static final String COMMAND_EXAMPLE = "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Attack on Titan "
            + PREFIX_STATUS + "watching "
            + PREFIX_COMMENT + "This is not bad!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Modifies the details of the show identified "
            + "by the index number used in the displayed show list. "
            + "Existing values will be overwritten by the input values. "
            + "At least one parameter must be stated in the edit command.\n"
            + COMMAND_FORMAT + "\n" + COMMAND_EXAMPLE;

    public static final String MESSAGE_EDIT_SHOW_SUCCESS = "Edited Show: %1$s";

    private final Index index;
    private final EditShowDescriptor editShowDescriptor;

    /**
     * Creates an edit constructor to edit the specified {@code Show} at a specific {@code Index}.
     * @param index of the show in the filtered show list to edit.
     * @param editShowDescriptor details to edit the show with.
     */
    public EditCommand(Index index, EditShowDescriptor editShowDescriptor) {
        requireNonNull(index);
        requireNonNull(editShowDescriptor);

        this.index = index;
        this.editShowDescriptor = new EditShowDescriptor(editShowDescriptor);
    }

    /**
     * Executes a {@code Model} object.
     * @param model {@code Model} which the command should operate on.
     * @return a {@code CommandResult} object.
     * @throws CommandException if there is an invalid index.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Show> lastShownList = model.getFilteredShowList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Show showToEdit = lastShownList.get(index.getZeroBased());
        Show editedShow = createEditedShow(showToEdit, editShowDescriptor);


        if (!showToEdit.isSameShow(editedShow) && model.hasShow(editedShow)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_SHOW);
        }

        model.setShow(showToEdit, editedShow);
        return new CommandResult(String.format(MESSAGE_EDIT_SHOW_SUCCESS, editedShow), index.getZeroBased());
    }

    /**
     * Creates and returns a {@code Show} with the details of {@code showToEdit}
     * edited with {@code editShowDescriptor}.
     * @param showToEdit the show to be edited.
     * @param editShowDescriptor details to edit the show with.
     * @return a {@code Show} with the details of {@code showToEdit}.
     */
    private static Show createEditedShow(Show showToEdit, EditShowDescriptor editShowDescriptor) {
        assert showToEdit != null;

        Name updatedName = editShowDescriptor.getName().orElse(showToEdit.getName());
        Status updatedStatus = editShowDescriptor.getStatus().orElse(showToEdit.getStatus());
        Set<Tag> updatedTags = editShowDescriptor.getTags().orElse(showToEdit.getTags());
        Comment updateComment = editShowDescriptor.getComment().orElse(showToEdit.getComment());
        Rating updateRating = editShowDescriptor.getRating().orElse(showToEdit.getRating());
        return new Show(updatedName, updatedStatus, updatedTags, updateComment, updateRating);
    }

    /**
     * Returns whether two objects are equal.
     * @param other the second object to be compared with.
     * @return true if both objects are equal, else return false.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editShowDescriptor.equals(e.editShowDescriptor);
    }

    /**
     * Stores the details to edit the show with. Each non-empty field value will replace the
     * corresponding field value of the show.
     */
    public static class EditShowDescriptor {
        private Name name;
        private Status status;
        private Set<Tag> tags;
        private Comment comment;
        private Rating rating;

        /**
         * Creates a default constructor.
         */
        public EditShowDescriptor() {}

        /**
         * Creates a defensive copy of {@code tags} and it is used internally. It is a copy constructor.
         * @param toCopy the constructor to be copied.
         */
        public EditShowDescriptor(EditShowDescriptor toCopy) {
            setName(toCopy.name);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
            setComment(toCopy.comment);
            setRating(toCopy.rating);
        }

        /**
         * Returns true if at least one field is edited.
         * @return true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, status, tags, comment, rating);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public Optional<Comment> getComment() {
            return Optional.ofNullable(comment);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Returns whether two objects are equal.
         * @param other the second object to be compared with.
         * @return true if both objects are equal, else return false.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditShowDescriptor)) {
                return false;
            }

            // state check
            EditShowDescriptor e = (EditShowDescriptor) other;

            return getName().equals(e.getName())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags())
                    && getComment().equals(e.getComment())
                    && getRating().equals(e.getRating());
        }
    }
}
