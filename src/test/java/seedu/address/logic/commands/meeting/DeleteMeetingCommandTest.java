package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMeetingCommand}.
 */
public class DeleteMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        final Meeting meetingToDelete = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        final DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Meeting meetingToDelete = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingList().size());

        final DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DeleteMeetingCommand deleteFirstCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        final DeleteMeetingCommand deleteSecondCommand = new DeleteMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        final DeleteMeetingCommand deleteFirstCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


    /**
     * Updates {@code model}'s filtered list to show no meetings.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
