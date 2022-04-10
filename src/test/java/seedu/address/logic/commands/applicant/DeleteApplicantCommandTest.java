package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApplicantCommand}.
 */
public class DeleteApplicantCommandTest {

    private Model model = new ModelManager(getTypicalHireLah(), new UserPrefs());

    //    @Test
    //    public void execute_validIndexUnfilteredList_success() {
    //        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST.getZeroBased());
    //        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(INDEX_FIRST);
    //
    //        String expectedMessage =
    //                String.format(DeleteApplicantCommand.MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete) + "\n"
    //                        + String.format(DeleteApplicantCommand.MESSAGE_DELETE_INTERVIEWS, 0);
    //
    //        ModelManager expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
    //        expectedModel.deleteApplicant(applicantToDelete);
    //
    //        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showApplicantAtIndex(model, INDEX_FIRST);
    //
    //        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST.getZeroBased());
    //        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(INDEX_FIRST);
    //
    //        String expectedMessage =
    //                String.format(DeleteApplicantCommand.MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete) + "\n"
    //                        + String.format(DeleteApplicantCommand.MESSAGE_DELETE_INTERVIEWS, 0);
    //
    //        Model expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
    //        expectedModel.deleteApplicant(applicantToDelete);
    //        showNoApplicant(expectedModel);
    //
    //        assertCommandSuccess(deleteApplicantCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHireLah().getApplicantList().size());

        DeleteApplicantCommand deleteApplicantCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteApplicantCommand, model, Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteApplicantCommand deleteFirstCommand = new DeleteApplicantCommand(INDEX_FIRST);
        DeleteApplicantCommand deleteSecondCommand = new DeleteApplicantCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteApplicantCommand deleteFirstCommandCopy = new DeleteApplicantCommand(INDEX_FIRST);

        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
