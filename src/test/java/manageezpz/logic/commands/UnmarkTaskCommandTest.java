package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showTaskAtIndex;
import static manageezpz.logic.commands.UnmarkTaskCommand.MESSAGE_UNMARK_TASK_SUCCESS;
import static manageezpz.logic.commands.UnmarkTaskCommand.MESSAGE_USAGE;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalIndexes.INDEX_SECOND;
import static manageezpz.testutil.TypicalTasks.getTypicalAddressBookTasks;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkTaskCommand}.
 */
public class UnmarkTaskCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Task unmarkedTask = expectedModel.unmarkTask(taskToUnmark);

        String expectedMessage = String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToUnmarkInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        Task unmarkedTask = model.unmarkTask(taskToUnmarkInFilteredList);

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST);

        assertCommandSuccess(unmarkTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // Ensures that outOfBoundIndex is still in bounds of address book task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        UnmarkTaskCommand unmarkTaskCommand = new UnmarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(unmarkTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        UnmarkTaskCommand unmarkFirstTaskCommand = new UnmarkTaskCommand(INDEX_FIRST);
        UnmarkTaskCommand unmarkSecondTaskCommand = new UnmarkTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(unmarkFirstTaskCommand.equals(unmarkFirstTaskCommand));

        // same values -> returns true
        UnmarkTaskCommand markFirstTaskCommandCopy = new UnmarkTaskCommand(INDEX_FIRST);
        assertTrue(unmarkFirstTaskCommand.equals(markFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(unmarkFirstTaskCommand.equals(unmarkSecondTaskCommand));
    }
}
