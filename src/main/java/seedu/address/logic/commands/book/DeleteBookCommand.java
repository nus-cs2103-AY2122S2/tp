package seedu.address.logic.commands.book;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;

/**
 * Deletes a book identified using its displayed index from LibTask.
 */
public class DeleteBookCommand extends Command {

    public static final String MESSAGE_USAGE = BOOK_COMMAND_GROUP + " " + DELETE_COMMAND_WORD
            + ": Deletes the book identified by the index number used in the displayed book list.\n"
            + "Parameters: INDEX (must be a positive integer "
            + "and does not exceed the largest index number in the displayed book list)\n"
            + "Example: " + BOOK_COMMAND_GROUP + " " + DELETE_COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_BOOK_SUCCESS = "Deleted Book: %1$s";
    public static final String MESSAGE_DELETE_BORROWED_BOOK = "You cannot delete a book that is borrowed!";

    private final Index targetIndex;

    /**
     * Creates an DeleteBookCommand to delete the specified {@code Book}
     */
    public DeleteBookCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Book> lastShownList = model.getFilteredBookList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToDelete = lastShownList.get(targetIndex.getZeroBased());
        if (bookToDelete.isBorrowed()) {
            throw new CommandException(MESSAGE_DELETE_BORROWED_BOOK);
        }

        model.deleteBook(bookToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteBookCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteBookCommand) other).targetIndex)); // state check
    }
}
