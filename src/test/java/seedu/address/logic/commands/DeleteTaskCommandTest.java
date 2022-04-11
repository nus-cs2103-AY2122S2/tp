package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTask.getTypicalTaskList;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class DeleteTaskCommandTest {

    private ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    public void constructor_validInputSuccess() throws Exception {
        Integer taskNumberToBeDeleted = 1;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(taskNumberToBeDeleted);

        model.addTask(new Task("test", "2022"));

        CommandResult commandResult = new DeleteTaskCommand(taskNumberToBeDeleted)
            .execute(model);

        assertEquals(String.format(DeleteTaskCommand.MESSAGE_ARGUMENTS, taskNumberToBeDeleted),
            commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() throws CommandException {
        Integer outOfBoundIndex = model.getTaskList().size() + 1;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        String expectedMessage = DeleteTaskCommand.MESSAGE_INDEX_OUT_OF_BOUNDS
            + "\n" + DeleteTaskCommand.MESSAGE_USAGE;

        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void constructor_nullDescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null));
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstTaskCommand = new DeleteTaskCommand(1);
        DeleteTaskCommand deleteSecondTaskCommand = new DeleteTaskCommand(2);

        // same object -> returns true
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstTaskCommandCopy = new DeleteTaskCommand(1);
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstTaskCommand.equals(deleteSecondTaskCommand));
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
        public void deleteTask(Integer taskNumber) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TaskList getTaskList() {
            throw new AssertionError("This method should not be called.");
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
}
