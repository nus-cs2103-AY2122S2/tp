package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.EIGHTH_TASK;
import static seedu.address.testutil.TypicalTasks.FIFTH_TASK;
import static seedu.address.testutil.TypicalTasks.SEVENTH_TASK;
import static seedu.address.testutil.TypicalTasks.SIXTH_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Date;

/**
 * Contains tests for
 * {@code ClearTaskCommand}.
 */
public class ClearTaskCommandTest {

    private final Date dateStub = new Date("03-03-2000");
    private final Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearTaskCommand(null));
    }

    @Test
    public void execute_clearTaskBook_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        expectedModel.setTaskBook(new TaskBook());

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_validDate_success() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());

        TaskBook taskBook = new TaskBook();
        taskBook.addTask(FIFTH_TASK);
        expectedModel.setTaskBook(taskBook);
        taskBook.addTask(SIXTH_TASK);
        taskBook.addTask(SEVENTH_TASK);
        taskBook.addTask(EIGHTH_TASK);
        model.setTaskBook(taskBook);

        assertCommandSuccess(new ClearTaskCommand(dateStub), model,
                String.format(ClearTaskCommand.MESSAGE_SUCCESS_ALT, dateStub), expectedModel);
    }

    @Test
    public void execute_invalidDate_throwsCommandException() {
        ClearTaskCommand clearTaskCommand = new ClearTaskCommand(dateStub);

        assertCommandFailure(clearTaskCommand, model, String.format(ClearTaskCommand.MESSAGE_INVALID_DATE, dateStub));
    }

    @Test
    public void execute_emptyTaskList_throwsCommandException() {
        Model emptyTaskListModel = new ModelManager(getTypicalAddressBook(), new TaskBook(),
                new StrategyBoard(), new UserPrefs());
        ClearTaskCommand clearTaskCommand = new ClearTaskCommand();

        assertCommandFailure(clearTaskCommand, emptyTaskListModel, ClearTaskCommand.MESSAGE_EMPTY_LIST);
    }

    @Test
    public void equals() {
        Date dateStub2 = new Date("03-03-2001");
        ClearTaskCommand clearTaskFirstCommand = new ClearTaskCommand(dateStub);
        ClearTaskCommand clearTaskSecondCommand = new ClearTaskCommand(dateStub2);
        ClearTaskCommand clearTaskEmptyCommand = new ClearTaskCommand();

        // same object -> returns true
        assertTrue(clearTaskFirstCommand.equals(clearTaskFirstCommand));
        assertTrue(clearTaskEmptyCommand.equals(clearTaskEmptyCommand));

        // same values -> returns true
        ClearTaskCommand clearTaskFirstCommandCopy = new ClearTaskCommand(dateStub);
        ClearTaskCommand clearTaskEmptyCommandCopy = new ClearTaskCommand();
        assertTrue(clearTaskFirstCommand.equals(clearTaskFirstCommandCopy));
        assertTrue(clearTaskEmptyCommand.equals(clearTaskEmptyCommandCopy));

        // different types -> returns false
        assertFalse(clearTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(clearTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(clearTaskFirstCommand.equals(clearTaskSecondCommand));
    }
}
