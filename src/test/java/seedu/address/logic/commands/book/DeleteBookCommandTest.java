package seedu.address.logic.commands.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBookAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_BOOK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.testutil.TypicalLibTask;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteBookCommand}.
 */
public class DeleteBookCommandTest {

    private Model model = new ModelManager(TypicalLibTask.getTypicalLibTask(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteBookCommand deleteCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteBookCommand.MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete);

        ModelManager expectedModel = new ModelManager(model.getLibTask(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookList().size() + 1);
        DeleteBookCommand deleteCommand = new DeleteBookCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Book bookToDelete = model.getFilteredBookList().get(INDEX_FIRST_BOOK.getZeroBased());
        DeleteBookCommand deleteCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);

        String expectedMessage = String.format(DeleteBookCommand.MESSAGE_DELETE_BOOK_SUCCESS, bookToDelete);

        Model expectedModel = new ModelManager(model.getLibTask(), new UserPrefs());
        expectedModel.deleteBook(bookToDelete);
        showNoBook(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBookAtIndex(model, INDEX_FIRST_BOOK);

        Index outOfBoundIndex = INDEX_SECOND_BOOK;
        // ensures that outOfBoundIndex is still in bounds of book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLibTask().getBookList().size());

        DeleteBookCommand deleteCommand = new DeleteBookCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteBorrowedBook_throwsCommandException() {
        DeleteBookCommand deleteCommand = new DeleteBookCommand(INDEX_FOURTH_BOOK); // the fourth book is borrowed
        assertCommandFailure(deleteCommand, model, DeleteBookCommand.MESSAGE_DELETE_BORROWED_BOOK);
    }

    @Test
    public void equals() {
        DeleteBookCommand deleteFirstCommand = new DeleteBookCommand(INDEX_FIRST_BOOK);
        DeleteBookCommand deleteSecondCommand = new DeleteBookCommand(INDEX_SECOND_BOOK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBookCommand deleteFirstCommandCopy = new DeleteBookCommand(INDEX_FIRST_BOOK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBook(Model model) {
        model.updateFilteredBookList(p -> false);

        assertTrue(model.getFilteredBookList().isEmpty());
    }
}
