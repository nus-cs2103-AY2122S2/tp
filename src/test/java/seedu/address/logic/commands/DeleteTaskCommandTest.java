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

public class DeleteTaskCommandTest {
    public static final String NON_EXISTENT_GROUP_NAME = "Invalid Group Name";
    public static final String NON_EXISTENT_TASK_NAME = "Invalid Task Name";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullTaskGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, null));
    }

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, new GroupBuilder().build()));
    }

    @Test
    public void constructor_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(new TaskBuilder().build(), null));
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group groupToDeleteTask = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        DeleteTaskCommand command = new DeleteTaskCommand(new TaskBuilder().build(), groupToDeleteTask);

        assertCommandFailure(command, model, DeleteTaskCommand.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void execute_invalidTask_throwsCommandException() {
        Group groupToDeleteTask = new GroupBuilder().build();
        Task invalidTask = new TaskBuilder().withTaskName(NON_EXISTENT_TASK_NAME).build();

        DeleteTaskCommand command = new DeleteTaskCommand(invalidTask, groupToDeleteTask);

        assertCommandFailure(command, model, DeleteTaskCommand.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void execute_invalidTaskGroup_throwsCommandException() {
        Group groupToDeleteTask = new GroupBuilder().withGroupName(NON_EXISTENT_GROUP_NAME).build();
        Task invalidTask = new TaskBuilder().withTaskName(NON_EXISTENT_TASK_NAME).build();

        DeleteTaskCommand command = new DeleteTaskCommand(invalidTask, groupToDeleteTask);

        assertCommandFailure(command, model, DeleteTaskCommand.MESSAGE_NON_EXISTENT_GROUP);
    }

    @Test
    public void equals() {
        Group nusFintechSociety = new GroupBuilder().withGroupName("Nus Fintech Society").build();
        Group nusDataScienceSociety = new GroupBuilder().withGroupName("Nus Data Science Society").build();
        Task taskToAdd = new TaskBuilder().build();

        DeleteTaskCommand deleteTaskFromNusFintechSocietyCommand = new DeleteTaskCommand(taskToAdd, nusFintechSociety);
        DeleteTaskCommand deleteTaskFromNusDataScienceSocietyCommand =
                new DeleteTaskCommand(taskToAdd, nusDataScienceSociety);

        // same object -> returns true
        assertTrue(deleteTaskFromNusFintechSocietyCommand.equals(deleteTaskFromNusFintechSocietyCommand));

        // same values -> returns true
        DeleteTaskCommand deleteTaskFromNusFintechSocietyCommandCopy =
                new DeleteTaskCommand(taskToAdd, nusFintechSociety);
        assertTrue(deleteTaskFromNusFintechSocietyCommand.equals(deleteTaskFromNusFintechSocietyCommandCopy));

        // different types -> returns false
        assertFalse(deleteTaskFromNusFintechSocietyCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTaskFromNusFintechSocietyCommand.equals(null));

        // different group -> returns false
        assertFalse(deleteTaskFromNusFintechSocietyCommand.equals(deleteTaskFromNusDataScienceSocietyCommand));
    }
}
