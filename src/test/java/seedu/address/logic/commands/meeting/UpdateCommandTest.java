package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_QUARTERLY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGENDA_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PLACE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UpdateMeetingDescriptor;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.UpdateMeetingDescriptorBuilder;

public class UpdateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder().build();
        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder(editedMeeting).build();
        UpdateCommand command = new UpdateCommand(INDEX_FIRST_MEETING, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedPerson = meetingInList.withAgenda(VALID_AGENDA_PROJECT).withTime(VALID_TIME_PROJECT)
                .withPlace(VALID_PLACE_PROJECT).build();

        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT)
                .withPlace(VALID_PLACE_PROJECT).withTime(VALID_TIME_PROJECT).build();
        UpdateCommand command = new UpdateCommand(indexLastMeeting, descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_MEETING_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedPerson);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        UpdateCommand command = new UpdateCommand(INDEX_FIRST_MEETING, new UpdateMeetingDescriptor());
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new UserPrefs());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withAgenda(VALID_AGENDA_PROJECT).build();
        UpdateCommand command = new UpdateCommand(INDEX_FIRST_MEETING,
                new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT).build());

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder(firstMeeting).build();
        UpdateCommand command = new UpdateCommand(INDEX_SECOND_MEETING, descriptor);

        assertCommandFailure(command, model, MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        UpdateMeetingDescriptor descriptor = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT)
                .build();
        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of meeting book
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);
        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        UpdateCommand updateCommand = new UpdateCommand(outOfBoundIndex,
                new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT).build());

        assertCommandFailure(updateCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final UpdateCommand standardCommand = new UpdateCommand(INDEX_FIRST_MEETING, DESC_QUARTERLY);

        // same values -> returns true
        UpdateMeetingDescriptor copyDescriptor = new UpdateMeetingDescriptor(DESC_QUARTERLY);
        UpdateCommand commandWithSameValues = new UpdateCommand(INDEX_FIRST_MEETING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_FIRST_MEETING, DESC_PROJECT)));
        //different index -> false
        assertFalse(standardCommand.equals(new UpdateCommand(INDEX_THIRD_MEETING, DESC_QUARTERLY)));
    }

}
