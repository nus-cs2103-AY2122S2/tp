package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.meeting.MeetingUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListMeetingCommand.
 */
public class ListMeetingCommandTest {

    @Test
    public void execute_typicalList_showsDefaultList() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model defaultModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), defaultModel);
    }

    @Test
    public void execute_listIsFiltered_showsIdenticalList() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        model.updateFilteredMeetingList(x -> false);
        expectedModel.showCompletedMeetings(false);

        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
    }

    @Test
    public void execute_listHasOneCompletedMeeting_showsNothing() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(-1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(false);

        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
        assertEquals(0, model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_listHasOneUncompletedMeeting_showsEverything() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(false);

        assertCommandSuccess(new ListMeetingCommand(), model,
                new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                        CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
        assertEquals(model.getMeetingList().size(), model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_listHasOneCompletedAndUncompletedMeeting_showsOneMeeting() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(1));
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(-1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(false);

        assertCommandSuccess(new ListMeetingCommand(), model, new CommandResult(ListMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.UPCOMING), expectedModel);
        assertEquals(1, model.getFilteredMeetingList().size());
    }
}
