package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;

/**
 * Adds a comment to an existing person in the address book.
 * AddCommentCommand is adapted from https://nus-cs2103-ay2122s2.github.io/tp/tutorials/AddRemark.html
 */
public class CommentCommand extends Command {

    public static final String COMMAND_WORD = "comment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a comment to the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing comments will be overwritten by the input.\n"
            + "Use an empty value after c/ to delete the comment.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COMMENT + "COMMENT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMMENT + "Good at teamwork and programming";
    public static final String MESSAGE_ADD_SUCCESS = "Added comment to %s: %s";
    public static final String MESSAGE_REMOVE_SUCCESS = "Removed comment from %s";

    private final Index index;
    private final Comment comment;

    /**
     * @param index of the person in the filtered person list
     * @param comment comment to be added
     */
    public CommentCommand(Index index, Comment comment) {
        requireAllNonNull(index, comment);

        this.index = index;
        this.comment = comment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getStatus(),
                personToEdit.getModules(), comment);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the comment is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        if (personToEdit.getComment().isEmpty()) {
            return String.format(MESSAGE_REMOVE_SUCCESS, personToEdit.getName());
        } else {
            return String.format(MESSAGE_ADD_SUCCESS, personToEdit.getName(), personToEdit.getComment());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommentCommand)) {
            return false;
        }

        // state check
        CommentCommand e = (CommentCommand) other;
        return index.equals(e.index)
                && comment.equals(e.comment);
    }
}
