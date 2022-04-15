package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPLETION_STATUS_TRUE;
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
 * {@code MarkCommand}.
 */
public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        //task to mark
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        //marked task
        Task markedTask = new TaskBuilder(taskToMark).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        List<Task> markedTaskList = List.of(markedTask);

        Index index = Index.fromOneBased(INDEX_FIRST_TASK.getOneBased());
        List<Index> indexList = List.of(index);

        MarkCommand markCommand = new MarkCommand(indexList);

        String expectedMessage = getExpectedSuccessMessage(markedTaskList, indexList);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), markedTask);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        //task to mark
        Task taskToMark1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task taskToMark2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());

        //marked task
        Task markedTask1 = new TaskBuilder(taskToMark1).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        Task markedTask2 = new TaskBuilder(taskToMark2).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        List<Task> markedTasksList = List.of(markedTask1, markedTask2);

        Index index1 = Index.fromOneBased(INDEX_FIRST_TASK.getOneBased());
        Index index2 = Index.fromOneBased(INDEX_SECOND_TASK.getOneBased());
        List<Index> indexesList = List.of(index1, index2);

        MarkCommand markCommand = new MarkCommand(indexesList);

        String expectedMessage = getExpectedSuccessMessage(markedTasksList, indexesList);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), markedTask1);
        expectedModel.strictSetTask(model.getFilteredTaskList().get(1), markedTask2);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> alreadyMarkedIndexList = List.of();
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);
        MarkCommand markCommand = new MarkCommand(outOfBoundIndexList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyMarkedIndexList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexesUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex1 = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        Index outOfBoundIndex2 = Index.fromOneBased(model.getFilteredTaskList().size() + 2);
        List<Index> alreadyMarkedIndexesList = List.of();
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex1, outOfBoundIndex2);

        MarkCommand markCommand = new MarkCommand(outOfBoundIndexesList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedIndexesList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validCompletedTaskUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to true
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());

        assertEquals(taskToMark.getCompletionStatus().value, "true");

        List<Index> outOfBoundIndexes = List.of();
        Index alreadyMarkedIndex = Index.fromOneBased(INDEX_FIFTH_TASK.getOneBased());
        List<Index> alreadyMarkedIndexList = List.of(alreadyMarkedIndex);

        MarkCommand markCommand = new MarkCommand(alreadyMarkedIndexList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexes, alreadyMarkedIndexList);
        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validCompletedTasksUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to true
        Task taskToMark1 = model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());
        Task taskToMark2 = model.getFilteredTaskList().get(INDEX_SIXTH_TASK.getZeroBased());

        assertEquals(taskToMark1.getCompletionStatus().value, "true");
        assertEquals(taskToMark2.getCompletionStatus().value, "true");

        List<Index> outOfBoundIndexesList = List.of();
        Index index1 = Index.fromOneBased(INDEX_FIFTH_TASK.getOneBased());
        Index index2 = Index.fromOneBased(INDEX_SIXTH_TASK.getOneBased());
        List<Index> alreadyMarkedIndexesList = List.of(index1, index2);

        MarkCommand markCommand = new MarkCommand(alreadyMarkedIndexesList);

        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedIndexesList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexAndCompletedTaskUnfilteredList_throwsCommandException() {
        // INDEX SECOND TASK: default completion status set to true
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());

        assertEquals(taskToMark.getCompletionStatus().value, "true");

        Index alreadyMarkedIndex = Index.fromOneBased(INDEX_FIFTH_TASK.getOneBased());
        List<Index> alreadyMarkedIndexesList = List.of(alreadyMarkedIndex);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);

        List<Index> indexesList = List.of(alreadyMarkedIndex, outOfBoundIndex);
        MarkCommand markCommand = new MarkCommand(indexesList);

        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedIndexesList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndInvalidIndexUnfilteredList_throwsCommandException() {
        List<Index> alreadyMarkedTasksList = List.of();

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);

        //task to mark
        Index taskToMarkIndex = INDEX_FIRST_TASK;

        List<Index> indexes = List.of(taskToMarkIndex, outOfBoundIndex);

        MarkCommand markCommand = new MarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyMarkedTasksList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndAlreadyMarkedIndexUnfilteredList_throwsCommandException() {
        Index alreadyMarkedIndex = INDEX_FIFTH_TASK;
        List<Index> alreadyMarkedIndexList = List.of(alreadyMarkedIndex);

        List<Index> outOfBoundIndexList = List.of();

        //task to mark
        Index taskToMarkIndex = INDEX_FIRST_TASK;

        List<Index> indexes = List.of(taskToMarkIndex, alreadyMarkedIndex);

        MarkCommand markCommand = new MarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyMarkedIndexList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexAndAlreadyMarkedIndexAndInvalidIndexUnfilteredList_throwsCommandException() {
        Index alreadyMarkedIndex = INDEX_FIFTH_TASK;
        List<Index> alreadyMarkedIndexList = List.of(alreadyMarkedIndex);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        List<Index> outOfBoundIndexList = List.of(outOfBoundIndex);

        //task to mark
        Index taskToMarkIndex = INDEX_FIRST_TASK;

        List<Index> indexes = List.of(taskToMarkIndex, alreadyMarkedIndex, outOfBoundIndex);

        MarkCommand markCommand = new MarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexList, alreadyMarkedIndexList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        List<Index> indexes = List.of(Index.fromZeroBased(0));
        Task taskToMark = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task markedTask = new TaskBuilder(taskToMark).withCompletionStatus(VALID_COMPLETION_STATUS_TRUE).build();
        List<Task> markedTaskList = List.of(markedTask);

        MarkCommand markCommand = new MarkCommand(indexes);

        String expectedMessage = getExpectedSuccessMessage(markedTaskList, indexes);

        ModelManager expectedModel = new ModelManager(model.getTaskList(), new UserPrefs());
        expectedModel.strictSetTask(model.getFilteredTaskList().get(0), markedTask);

        showTaskAtIndex(model, INDEX_FIRST_TASK);
        showTaskAtIndex(expectedModel, INDEX_FIRST_TASK);

        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        List<Index> alreadyMarkedTasksList = List.of();

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        MarkCommand markCommand = new MarkCommand(outOfBoundIndexesList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedTasksList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_alreadyMarkedIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIFTH_TASK);
        List<Index> alreadyMarkedTasksList = List.of(Index.fromZeroBased(0));
        List<Index> outOfBoundIndexesList = List.of();

        MarkCommand markCommand = new MarkCommand(alreadyMarkedTasksList);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedTasksList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexAndAlreadyMarkedIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIFTH_TASK);
        Index alreadyMarkedIndex = Index.fromZeroBased(0);
        List<Index> alreadyMarkedIndexList = List.of(alreadyMarkedIndex);
        Index outOfBoundIndex = Index.fromZeroBased(1);
        List<Index> outOfBoundIndexesList = List.of(outOfBoundIndex);
        List<Index> indexes = List.of(alreadyMarkedIndex, outOfBoundIndex);

        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        MarkCommand markCommand = new MarkCommand(indexes);
        String expectedMessage = getExpectedErrorMessage(outOfBoundIndexesList, alreadyMarkedIndexList);

        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(List.of(INDEX_FIRST_TASK));
        MarkCommand markSecondCommand = new MarkCommand(List.of(INDEX_SECOND_TASK));
        MarkCommand markFirstAndSecondCommand = new MarkCommand(List.of(INDEX_FIRST_TASK, INDEX_SECOND_TASK));
        MarkCommand markSecondAndFirstCommand = new MarkCommand(List.of(INDEX_SECOND_TASK, INDEX_FIRST_TASK));

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));
        assertTrue(markFirstAndSecondCommand.equals(markFirstAndSecondCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(List.of(INDEX_FIRST_TASK));
        MarkCommand markFirstAndSecondCommandCopy = new MarkCommand(List.of(INDEX_FIRST_TASK, INDEX_SECOND_TASK));
        MarkCommand markSecondAndFirstCommandCopy = new MarkCommand(List.of(INDEX_SECOND_TASK, INDEX_FIRST_TASK));
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));
        assertTrue(markFirstAndSecondCommand.equals(markSecondAndFirstCommand));
        assertTrue(markFirstAndSecondCommand.equals(markFirstAndSecondCommandCopy));
        assertTrue(markSecondAndFirstCommand.equals(markSecondAndFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));
        assertFalse(markFirstAndSecondCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));
        assertFalse(markFirstAndSecondCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
        assertFalse(markFirstAndSecondCommand.equals(markFirstCommand));
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
    private String getExpectedSuccessMessage(List<Task> markedTasks, List<Index> markedTasksIndexes) {
        String str = "Successfully Marked Task(s): \n";
        for (int i = 0; i < markedTasks.size(); i++) {
            str += markedTasksIndexes.get(i).getOneBased() + ". " + markedTasks.get(i) + "\n";
        }
        return str;
    }

    /**
     * Returns the expected error message.
     */
    private String getExpectedErrorMessage(List<Index> outOfBoundsIndexes, List<Index> alreadyMarkedIndexes) {
        StringBuilder errorString = new StringBuilder();

        if (!alreadyMarkedIndexes.isEmpty()) {
            if (alreadyMarkedIndexes.size() == 1) {
                errorString.append("Index " + indexesToString(alreadyMarkedIndexes) + ": "
                        + MarkCommand.MESSAGE_TASK_ALREADY_COMPLETED + "\n");
            } else {
                errorString.append("Indexes " + indexesToString(alreadyMarkedIndexes) + ": "
                        + MarkCommand.MESSAGE_MULTIPLE_TASKS_ALREADY_COMPLETED + "\n");
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
