package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.meeting.MeetingUtil;

public class ArchiveMeetingCommandTest {
    @Test
    public void execute_typicalList_showsNonDefaultList() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model defaultModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

        assertCommandSuccess(new ArchiveMeetingCommand(), model,
                new CommandResult(ArchiveMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.ARCHIVE), model);
        assertFalse(model.equals(defaultModel));
    }

    @Test
    public void execute_listIsFiltered_showsIdenticalList() {
        Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

        model.updateFilteredMeetingList(x -> false);
        expectedModel.showCompletedMeetings(true);

        assertCommandSuccess(new ArchiveMeetingCommand(),
                model, new CommandResult(ArchiveMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.ARCHIVE), expectedModel);
    }

    @Test
    public void execute_listHasOneCompletedMeeting_showsEverything() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(-1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(true);

        assertCommandSuccess(new ArchiveMeetingCommand(), model,
                new CommandResult(ArchiveMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.ARCHIVE), expectedModel);
        assertEquals(model.getMeetingList().size(), model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_listHasOneUncompletedMeeting_showsNothing() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(true);

        assertCommandSuccess(new ArchiveMeetingCommand(), model,
                new CommandResult(ArchiveMeetingCommand.MESSAGE_SUCCESS,
                        CommandResult.MeetingListStatusResult.ARCHIVE), expectedModel);
        assertEquals(0, model.getFilteredMeetingList().size());
    }

    @Test
    public void execute_listHasOneCompletedAndUncompletedMeeting_showsOneMeeting() {
        Model model = new ModelManager();
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(1));
        model.addMeeting(MeetingUtil.getRelativeNonRecurringMeeting(-1));

        Model expectedModel = new ModelManager(model.getLinkyTime(), model.getUserPrefs());
        expectedModel.showCompletedMeetings(true);

        assertCommandSuccess(new ArchiveMeetingCommand(), model,
                new CommandResult(ArchiveMeetingCommand.MESSAGE_SUCCESS,
                CommandResult.MeetingListStatusResult.ARCHIVE), expectedModel);
        assertEquals(1, model.getFilteredMeetingList().size());
    }
}
