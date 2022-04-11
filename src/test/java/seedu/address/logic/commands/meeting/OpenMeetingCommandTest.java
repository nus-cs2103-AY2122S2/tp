package seedu.address.logic.commands.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.logic.commands.meeting.OpenMeetingCommand.MESSAGE_INVALID_URL;
import static seedu.address.logic.commands.meeting.OpenMeetingCommand.MESSAGE_SYSTEM_BROWSER_ERROR;
import static seedu.address.logic.commands.meeting.OpenMeetingCommand.MESSAGE_SYSTEM_PERMISSION_DENIED;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.UrlOpenerStub;
import seedu.address.logic.commands.UrlOpenerStubThrowsIoException;
import seedu.address.logic.commands.UrlOpenerStubThrowsSecurityException;
import seedu.address.logic.commands.UrlOpenerStubThrowsUriSyntaxException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UrlOpener;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code OpenMeetingCommand}.
 * <p>
 * NOTE: Tests should not be run in an environment that does not support {@code java.awt.Desktop} operations. <br>
 * NOTE: Unable to test for unsupported systems, browser error and permission denied scenarios.
 */
public class OpenMeetingCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
    private final UrlOpener urlOpenerStubThrowsIoException = new UrlOpenerStubThrowsIoException();

    @Test
    public void executeWithUrlOpener_validIndexUnfilteredList_success() {
        final Meeting meetingToOpen = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(OpenMeetingCommand.MESSAGE_SUCCESS, meetingToOpen);

        // OpenMeetingCommand::execute should not modify model
        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        CommandResult commandResult;
        try {
            commandResult = openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStub());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, expectedCommandResult);
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_invalidIndexUnfilteredList_throwsCommandException() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(outOfBoundIndex);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () ->
                openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStub()));
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_SECOND_MEETING);

        final Meeting meetingToOpen = model.getFilteredMeetingList()
                .get(INDEX_FIRST_MEETING.getZeroBased());
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);

        final String expectedMessage =
                String.format(OpenMeetingCommand.MESSAGE_SUCCESS, meetingToOpen);

        // OpenMeetingCommand::execute should not modify model
        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        showMeetingAtIndex(expectedModel, INDEX_SECOND_MEETING);

        CommandResult commandResult;
        try {
            commandResult = openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStub());
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(commandResult, expectedCommandResult);
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        final Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getMeetingList().size());

        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(outOfBoundIndex);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        showMeetingAtIndex(expectedModel, INDEX_FIRST_MEETING);

        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
        assertThrows(CommandException.class, expectedMessage, () ->
                openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStub()));
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_urlOpenerStubThrowsUriSyntaxException_throwsCommandException() {
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        assertThrows(CommandException.class, MESSAGE_INVALID_URL, () ->
                openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStubThrowsUriSyntaxException()));
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_urlOpenerStubThrowsIoException_throwsCommandException() {
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        assertThrows(CommandException.class, MESSAGE_SYSTEM_BROWSER_ERROR, () ->
                openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStubThrowsIoException()));
        assertEquals(model, expectedModel);
    }

    @Test
    public void executeWithUrlOpener_urlOpenerStubThrowsSecurityException_throwsCommandException() {
        final OpenMeetingCommand openMeetingCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());

        assertThrows(CommandException.class, MESSAGE_SYSTEM_PERMISSION_DENIED, () ->
                openMeetingCommand.executeWithUrlOpener(model, new UrlOpenerStubThrowsSecurityException()));
        assertEquals(model, expectedModel);
    }

    @Test
    public void equals() {
        final OpenMeetingCommand openFirstCommand = new OpenMeetingCommand(INDEX_FIRST_MEETING);
        final OpenMeetingCommand openSecondCommand = new OpenMeetingCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(openFirstCommand.equals(openFirstCommand));

        // same values -> returns true
        final OpenMeetingCommand openFirstCommandCopy = new OpenMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(openFirstCommand.equals(openFirstCommandCopy));

        // different types -> returns false
        assertFalse(openFirstCommand.equals(1));

        // null -> returns false
        assertFalse(openFirstCommand.equals(null));

        // different meeting -> returns false
        assertFalse(openFirstCommand.equals(openSecondCommand));
    }
}
