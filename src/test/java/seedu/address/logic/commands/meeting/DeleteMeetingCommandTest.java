package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBookWithMeetings;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;



/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithMeetings(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getMeetingList().size());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        DeleteMeetingCommand deleteSecondMeetingCommand = new DeleteMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstMeetingCommand.equals(deleteFirstMeetingCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteFirstMeetingCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(deleteFirstMeetingCommand.equals(deleteFirstMeetingCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(deleteSecondMeetingCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show meetings.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false, true);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
