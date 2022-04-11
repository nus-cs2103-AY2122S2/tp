package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindTaskCommandTest {

    private final ModelManager model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList());

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindTaskCommand(null));
    }

    @Test
    public void execute_validInputSuccess() throws CommandException {
        String testInputSuccess = "test";
        FindTaskCommand findTaskCommand = new FindTaskCommand(testInputSuccess);
        model.addTask(new Task("test", "2022"));
        CommandResult commandResult = findTaskCommand
                .execute(model);
        String taskSuccess = model.findTask(testInputSuccess);
        assertEquals(String.format(FindTaskCommand.MESSAGE_ARGUMENTS, taskSuccess),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validInput_noTaskFound() {
        String testInputUnsuccessful = "fail";
        FindTaskCommand findTaskCommand = new FindTaskCommand(testInputUnsuccessful);
        model.addTask(new Task("test", "2022"));
        String expectedMessage = FindTaskCommand.MESSAGE_NO_MATCHING_TASK;
        assertThrows(CommandException.class, expectedMessage, () -> findTaskCommand.execute(model));
    }

    @Test
    public void equals() {
        String firstTest = "test1";
        String secondTest = "test2";
        FindTaskCommand findFirstTaskCommand = new FindTaskCommand(firstTest);
        FindTaskCommand findSecondTaskCommand = new FindTaskCommand(secondTest);

        // same object -> returns true
        assertTrue(findFirstTaskCommand.equals(findFirstTaskCommand));

        // same values -> returns true
        FindTaskCommand findFirstTaskCommandCopy = new FindTaskCommand(firstTest);
        assertTrue(findFirstTaskCommand.equals(findFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(findFirstTaskCommand.equals(firstTest));

        // null -> returns false
        assertFalse(findFirstTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstTaskCommand.equals(findSecondTaskCommand));
    }


    /**
     * A default model stub that have all the methods failing.
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

