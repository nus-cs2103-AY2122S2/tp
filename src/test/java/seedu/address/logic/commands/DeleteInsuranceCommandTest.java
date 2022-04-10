package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInsuranceCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showInsuranceAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static seedu.address.testutil.TypicalInsurances.getTypicalInsuranceBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.insurance.Insurance;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInsuranceCommand}.
 */
public class DeleteInsuranceCommandTest {

    private Model model = new ModelManager(getTypicalInsuranceBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Insurance insuranceToDelete = model.getFilteredInsuranceList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteInsuranceCommand deleteInsuranceCommand = new DeleteInsuranceCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteInsuranceCommand.MESSAGE_DELETE_INSURANCE_SUCCESS,
                insuranceToDelete);

        ModelManager expectedModel = new ModelManager(model.getInsuranceBook(), new UserPrefs());
        expectedModel.deleteInsurance(insuranceToDelete);

        assertCommandSuccess(deleteInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInsuranceList().size() + 1);
        DeleteInsuranceCommand deleteInsuranceCommand = new DeleteInsuranceCommand(outOfBoundIndex);

        assertInsuranceCommandFailure(deleteInsuranceCommand, model,
                Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showInsuranceAtIndex(model, INDEX_FIRST_OBJECT);

        Insurance insuranceToDelete = model.getFilteredInsuranceList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteInsuranceCommand deleteInsuranceCommand = new DeleteInsuranceCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteInsuranceCommand.MESSAGE_DELETE_INSURANCE_SUCCESS,
                insuranceToDelete);

        Model expectedModel = new ModelManager(model.getInsuranceBook(), new UserPrefs());
        expectedModel.deleteInsurance(insuranceToDelete);
        showNoInsurance(expectedModel);

        assertCommandSuccess(deleteInsuranceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showInsuranceAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of insurance book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInsuranceBook().getInsuranceList().size());

        DeleteInsuranceCommand deleteInsuranceCommand = new DeleteInsuranceCommand(outOfBoundIndex);

        assertInsuranceCommandFailure(deleteInsuranceCommand, model,
                Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteInsuranceCommand deleteFirstCommand = new DeleteInsuranceCommand(INDEX_FIRST_OBJECT);
        DeleteInsuranceCommand deleteSecondCommand = new DeleteInsuranceCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInsuranceCommand deleteFirstCommandCopy = new DeleteInsuranceCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different insurance -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoInsurance(Model model) {
        model.updateFilteredInsuranceList(p -> false);

        assertTrue(model.getFilteredInsuranceList().isEmpty());
    }
}
