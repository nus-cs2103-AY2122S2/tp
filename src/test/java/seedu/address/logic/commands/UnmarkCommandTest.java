package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkCommand}.
 */
public class UnmarkCommandTest {

    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //task to unmark
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());

        //unmarked task
        Task unmarkedTask = new TaskBuilder(taskToUnmark).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
        List<Task> unmarkedTaskList = List.of(unmarkedTask);

        Index index = Index.fromOneBased(INDEX_FIFTH_TASK.getOneBased());
        List<Index> indexList = List.of(index);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexList);

        String expectedMessage = getExpectedSuccessMessage(unmarkedTaskList, indexList);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(4), unmarkedTask);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        //task to unmark
        Task taskToUnmark1 = model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());
        Task taskToUnmark2 = model.getFilteredTaskList().get(INDEX_SIXTH_TASK.getZeroBased());

        //unmarked task
        Task unmarkedTask1 = new TaskBuilder(taskToUnmark1).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
        Task unmarkedTask2 = new TaskBuilder(taskToUnmark2).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
        List<Task> unmarkedTasksList = List.of(unmarkedTask1, unmarkedTask2);

        Index index1 = Index.fromOneBased(INDEX_FIFTH_TASK.getOneBased());
        Index index2 = Index.fromOneBased(INDEX_SIXTH_TASK.getOneBased());
        List<Index> indexesList = List.of(index1, index2);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexesList);

        String expectedMessage = getExpectedSuccessMessage(unmarkedTasksList, indexesList);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(4), unmarkedTask1);
        expectedModel.strictSetTask(model.getFilteredTaskList().get(5), unmarkedTask2);

        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> alreadyUnmarkedIndexList = List.of();
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);
        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndexList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyUnmarkedIndexList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexesUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        List<Index> alreadyUnmarkedIndexesList = List.of();
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex1, outOfBoundIndex2);

        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndexesList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedIndexesList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validCompletedTaskUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to false
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        assertEquals(taskToUnmark.getCompletionStatus().value, "false");

        List<Index> outOfBoundIndexes = List.of();
        Index alreadyUnmarkedIndex = Index.fromOneBased(INDEX_FIRST_TASK.getOneBased());
        List<Index> alreadyUnmarkedIndexList = List.of(alreadyUnmarkedIndex);

        UnmarkCommand unmarkCommand = new UnmarkCommand(alreadyUnmarkedIndexList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexes, alreadyUnmarkedIndexList);
        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validCompletedTasksUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to false
        Task taskToUnmark1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskToUnmark2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());

        assertEquals(taskToUnmark1.getCompletionStatus().value, "false");
        assertEquals(taskToUnmark2.getCompletionStatus().value, "false");

        List<Index> outOfBoundIndexesList = List.of();
        Index index1 = Index.fromOneBased(INDEX_FIRST_TASK.getOneBased());
        Index index2 = Index.fromOneBased(INDEX_SECOND_TASK.getOneBased());
        List<Index> alreadyUnmarkedIndexesList = List.of(index1, index2);

        UnmarkCommand unmarkCommand = new UnmarkCommand(alreadyUnmarkedIndexesList);

        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedIndexesList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexAndCompletedTaskUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to false
        Task taskToUnmark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        assertEquals(taskToUnmark.getCompletionStatus().value, "false");

        Index alreadyUnmarkedIndex = Index.fromOneBased(INDEX_FIRST_TASK.getOneBased());
        List<Index> alreadyUnmarkedIndexesList = List.of(alreadyUnmarkedIndex);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);

        List<Index> indexesList = List.of(alreadyUnmarkedIndex, outOfBoundIndex);
        UnmarkCommand unmarkCommand = new UnmarkCommand(indexesList);

        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedIndexesList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndInvalidIndexUnfilteredList_throwsCommandException() {
        List<Index> alreadyUnmarkedTasksList = List.of();

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);

        //task to unmark
        Index taskToUnmarkIndex = INDEX_FIFTH_TASK;

        List<Index> indexes = List.of(taskToUnmarkIndex, outOfBoundIndex);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyUnmarkedTasksList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndAlreadyUnmarkedIndexUnfilteredList_throwsCommandException() {
        Index alreadyUnmarkedIndex = INDEX_FIRST_TASK;
        List<Index> alreadyUnmarkedIndexList = List.of(alreadyUnmarkedIndex);

        List<Index> outOfBoundIndexList = List.of();

        //task to unmark
        Index taskToUnmarkIndex = INDEX_FIFTH_TASK;

        List<Index> indexes = List.of(taskToUnmarkIndex, alreadyUnmarkedIndex);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyUnmarkedIndexList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndAlreadyUnmarkedIndexAndInvalidIndexUnfilteredList_throwsCommandException() {
        Index alreadyUnmarkedIndex = INDEX_FIRST_TASK;
        List<Index> alreadyUnmarkedIndexList = List.of(alreadyUnmarkedIndex);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);

        //task to unmark
        Index taskToUnmarkIndex = INDEX_FIFTH_TASK;

        List<Index> indexes = List.of(taskToUnmarkIndex, alreadyUnmarkedIndex, outOfBoundIndex);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyUnmarkedIndexList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIFTH_TASK);

        List<Index> indexes = List.of(Index.fromZeroBased(0));
        Task taskToUnmark = model.getFilteredTaskList().get(0);
        Task unmarkedTask = new TaskBuilder(taskToUnmark).withCompletionStatus(VALID_COMPLETION_STATUS_FALSE).build();
        List<Task> unmarkedTaskList = List.of(unmarkedTask);

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexes);

        String expectedMessage = getExpectedSuccessMessage(unmarkedTaskList, indexes);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), unmarkedTask);

        showTaskAtIndex(expectedModel, INDEX_FIFTH_TASK);
        assertCommandSuccess(unmarkCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        List<Index> alreadyUnmarkedTasksList = List.of();

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        UnmarkCommand unmarkCommand = new UnmarkCommand(outOfBoundIndexesList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedTasksList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_alreadyUnmarkedIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        List<Index> alreadyUnmarkedTasksList = List.of(Index.fromZeroBased(0));
        List<Index> outOfBoundIndexesList = List.of();

        UnmarkCommand unmarkCommand = new UnmarkCommand(alreadyUnmarkedTasksList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedTasksList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexAndAlreadyUnmarkedIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index alreadyUnmarkedIndex = Index.fromZeroBased(0);
        List<Index> alreadyUnmarkedIndexList = List.of(alreadyUnmarkedIndex);
        Index outOfBoundIndex = Index.fromZeroBased(1);
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);
        List<Index> indexes = List.of(alreadyUnmarkedIndex, outOfBoundIndex);

        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        UnmarkCommand unmarkCommand = new UnmarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyUnmarkedIndexList);

        assertCommandFailure(unmarkCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(List.of(INDEX_FIRST_TASK));
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(List.of(INDEX_SECOND_TASK));
        UnmarkCommand unmarkFirstAndSecondCommand = new UnmarkCommand(List.of(INDEX_FIRST_TASK, INDEX_SECOND_TASK));
        UnmarkCommand unmarkSecondAndFirstCommand = new UnmarkCommand(List.of(INDEX_SECOND_TASK, INDEX_FIRST_TASK));

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));
        assertTrue(unmarkFirstAndSecondCommand.equals(unmarkFirstAndSecondCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(List.of(INDEX_FIRST_TASK));
        UnmarkCommand unmarkFirstAndSecondCommandCopy = new UnmarkCommand(List.of(INDEX_FIRST_TASK, INDEX_SECOND_TASK));
        UnmarkCommand unmarkSecondAndFirstCommandCopy = new UnmarkCommand(List.of(INDEX_SECOND_TASK, INDEX_FIRST_TASK));
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));
        assertTrue(unmarkFirstAndSecondCommand.equals(unmarkSecondAndFirstCommand));
        assertTrue(unmarkFirstAndSecondCommand.equals(unmarkFirstAndSecondCommandCopy));
        assertTrue(unmarkSecondAndFirstCommand.equals(unmarkSecondAndFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));
        assertFalse(unmarkFirstAndSecondCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));
        assertFalse(unmarkFirstAndSecondCommand.equals(null));

        // different task -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
        assertFalse(unmarkFirstAndSecondCommand.equals(unmarkFirstCommand));
    }

    @Test
    private String indexesToString(List<Index> indexes) {
        StringBuilder str = new StringBuilder();
        if (indexes.size() > 1) {
            for (int i = indexes.size() - 1; i >= 1; i--) {
                str.append(indexes.get(i).getOneBased());
                str.append(i == 1 ? " and " : ", ");
            }
        }
        str.append(indexes.get(0).getOneBased());
        return str.toString();
    }

    @Test
    private String getExpectedSuccessMessage(List<Task> unmarkedTasks, List<Index> unmarkedTasksIndexes) {
        String str = "Successfully Unmarked Task(s): \n";
        for (int i = 0; i < unmarkedTasks.size(); i++) {
            str += unmarkedTasksIndexes.get(i).getOneBased() + ". " + unmarkedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Returns the expected error message.
     */
    private String getExpectedErrorMessage(List<Index> outOfBoundsIndexes, List<Index> alreadyUnmarkedIndexes) {
        StringBuilder errorString = new StringBuilder();

        if (!alreadyUnmarkedIndexes.isEmpty()) {
            if (alreadyUnmarkedIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + UnmarkCommand.MESSAGE_TASK_ALREADY_UNCOMPLETED + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(alreadyUnmarkedIndexes) + ": "
                        + UnmarkCommand.MESSAGE_MULTIPLE_TASKS_ALREADY_UNCOMPLETED + "\n");
            }
        }

        if (!outOfBoundsIndexes.isEmpty()) {
            if (outOfBoundsIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(outOfBoundsIndexes) + ": "
                        + Messages.MESSAGE_INVALID_MULTIPLE_TASK_DISPLAYED_INDEX + "\n");
            }
        }

        return errorString.toString();
    }
}
