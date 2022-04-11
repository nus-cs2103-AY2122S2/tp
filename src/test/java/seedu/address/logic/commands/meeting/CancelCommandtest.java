package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code CancelCommand}.
 */
public class CancelCommandtest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), new UserPrefs());

    /**
     * Tests for valid indexes for unfiltered meeting list
     */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(CancelCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for invalid index for unfiltered meeting list
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, String.format(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX,
                model.getFilteredMeetingList().size() + 1));
    }

    /**
     * Tests for valid index for filtered meeting list
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        CancelCommand cancelCommand = new CancelCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(CancelCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingBook(), new UserPrefs());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(cancelCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for invalid indexes for filtered meeting list
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of meeting book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        CancelCommand cancelCommand = new CancelCommand(outOfBoundIndex);

        assertCommandFailure(cancelCommand, model, String.format(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX,
                outOfBoundIndex.getOneBased()));
    }

    /**
     * Tests for equal methods
     */
    @Test
    public void equals() {
        CancelCommand cancelFirstCommand = new CancelCommand(INDEX_FIRST_MEETING);
        CancelCommand cancelSecondCommand = new CancelCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(cancelFirstCommand.equals(cancelFirstCommand));

        // same values -> returns true
        CancelCommand cancelFirstCommandCopy = new CancelCommand(INDEX_FIRST_MEETING);
        assertTrue(cancelFirstCommand.equals(cancelFirstCommandCopy));

        // different types -> returns false
        assertFalse(cancelFirstCommand.equals(1));

        // null -> returns false
        assertFalse(cancelFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(cancelFirstCommand.equals(cancelSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }
}
