package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;
import seedu.address.model.task.Task;
import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandTest {

    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullTaskGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, null));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(null, new GroupBuilder().build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTaskCommand(new TaskBuilder().build(), null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToAssign = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        AddTaskCommand command = new AddTaskCommand(new TaskBuilder().build(), groupToAssign);

        assertCommandFailure(command, model, AddTaskCommand.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void equals() {
        Group nusFintechSociety = new GroupBuilder().withGroupName("Nus Fintech Society").build();
        Group nusDataScienceSociety = new GroupBuilder().withGroupName("Nus Data Science Society").build();
        Task taskToAdd = new TaskBuilder().build();

        AddTaskCommand addTaskToNusFintechSocietyCommand = new AddTaskCommand(taskToAdd, nusFintechSociety);
        AddTaskCommand addTaskToNusDataScienceSocietyCommand = new AddTaskCommand(taskToAdd, nusDataScienceSociety);

        // same object -> returns true
        assertTrue(addTaskToNusFintechSocietyCommand.equals(addTaskToNusFintechSocietyCommand));

        // same values -> returns true
        AddTaskCommand addTaskToNusFintechSocietyCommandCopy = new AddTaskCommand(taskToAdd, nusFintechSociety);
        assertTrue(addTaskToNusFintechSocietyCommand.equals(addTaskToNusFintechSocietyCommandCopy));

        // different types -> returns false
        assertFalse(addTaskToNusFintechSocietyCommand.equals(1));

        // null -> returns false
        assertFalse(addTaskToNusFintechSocietyCommand.equals(null));

        // different group -> returns false
        assertFalse(addTaskToNusFintechSocietyCommand.equals(addTaskToNusDataScienceSocietyCommand));
    }
}
