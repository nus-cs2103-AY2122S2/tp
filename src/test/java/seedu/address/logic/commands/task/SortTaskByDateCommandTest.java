package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.EIGHTH_TASK;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.NINTH_TASK;
import static seedu.address.testutil.TypicalTasks.SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;

class SortTaskByDateCommandTest {

    // Test model
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
            new StrategyBoard(), new UserPrefs());

    @Test
    void execute_sortTaskByDate_success() {

        SortTaskByDateCommand sortTaskByDateCommand = new SortTaskByDateCommand();

        String expectedMessage = String.format(SortTaskByDateCommand.MESSAGE_SORT_TASKS_SUCCESS);

        // Creating expected TaskBook
        TaskBook expectedTaskBook = new TaskBook();
        expectedTaskBook.setTasks(Arrays.asList(SECOND_TASK, THIRD_TASK, FIRST_TASK));

        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()),
                new TaskBook(model.getTaskBook()),
                new StrategyBoard(),
                new UserPrefs());
        expectedModel.setTaskBook(expectedTaskBook);

        assertCommandSuccess(sortTaskByDateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortTaskByDateWithSameDay_success() {

        SortTaskByDateCommand sortTaskByDateCommand = new SortTaskByDateCommand();

        String expectedMessage = String.format(SortTaskByDateCommand.MESSAGE_SORT_TASKS_SUCCESS);

        // Test taskbook
        TaskBook testTaskBook = new TaskBook();
        testTaskBook.setTasks(Arrays.asList(EIGHTH_TASK, NINTH_TASK));

        TaskBook expectedTaskBook = new TaskBook();
        expectedTaskBook.setTasks(Arrays.asList(NINTH_TASK, EIGHTH_TASK));

        // Test model
        Model model = new ModelManager(getTypicalAddressBook(), testTaskBook,
                new StrategyBoard(), new UserPrefs());

        // Expected model
        Model expectedModel = new ModelManager(
                new AddressBook(model.getAddressBook()),
                new TaskBook(model.getTaskBook()),
                new StrategyBoard(),
                new UserPrefs());
        expectedModel.setTaskBook(expectedTaskBook);

        assertCommandSuccess(sortTaskByDateCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void equals() {
        final SortTaskByDateCommand command = new SortTaskByDateCommand();

        // If they are the same objects, they are equal
        assertTrue(command.equals(command));

    }
}
