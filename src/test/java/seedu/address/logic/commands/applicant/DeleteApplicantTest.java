package seedu.address.logic.commands.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICANT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICANT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;

class DeleteApplicantTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        DeleteApplicant deleteApplicant = new DeleteApplicant(INDEX_FIRST_APPLICANT);

        String expectedMessage = String
                .format(DeleteApplicant.MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        assertCommandSuccess(deleteApplicant, model, expectedMessage, true, false, true, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        DeleteApplicant deleteApplicant = new DeleteApplicant(outOfBoundIndex);

        assertCommandFailure(deleteApplicant, model,
                String.format(MESSAGE_INVALID_INDEX, DeleteApplicant.MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_APPLICANT.getZeroBased());
        DeleteApplicant deleteApplicant = new DeleteApplicant(INDEX_FIRST_APPLICANT);

        String expectedMessage = String.format(DeleteApplicant
                .MESSAGE_DELETE_APPLICANT_SUCCESS, applicantToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);

        assertCommandSuccess(deleteApplicant, model, expectedMessage, true, false, true, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_APPLICANT);

        Index outOfBoundIndex = INDEX_SECOND_APPLICANT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getApplicantList().size());

        DeleteApplicant deleteApplicant = new DeleteApplicant(outOfBoundIndex);

        assertCommandFailure(deleteApplicant, model,
                String.format(MESSAGE_INVALID_INDEX, DeleteApplicant.MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        DeleteApplicant deleteApplicantFirstCommand = new DeleteApplicant(INDEX_FIRST_APPLICANT);
        DeleteApplicant deleteApplicantSecondCommand = new DeleteApplicant(INDEX_SECOND_APPLICANT);

        // same object -> returns true
        assertTrue(deleteApplicantFirstCommand.equals(deleteApplicantFirstCommand));

        // same values -> returns true
        DeleteApplicant deleteApplicantFirstCommandCopy = new DeleteApplicant(INDEX_FIRST_APPLICANT);
        assertTrue(deleteApplicantFirstCommand.equals(deleteApplicantFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteApplicantFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteApplicantFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteApplicantFirstCommand.equals(deleteApplicantSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
