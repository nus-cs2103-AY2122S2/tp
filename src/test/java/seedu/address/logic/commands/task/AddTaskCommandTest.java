package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddTaskCommand.
 */
public class AddTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null));
    }

    @Test
    public void execute_taskAcceptedByModel_success() throws Exception {
        // empty persons list -> success
        Task validTask = new TaskBuilder().build();

        AddTaskCommand addTaskCommand = new AddTaskCommand(validTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_SUCCESS, validTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskBook(model.getTaskBook()), new StrategyBoard(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(addTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personsNotInList_throwsCommandException() {
        // task with one person not found in the address book -> throws an error
        Task invalidTask = new TaskBuilder().withPersons("Johnson").build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(invalidTask);

        String expectedMessage = String.format(AddTaskCommand.MESSAGE_CONTACT_NOT_FOUND, "Johnson");
        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand.execute(model));

        // task with only one person out of the rest found in the address book -> throws an error
        invalidTask = new TaskBuilder().withPersons("Johnson", "Alice Pauline").build();
        AddTaskCommand addTaskCommand2 = new AddTaskCommand(invalidTask);

        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand2.execute(model));

        // task with multiple persons not found in the address book -> throws an error
        invalidTask = new TaskBuilder().withPersons("Johnson", "Kenny").build();
        AddTaskCommand addTaskCommand3 = new AddTaskCommand(invalidTask);

        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand3.execute(model));
    }

    @Test
    public void execute_personsInConflictingTask_throwsCommandException() {
        // task with same date, same time range, and same persons -> throws an error
        Task invalidTask = new TaskBuilder()
                .withDate("09-10-2022").withStartTime("09:00").withEndTime("10:00")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(invalidTask);

        String expectedMessage =
                String.format(AddTaskCommand.MESSAGE_SCHEDULE_CONFLICT, ALICE.getName().fullName);
        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand.execute(model));

        // task with same date, overlapping time range, and same persons -> throws an error
        invalidTask = new TaskBuilder()
                .withDate("09-10-2022").withStartTime("09:30").withEndTime("10:30")
                .withPersons(ALICE.getName().fullName, BENSON.getName().fullName).build();
        AddTaskCommand addTaskCommand2 = new AddTaskCommand(invalidTask);

        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand2.execute(model));

        // task with same date, overlapping time range, and only ALICE -> throws an error
        invalidTask = new TaskBuilder()
                .withDate("09-10-2022").withStartTime("09:30").withEndTime("10:30")
                .withPersons(ALICE.getName().fullName).build();
        AddTaskCommand addTaskCommand3 = new AddTaskCommand(invalidTask);

        assertThrows(CommandException.class,
                expectedMessage, () -> addTaskCommand3.execute(model));
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task duplicateTask = new TaskBuilder().withName(FIRST_TASK.getName().fullName).build();
        AddTaskCommand addTaskCommand = new AddTaskCommand(duplicateTask);

        assertThrows(CommandException.class,
                AddTaskCommand.MESSAGE_DUPLICATE_TASK, () -> addTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        Task shareholderMeet = new TaskBuilder().withName("Shareholder Meet").build();
        Task welcomeTea = new TaskBuilder().withName("Welcome Tea").build();
        AddTaskCommand addShareholderMeetCommand = new AddTaskCommand(shareholderMeet);
        AddTaskCommand addWelcomeTeaCommand = new AddTaskCommand(welcomeTea);

        // same object -> returns true
        assertTrue(addShareholderMeetCommand.equals(addShareholderMeetCommand));

        // same values -> returns true
        AddTaskCommand addShareholderMeetCommandCopy = new AddTaskCommand(shareholderMeet);
        assertTrue(addShareholderMeetCommand.equals(addShareholderMeetCommandCopy));

        // different types -> returns false
        assertFalse(addShareholderMeetCommand.equals(1));

        // null -> returns false
        assertFalse(addShareholderMeetCommand.equals(null));

        // different person -> returns false
        assertFalse(addShareholderMeetCommand.equals(addWelcomeTeaCommand));
    }
}
