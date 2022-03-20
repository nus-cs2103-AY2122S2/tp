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
 * Changes the remark of an existing person in the address book.
 */
public class AddCommentCommand extends Command {

    public static final String COMMAND_WORD = "addcomment";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a comment to the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing comments will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_COMMENT + "COMMENT\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_COMMENT + "Good at teamwork and programming";
    public static final String MESSAGE_ADD_COMMENT_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_COMMENT_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Comment comment;

    /**
     * @param index of the person in the filtered person list
     * @param comment comment to be added
     */
    public AddCommentCommand(Index index, Comment comment) {
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
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_ADD_COMMENT_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommentCommand)) {
            return false;
        }

        // state check
        AddCommentCommand e = (AddCommentCommand) other;
        return index.equals(e.index)
                && comment.equals(e.comment);
    }
}
