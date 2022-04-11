package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalAddressBookWithMeetings;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;


public class CloseMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithMeetings(), new UserPrefs());

    @Test
    public void execute_pastMeeting_success() {
        Meeting meetingToClose = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        CloseMeetingCommand closeMeetingCommand = new CloseMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(CloseMeetingCommand.MESSAGE_CLOSE_MEETING_SUCCESS, meetingToClose);
        Meeting closedMeeting = meetingToClose.closeMeeting();

        AddressBook updatedAddressBook = getTypicalAddressBookWithMeetings();
        updatedAddressBook.setMeeting(meetingToClose, closedMeeting);
        Model expectedModel = new ModelManager(updatedAddressBook, new UserPrefs());

        assertCommandSuccess(closeMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonPastMeeting_failure() {
        CloseMeetingCommand closeMeetingCommand = new CloseMeetingCommand(INDEX_SECOND_MEETING);

        String expectedMessage = CloseMeetingCommand.MESSAGE_CANNOT_CLOSE_UPCOMING_MEETING;

        assertCommandFailure(closeMeetingCommand, model, expectedMessage);
    }

    @Test void execute_invalidIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        CloseMeetingCommand closeMeetingCommand = new CloseMeetingCommand(outOfBoundIndex);

        assertCommandFailure(closeMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }
}
