package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertRecordCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showRecordAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.address.testutil.TypicalRecords.getTypicalRecordBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.record.Record;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteRecordCommand}.
 */
public class DeleteRecordCommandTest {

    private Model model = new ModelManager(getTypicalRecordBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Record insuranceToDelete = model.getFilteredRecordList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS, insuranceToDelete);

        ModelManager expectedModel = new ModelManager(model.getRecordBook(), new UserPrefs());
        expectedModel.deleteRecord(insuranceToDelete);

        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecordList().size() + 1);
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundIndex);

        assertRecordCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRecordAtIndex(model, INDEX_FIRST_OBJECT);

        Record insuranceToDelete = model.getFilteredRecordList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteRecordCommand.MESSAGE_DELETE_RECORD_SUCCESS, insuranceToDelete);

        Model expectedModel = new ModelManager(model.getRecordBook(), new UserPrefs());
        expectedModel.deleteRecord(insuranceToDelete);
        showNoRecord(expectedModel);

        assertCommandSuccess(deleteRecordCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecordAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of record book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRecordBook().getRecordList().size());

        DeleteRecordCommand deleteRecordCommand = new DeleteRecordCommand(outOfBoundIndex);

        assertRecordCommandFailure(deleteRecordCommand, model, Messages.MESSAGE_INVALID_RECORD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRecordCommand deleteFirstCommand = new DeleteRecordCommand(INDEX_FIRST_OBJECT);
        DeleteRecordCommand deleteSecondCommand = new DeleteRecordCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteRecordCommand deleteFirstCommandCopy = new DeleteRecordCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different record -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRecord(Model model) {
        model.updateFilteredRecordList(p -> false);

        assertTrue(model.getFilteredRecordList().isEmpty());
    }
}
