package seedu.address.logic.commands.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.position.DeletePositionCommand.MESSAGE_DELETE_INTERVIEWS;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;

public class DeletePositionCommandTest {

    private Model model = new ModelManager(getTypicalHireLah(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Position positionToDelete = model.getFilteredPositionList().get(INDEX_FIRST.getZeroBased());
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(INDEX_FIRST);

        ModelManager expectedModel = new ModelManager(model.getHireLah(), new UserPrefs());
        expectedModel.deletePosition(positionToDelete);

        ArrayList<Interview> interviewsToDelete = model.getPositionsInterviews(positionToDelete);
        for (Interview i : interviewsToDelete) {
            expectedModel.deleteInterview(i);
        }

        String expectedMessage =
                String.format(DeletePositionCommand.MESSAGE_DELETE_POSITION_SUCCESS, positionToDelete)
                + "\n" + String.format(MESSAGE_DELETE_INTERVIEWS, interviewsToDelete.size());

        assertCommandSuccess(deletePositionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredInterviewList().size() + 1);
        DeletePositionCommand deletePositionCommand = new DeletePositionCommand(outOfBoundIndex);

        assertCommandFailure(deletePositionCommand, model, Messages.MESSAGE_INVALID_POSITION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePositionCommand deleteFirstCommand = new DeletePositionCommand(INDEX_FIRST);
        DeletePositionCommand deleteSecondCommand = new DeletePositionCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeletePositionCommand deleteFirstCommandCopy = new DeletePositionCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different position -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
