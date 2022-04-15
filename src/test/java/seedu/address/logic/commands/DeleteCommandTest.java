package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showTasksAtIndexes;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        List<Index> listOfIndexToDelete = List.of(INDEX_FIRST_TASK);
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        List<Task> listOfTaskToDelete = List.of(taskToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedSuccessMessage(listOfTaskToDelete, listOfIndexToDelete);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleValidIndexUnfilteredList_success() {
        List<Index> listOfIndexToDelete = Arrays.asList(INDEX_SECOND_TASK, INDEX_FIRST_TASK);
        Task firstTaskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task secondTaskToDelete = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        List<Task> listOfTaskToDelete = Arrays.asList(secondTaskToDelete, firstTaskToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedSuccessMessage(listOfTaskToDelete, listOfIndexToDelete);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(secondTaskToDelete);
        expectedModel.deleteTask(firstTaskToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> listOfIndexToDelete = List.of(outOfBoundIndex);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedErrorMessage(listOfIndexToDelete);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_multipleInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        List<Index> listOfOutOfBoundIndex = Arrays.asList(outOfBoundIndex1, outOfBoundIndex2);

        DeleteCommand deleteCommand = new DeleteCommand(listOfOutOfBoundIndex);

        String expectedMessage = getExpectedErrorMessage(listOfOutOfBoundIndex);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validAndMultipleInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        List<Index> listOfOutOfBoundIndex = Arrays.asList(outOfBoundIndex1, outOfBoundIndex2);
        List<Index> listOfIndexToDelete = Arrays.asList(INDEX_FIRST_TASK, outOfBoundIndex1, outOfBoundIndex2);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedErrorMessage(listOfOutOfBoundIndex);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        List<Index> listOfIndexToDelete = List.of(INDEX_FIRST_TASK);
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        List<Task> listOfTaskToDelete = List.of(taskToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedSuccessMessage(listOfTaskToDelete, listOfIndexToDelete);

        Model expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleValidIndexFilteredList_success() {
        showTasksAtIndexes(model, INDEX_FIRST_TASK, INDEX_SECOND_TASK);

        List<Index> listOfIndexToDelete = Arrays.asList(INDEX_SECOND_TASK, INDEX_FIRST_TASK);
        Task firstTaskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task secondTaskToDelete = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        List<Task> listOfTaskToDelete = Arrays.asList(secondTaskToDelete, firstTaskToDelete);

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedSuccessMessage(listOfTaskToDelete, listOfIndexToDelete);

        Model expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(firstTaskToDelete);
        expectedModel.deleteTask(secondTaskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        DeleteCommand deleteCommand = new DeleteCommand(List.of(outOfBoundIndex));

        String expectedMessage = getExpectedErrorMessage(List.of(outOfBoundIndex));
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_multipleInvalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        List<Index> listOfOutOfBoundIndex = Arrays.asList(INDEX_SECOND_TASK, INDEX_THIRD_TASK);
        // ensures that outOfBoundIndexes are still in bounds of task list
        assertTrue(INDEX_SECOND_TASK.getZeroBased() < model.getTaskList().getTaskList().size());
        assertTrue(INDEX_THIRD_TASK.getZeroBased() < model.getTaskList().getTaskList().size());

        DeleteCommand deleteCommand = new DeleteCommand(listOfOutOfBoundIndex);

        String expectedMessage = getExpectedErrorMessage(listOfOutOfBoundIndex);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_validAndMultipleInvalidIndexFilteredList_throwsCommandException() {
        showTasksAtIndexes(model, INDEX_FIRST_TASK, INDEX_SECOND_TASK);

        List<Index> listOfIndexToDelete = Arrays.asList(INDEX_FIRST_TASK, INDEX_THIRD_TASK, INDEX_FIFTH_TASK);
        List<Index> listOfOutOfBoundIndex = Arrays.asList(INDEX_THIRD_TASK, INDEX_FIFTH_TASK);
        // ensures that outOfBoundIndexes are still in bounds of task list
        assertTrue(INDEX_THIRD_TASK.getZeroBased() < model.getTaskList().getTaskList().size());
        assertTrue(INDEX_FIFTH_TASK.getZeroBased() < model.getTaskList().getTaskList().size());

        DeleteCommand deleteCommand = new DeleteCommand(listOfIndexToDelete);

        String expectedMessage = getExpectedErrorMessage(listOfOutOfBoundIndex);
        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(List.of(INDEX_FIRST_TASK));
        DeleteCommand deleteSecondCommand = new DeleteCommand(List.of(INDEX_SECOND_TASK));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(List.of(INDEX_FIRST_TASK));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }

    /**
     * Returns the expected success message.
     */
    private String getExpectedSuccessMessage(List<Task> deletedTasks, List<Index> deletedTasksIndexes) {
        String str = "Successfully Deleted Tasks: \n";
        for (int i = deletedTasks.size() - 1; i >= 0; i--) {
            str += deletedTasksIndexes.get(i).getOneBased() + ". " + deletedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Returns the expected error message.
     */
    private String getExpectedErrorMessage(List<Index> outOfBoundsIndexes) {
        return String.format("Index %s: %s\n", indexesToString(outOfBoundsIndexes),
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Converts a list of indexes to a string.
     */
    private String indexesToString(List<Index> indexes) {
        StringBuilder str = new StringBuilder();
        str.append(indexes.get(0).getOneBased());
        if (indexes.size() > 1) {
            for (int i = 1; i < indexes.size(); i++) {
                str.append(i == 1 ? " and " : ", ");
                str.append(indexes.get(i).getOneBased());
            }
        }
        return str.toString();
    }
}
