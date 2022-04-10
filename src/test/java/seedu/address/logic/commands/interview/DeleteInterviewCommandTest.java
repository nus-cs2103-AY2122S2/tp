package seedu.address.logic.commands.interview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApplicantCommand}.
 */
public class DeleteInterviewCommandTest {

    private Model model = new ModelManager(getTypicalHireLah(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Interview interviewToDelete = model.getFilteredInterviewList().get(INDEX_FIRST.getZeroBased());
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(INDEX_FIRST);

        String expectedMessage =
                String.format(DeleteInterviewCommand.MESSAGE_DELETE_INTERVIEW_SUCCESS, interviewToDelete);

        ModelManager expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
        expectedModel.deleteInterview(interviewToDelete);

        assertCommandSuccess(deleteInterviewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        DeleteInterviewCommand deleteInterviewCommand = new DeleteInterviewCommand(outOfBoundIndex);

        assertCommandFailure(deleteInterviewCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteInterviewCommand deleteFirstCommand = new DeleteInterviewCommand(INDEX_FIRST);
        DeleteInterviewCommand deleteSecondCommand = new DeleteInterviewCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteInterviewCommand deleteFirstCommandCopy = new DeleteInterviewCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different interview -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
