package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.ViewTaskCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskList;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class ViewTaskCommandTest {

    @Test
    public void execute_taskListIsEmpty_throwsCommandException() {
        ModelStubViewTask modelStubViewTask = new ModelStubViewTask();
        assertThrows(CommandException.class, () -> new ViewTaskCommand().execute(modelStubViewTask));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewTaskCommand().execute(null));
    }

    @Test
    public void execute_taskListIsNotEmpty_viewSuccessful() throws CommandException {
        ModelStubHasTask modelStubHasTask = new ModelStubHasTask();
        modelStubHasTask.addTask(new Task("description", "01/01/2022"));
        String message = modelStubHasTask.viewTask();
        CommandResult commandResult = new ViewTaskCommand().execute(modelStubHasTask);

        assertEquals(MESSAGE_SUCCESS + "\n" + message, commandResult.getFeedbackToUser());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task, Integer taskId) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Integer taskNumber) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public String findTask(String input) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String viewTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskListFilePath(Path taskListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getReadOnlyTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTask(Task updatedTask, Integer taskId) {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubViewTask extends ModelStub {
        private TaskList taskList = new TaskList();

        @Override
        public TaskList getTaskList() {
            return this.taskList;
        }

        @Override
        public String viewTask() {
            return taskList.viewTask();
        }
    }

    private class ModelStubHasTask extends ModelStub {
        private TaskList taskList = new TaskList();

        @Override
        public void addTask(Task task) {
            taskList.addTask(task);
        }

        @Override
        public TaskList getTaskList() {
            return this.taskList;
        }

        @Override
        public String viewTask() {
            return taskList.viewTask();
        }
    }
}
