package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meeting.AddMeetingCommand.AddMeetingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.meeting.AddMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.MeetingBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddMeetingCommand}.
 */
public class AddMeetingCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
    }

    @Test
    public void execute_newMeeting_success() {
        final Meeting validMeeting = new MeetingBuilder().build();

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);
        model.addModule(validMeeting.getModule()); // add the module into model
        expectedModel.addModule(validMeeting.getModule()); // add the module into the expected model

        final AddMeetingDescriptorBuilder addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(validMeeting);
        // to find the index of the module
        final Index modIndex = Index.fromZeroBased(model.getModuleList().indexOf(validMeeting.getModule()));
        addMeetingDescriptorBuilder.withModule(modIndex);
        final AddMeetingDescriptor addMeetingDescriptor = addMeetingDescriptorBuilder.build();
        assertCommandSuccess(new AddMeetingCommand(addMeetingDescriptor), model,
                String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), expectedModel);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        final Meeting validMeeting = new MeetingBuilder().build();
        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.addMeeting(validMeeting);

        final Meeting meetingInList = model.getLinkyTime().getMeetingList().get(0);
        final AddMeetingDescriptorBuilder addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(meetingInList);

        // to find the index of the module
        final Index modIndex = Index.fromZeroBased(model.getModuleList().indexOf(meetingInList.getModule()));
        addMeetingDescriptorBuilder.withModule(modIndex);
        assertCommandFailure(new AddMeetingCommand(addMeetingDescriptorBuilder.build()),
                expectedModel, AddMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

}
