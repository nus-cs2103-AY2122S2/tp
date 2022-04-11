package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showTaskAtIndex;
import static manageezpz.logic.commands.DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static manageezpz.logic.commands.DeleteTaskCommand.MESSAGE_USAGE;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.model.AddressBook;
import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.person.Person;
import manageezpz.model.task.Task;
import manageezpz.testutil.DeadlineBuilder;
import manageezpz.testutil.EventBuilder;
import manageezpz.testutil.PersonBuilder;
import manageezpz.testutil.TodoBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());

        // Create persons Alex, Bernice and Charlotte
        Person personAlex = new PersonBuilder().withName("Alex Yeoh").withPhone("87438807")
                .withEmail("alexyeoh@example.com").build();
        Person personBernice = new PersonBuilder().withName("Bernice Yu").withPhone("99272758")
                .withEmail("berniceyu@example.com").build();
        Person personCharlotte = new PersonBuilder().withName("Charlotte Oliveiro").withPhone("93210283")
                .withEmail("charlotte@example.com").build();

        // Create tasks
        Task taskToDo = new TodoBuilder().withDescription("Review Monthly Finance KPI")
                .build();
        Task taskDeadline = new DeadlineBuilder().withDescription("Finish Client Proposal")
                .withDate("2022-03-15").withTime("1800").build();
        Task taskEvent = new EventBuilder().withDescription("Meeting with Client")
                .withDate("2022-03-15").withStartTime("1300").withEndTime("1400").build();

        // Tag tasks to Alex
        taskToDo.assignedTo(personAlex);
        personAlex.increaseTaskCount();
        taskDeadline.assignedTo(personAlex);
        personAlex.increaseTaskCount();
        taskEvent.assignedTo(personAlex);
        personAlex.increaseTaskCount();

        // Tag tasks to Charlotte
        taskToDo.assignedTo(personCharlotte);
        personCharlotte.increaseTaskCount();
        taskDeadline.assignedTo(personCharlotte);
        personCharlotte.increaseTaskCount();
        taskEvent.assignedTo(personCharlotte);
        personCharlotte.increaseTaskCount();

        // Add persons to the new address book
        model.addPerson(personAlex);
        model.addPerson(personBernice);
        model.addPerson(personCharlotte);

        // Add tasks to the new address book
        model.addTask(taskToDo);
        model.addTask(taskDeadline);
        model.addTask(taskEvent);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<Person> fullPersonList = model.getAddressBook().getPersonList();

        List<Person> affectedPersonList = taskToDelete.getAssignees();

        for (Person person : affectedPersonList) {
            Person personToUpdate = fullPersonList.get(fullPersonList.indexOf(person));
            expectedModel.decreaseNumOfTasks(personToUpdate);
        }

        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<Person> fullPersonList = model.getAddressBook().getPersonList();

        List<Person> affectedPersonList = taskToDelete.getAssignees();

        for (Person person : affectedPersonList) {
            Person personToUpdate = fullPersonList.get(fullPersonList.indexOf(person));
            expectedModel.decreaseNumOfTasks(personToUpdate);
        }

        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // Ensures that outOfBoundIndex is still in bounds of address book task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model,
                String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstTaskCommand = new DeleteTaskCommand(INDEX_FIRST);
        DeleteTaskCommand deleteSecondTaskCommand = new DeleteTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstTaskCommandCopy = new DeleteTaskCommand(INDEX_FIRST);
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTaskCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstTaskCommand.equals(deleteSecondTaskCommand));
    }

    /**
     * Updates {@code model}'s filtered task list to show no task.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
