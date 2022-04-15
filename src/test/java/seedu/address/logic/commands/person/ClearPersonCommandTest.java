package seedu.address.logic.commands.person;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.SECOND_TASK;
import static seedu.address.testutil.TypicalTasks.THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.StrategyBoard;
import seedu.address.model.TaskBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class ClearPersonCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearPersonCommand(),
                model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalTaskBook(),
                new StrategyBoard(), new UserPrefs());

        Task editedFirstTask = new TaskBuilder(FIRST_TASK).withPersons().build();
        Task editedSecondTask = new TaskBuilder(SECOND_TASK).withPersons().build();
        Task editedThirdTask = new TaskBuilder(THIRD_TASK).withPersons().build();
        List<Task> tasks = new ArrayList<>(Arrays.asList(editedFirstTask, editedSecondTask, editedThirdTask));
        TaskBook newTaskBook = new TaskBook();
        newTaskBook.setTasks(tasks);

        expectedModel.setTaskBook(newTaskBook);
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearPersonCommand(), model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
