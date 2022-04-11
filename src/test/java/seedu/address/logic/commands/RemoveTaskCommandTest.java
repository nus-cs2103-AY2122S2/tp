package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tasks.Task;
import seedu.address.testutil.TaskBuilder;

public class RemoveTaskCommandTest {
    private Model model = new ModelManager(new ContactList(), new UserPrefs(), getTypicalTaskList());

    @Test
    public void execute_validIndex_success() {
        model = new ModelManager(new ContactList(), new UserPrefs(), getTypicalTaskList());
        Task taskToDelete = new TaskBuilder(HOMEWORK).withDeadline(1, 1, 1, 0, 0).build();
        model.addTask(taskToDelete);
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(RemoveTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(
                new ContactList(), new UserPrefs(), model.getTaskList().getCopy());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(removeTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getTaskList().size() + 1);
        RemoveTaskCommand removeTaskCommand = new RemoveTaskCommand(outOfBoundIndex);

        assertCommandFailure(removeTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }
    @Test
    public void equals() {
        RemoveTaskCommand removeTaskCommandFirst = new RemoveTaskCommand(INDEX_FIRST);
        RemoveTaskCommand removeTaskCommandSecond = new RemoveTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(removeTaskCommandFirst.equals(removeTaskCommandFirst));

        // same values -> returns true
        RemoveTaskCommand removeTaskCommandFirstCopy = new RemoveTaskCommand(INDEX_FIRST);
        assertTrue(removeTaskCommandFirst.equals(removeTaskCommandFirstCopy));

        // different types -> returns false
        assertFalse(removeTaskCommandFirst.equals(1));

        // null -> returns false
        assertFalse(removeTaskCommandFirst.equals(null));

        // different commands -> returns false
        assertFalse(removeTaskCommandFirst.equals(removeTaskCommandSecond));
    }
}
