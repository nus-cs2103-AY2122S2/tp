package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showTaskAtIndex;
import static manageezpz.logic.commands.MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS;
import static manageezpz.logic.commands.MarkTaskCommand.MESSAGE_USAGE;
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
 * {@code MarkTaskCommand}.
 */
public class MarkTaskCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBookTasks(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Task markedTask = expectedModel.markTask(taskToMark);

        String expectedMessage = String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToMarkInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        Task markedTask = model.markTask(taskToMarkInFilteredList);

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showTaskAtIndex(expectedModel, INDEX_FIRST);

        assertCommandSuccess(markTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // Ensures that outOfBoundIndex is still in bounds of address book task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        MarkTaskCommand markTaskCommand = new MarkTaskCommand(outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        MarkTaskCommand markFirstTaskCommand = new MarkTaskCommand(INDEX_FIRST);
        MarkTaskCommand markSecondTaskCommand = new MarkTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markFirstTaskCommand.equals(markFirstTaskCommand));

        // same values -> returns true
        MarkTaskCommand markFirstTaskCommandCopy = new MarkTaskCommand(INDEX_FIRST);
        assertTrue(markFirstTaskCommand.equals(markFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(markFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstTaskCommand.equals(markSecondTaskCommand));
    }
}
