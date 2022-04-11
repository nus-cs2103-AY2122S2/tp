package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTask.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class UpdateTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Task updatedTask = new Task("test", "03/03/2022");
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(updatedTask.getDescription());
        descriptor.setDeadline(updatedTask.getDeadline());
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(1, descriptor);

        String expectedMessage = String.format(UpdateTaskCommand.MESSAGE_UPDATE_TASK_SUCCESS, updatedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
            model.getTaskList());
        expectedModel.updateTask(updatedTask, 1);

        assertCommandSuccess(updateTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void createTask_descriptorWithoutDeadline() {
        Task updatedTask = new Task("test", "03/03/2022");
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(updatedTask.getDescription());
        Task newTaskCreated = UpdateTaskCommand.createUpdatedTask(updatedTask, descriptor);
        assertTrue(newTaskCreated.getDeadline().equals(updatedTask.getDeadline()));
    }

    @Test
    public void createTask_descriptorWithoutDescription() {
        Task updatedTask = new Task("test", "03/03/2022");
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDeadline(updatedTask.getDeadline());
        Task newTaskCreated = UpdateTaskCommand.createUpdatedTask(updatedTask, descriptor);
        assertTrue(newTaskCreated.getDescription().equals(updatedTask.getDescription()));
    }

    @Test
    public void createTaskWithoutDeadline_descriptorWithoutDeadline() {
        Task updatedTask = new Task("test", "No deadline set");
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(updatedTask.getDescription());
        Task newTaskCreated = UpdateTaskCommand.createUpdatedTask(updatedTask, descriptor);
        assertTrue(newTaskCreated.getDeadline().equals("No deadline set"));
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Integer outOfBoundIndex = model.getTaskList().size() + 1;
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription("test");
        descriptor.setDeadline("03/03/2022");
        UpdateTaskCommand updateTaskCommand = new UpdateTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(updateTaskCommand, model, "The task id provided is invalid");
    }

    @Test
    public void equals() {
        Task updatedTask = new Task("test", "03/03/2022");
        Task differentTask = new Task("differentTest", "05/05/2022");
        UpdateTaskCommand.UpdateTaskDescriptor descriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(updatedTask.getDescription());
        descriptor.setDeadline(updatedTask.getDeadline());
        final UpdateTaskCommand standardCommand = new UpdateTaskCommand(1, descriptor);

        // same values -> returns true
        UpdateTaskCommand.UpdateTaskDescriptor copyDescriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        copyDescriptor.setDescription(updatedTask.getDescription());
        copyDescriptor.setDeadline(updatedTask.getDeadline());
        UpdateTaskCommand commandWithSameValues = new UpdateTaskCommand(1, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        assertFalse(descriptor.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new UpdateTaskCommand(2, descriptor)));

        UpdateTaskCommand.UpdateTaskDescriptor differentDescriptor = new UpdateTaskCommand.UpdateTaskDescriptor();
        descriptor.setDescription(differentTask.getDescription());
        descriptor.setDeadline(differentTask.getDeadline());

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new UpdateTaskCommand(1, differentDescriptor)));

    }


}
