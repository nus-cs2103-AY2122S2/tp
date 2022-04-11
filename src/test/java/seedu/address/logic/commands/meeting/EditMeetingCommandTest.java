package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.meeting.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.MeetingBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditMeetingCommand.
 */
public class EditMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        final Meeting editedMeeting = new MeetingBuilder().withModule("CS2103").build();
        final Index modIndex = Index.fromZeroBased(model.getFilteredModuleList().indexOf(editedMeeting.getModule()));
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).withModule(modIndex)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING, descriptor);

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        final Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        final Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        final MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        final Meeting editedMeeting = meetingInList.withName(VALID_NAME_LECTURE).withUrl(VALID_URL_LECTURE)
                .withTags(VALID_TAG_LECTURE).build();

        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .withUrl(VALID_URL_LECTURE).withTags(VALID_TAG_LECTURE).build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, descriptor);

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptor());
        final Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        final Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withName(VALID_NAME_LECTURE)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE).build());

        final String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        final Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        final Index modIndex = Index.fromZeroBased(model.getFilteredModuleList().indexOf(firstMeeting.getModule()));
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(firstMeeting).withModule(modIndex)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND_MEETING, descriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicateMeetingFilteredList_failure() {
        final Meeting meetingInList = model.getFilteredMeetingList().get(INDEX_SECOND_MEETING.getZeroBased());
        final Index modIndex = Index.fromZeroBased(model.getFilteredModuleList().indexOf(meetingInList.getModule()));
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        // edit meeting in filtered list into a duplicate in LinkyTime
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptorBuilder(meetingInList).withModule(modIndex).build());

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_invalidMeetingIndexUnfilteredList_failure() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        final EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE)
                .build();
        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of LinkyTime
     */
    @Test
    public void execute_invalidMeetingIndexFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);
        final Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime meeting list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingList().size());

        final EditMeetingCommand editMeetingCommand = new EditMeetingCommand(outOfBoundIndex,
                new EditMeetingDescriptorBuilder().withName(VALID_NAME_LECTURE).build());

        assertCommandFailure(editMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardCommand = new EditMeetingCommand(INDEX_FIRST_MEETING, DESC_LECTURE);

        // same values -> returns true
        final EditMeetingDescriptor copyDescriptor = new EditMeetingDescriptor(DESC_LECTURE);
        final EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_MEETING,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_SECOND_MEETING, DESC_LECTURE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditMeetingCommand(INDEX_FIRST_MEETING, DESC_TUTORIAL)));
    }
}
