package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ASSIGNMENT;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tasks.PriorityTaskList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;
import seedu.address.testutil.TaskBuilder;




public class NewTaskCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NewTaskCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Task validTask = new TaskBuilder().build();

        CommandResult commandResult = new NewTaskCommand(validTask).execute(modelStub);

        assertEquals(String.format(NewTaskCommand.MESSAGE_SUCCESS, validTask), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTask), modelStub.tasks);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Task validTask = new TaskBuilder().build();
        NewTaskCommand newTaskCommand = new NewTaskCommand(validTask);
        AddCommandTest.ModelStub modelStub = new ModelStubWithTask(validTask);

        assertThrows(CommandException.class,
                NewTaskCommand.MESSAGE_DUPLICATE_TASK, () -> newTaskCommand.execute(modelStub));
    }
    @Test
    public void equals() {
        Task homework = new TaskBuilder(HOMEWORK).build();
        Task assignment = new TaskBuilder(ASSIGNMENT).build();
        NewTaskCommand addHomeworkCommand = new NewTaskCommand(homework);
        NewTaskCommand addAssignmentCommand = new NewTaskCommand(assignment);

        // same object -> returns true
        assertTrue(addHomeworkCommand.equals(addHomeworkCommand));

        // same values -> returns true
        NewTaskCommand addHomeworkCommandCopy = new NewTaskCommand(homework);
        assertTrue(addHomeworkCommand.equals(addHomeworkCommandCopy));

        // different types -> returns false
        assertFalse(addHomeworkCommand.equals(1));

        // null -> returns false
        assertFalse(addHomeworkCommand.equals(null));

        // different commands -> returns false
        assertFalse(addHomeworkCommand.equals(addAssignmentCommand));
    }

    /**
     * A Model stub that contains a single Task.
     */
    protected static class ModelStubWithTask extends AddCommandTest.ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.equals(task);
        }
    }
    /**
     * A Model stub that always accept the task being added
     */
    protected static class ModelStubAcceptingTaskAdded extends AddCommandTest.ModelStub {
        final ArrayList<Task> tasks = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasks.stream().anyMatch(task::equals);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasks.add(task);
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
            return new PriorityTaskList();
        }
    }
}
