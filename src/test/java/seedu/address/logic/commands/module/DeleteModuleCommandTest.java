package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteModuleCommand}.
 */
public class DeleteModuleCommandTest {
    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getModuleList().size() + 1);
        final DeleteModuleCommand forceDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, true);
        final DeleteModuleCommand normalDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, false);

        // force delete
        assertCommandFailure(forceDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        // normal delete
        assertCommandFailure(normalDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }


    // ------------------------------- NO ASSOCIATED MEETINGS --------------------------------
    @Test
    public void execute_noMeetingsValidIndexUnfilteredListNotForced_success() {
        // the last module in typical LinkyTime is independent
        final Index indexOfIndependentModule = Index.fromOneBased(model.getModuleList().size());
        final Module moduleToDelete = model.getModuleList().get(indexOfIndependentModule.getZeroBased());

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        // normal delete
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(indexOfIndependentModule, false);
        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMeetingsValidIndexUnfilteredListIsForced_success() {
        // the last module in typical LinkyTime is independent
        final Index indexOfIndependentModule = Index.fromOneBased(model.getModuleList().size());
        final Module moduleToDelete = model.getModuleList().get(indexOfIndependentModule.getZeroBased());

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        // force delete
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(indexOfIndependentModule, true);
        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMeetingsValidIndexFilteredListNotForced_success() {
        // the last module in typical LinkyTime is independent
        final Index indexOfIndependentModule = Index.fromOneBased(model.getModuleList().size());
        showModuleAtIndex(model, indexOfIndependentModule);

        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased()); // independent module is now the only module shown
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, false);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        showNoModule(expectedModel);

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMeetingsValidIndexFilteredListIsForced_success() {
        // the last module in typical LinkyTime is independent
        final Index indexOfIndependentModule = Index.fromOneBased(model.getModuleList().size());
        showModuleAtIndex(model, indexOfIndependentModule);

        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased()); // independent module is now the only module shown
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, true);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        showNoModule(expectedModel);

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noMeetingsInvalidIndexFilteredList_throwsCommandException() {
        // the last module in typical LinkyTime is independent
        final Index indexOfIndependentModule = Index.fromOneBased(model.getModuleList().size());
        showModuleAtIndex(model, indexOfIndependentModule);

        final Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().size());

        final DeleteModuleCommand forceDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, true);
        final DeleteModuleCommand nonForceDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, false);

        assertCommandFailure(forceDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        assertCommandFailure(nonForceDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    // ------------------------------- HAVE ASSOCIATED MEETINGS --------------------------------
    @Test
    public void execute_haveMeetingsValidIndexUnfilteredListNotForced_throwsCommandException() {
        // first module in typical LinkyTime has associated meeting
        final Module moduleToDelete = model.getModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_RESTRICTED, moduleToDelete);

        // normal delete
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, false);
        assertCommandFailure(deleteModuleCommand, model, expectedMessage);
    }

    @Test
    public void execute_haveMeetingsValidIndexUnfilteredListIsForced_success() {
        // first module in typical LinkyTime has associated meeting
        final Module moduleToDelete = model.getModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final ModelManager expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        final List<Meeting> meetingsToDelete = new ArrayList<>(expectedModel.getMeetingList()
                .filtered(meeting -> meeting.getModule().equals(moduleToDelete)));
        for (Meeting m : meetingsToDelete) {
            expectedModel.deleteMeeting(m);
        }

        // force delete
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, true);
        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_haveMeetingsValidIndexFilteredListNotForced_throwsCommandException() {
        // first module in typical LinkyTime has associated meeting
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, false);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_RESTRICTED, moduleToDelete);

        assertCommandFailure(deleteModuleCommand, model, expectedMessage);
    }

    @Test
    public void execute_haveMeetingsValidIndexFilteredListIsForced_success() {
        // first module in typical LinkyTime has associated meeting
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        final Module moduleToDelete = model.getFilteredModuleList()
                .get(INDEX_FIRST_MODULE.getZeroBased());
        final DeleteModuleCommand deleteModuleCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, true);

        final String expectedMessage =
                String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        final Model expectedModel = new ModelManager(model.getLinkyTime(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);
        showNoModule(expectedModel);
        final List<Meeting> meetingsToDelete = new ArrayList<>(model.getMeetingList()
                .filtered(meeting -> meeting.getModule().equals(moduleToDelete)));
        for (Meeting m : meetingsToDelete) {
            expectedModel.deleteMeeting(m);
        }

        assertCommandSuccess(deleteModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_haveMeetingsInvalidIndexFilteredList_throwsCommandException() {
        // first module in typical LinkyTime has associated meeting
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        final Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getModuleList().size());

        final DeleteModuleCommand forceDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, true);
        final DeleteModuleCommand nonForceDeleteCommand = new DeleteModuleCommand(outOfBoundIndex, false);

        assertCommandFailure(forceDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        assertCommandFailure(nonForceDeleteCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }


    // ------------------------------------- other tests ------------------------------
    @Test
    public void equals() {
        final DeleteModuleCommand deleteFirstCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, false);
        final DeleteModuleCommand deleteSecondCommand = new DeleteModuleCommand(INDEX_SECOND_MODULE, false);
        final DeleteModuleCommand deleteThirdCommand = new DeleteModuleCommand(INDEX_FIRST_MODULE, true);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        final DeleteModuleCommand deleteFirstCommandCopy = new DeleteModuleCommand(INDEX_FIRST_MODULE, false);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different isForced value -> returns false
        assertFalse(deleteFirstCommand.equals(deleteThirdCommand));
    }


    /**
     * Updates {@code model}'s filtered list to show no modules.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
