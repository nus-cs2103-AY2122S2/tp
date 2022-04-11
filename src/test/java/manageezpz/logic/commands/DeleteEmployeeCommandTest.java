package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showPersonAtIndex;
import static manageezpz.logic.commands.DeleteEmployeeCommand.MESSAGE_DELETE_PERSON_SUCCESS;
import static manageezpz.logic.commands.DeleteEmployeeCommand.MESSAGE_USAGE;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalIndexes.INDEX_SECOND;
import static manageezpz.testutil.TypicalIndexes.INDEX_THIRD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

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
 * {@code DeleteEmployeeCommand}.
 */
public class DeleteEmployeeCommandTest {

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
        List<Task> modelFullTaskList = model.getAddressBook().getTaskList();

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(INDEX_FIRST);

        assertEquals(model.getFilteredPersonList().size(), 3);
        assertEquals(personToDelete.getNumOfTasks(), 3);
        assertEquals(modelFullTaskList.get(INDEX_FIRST.getZeroBased()).getAssignees().toString(),
                "[Alex Yeoh; Phone: 87438807; Email: alexyeoh@example.com, "
                        + "Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");

        String expectedMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToDelete))
                .collect(Collectors.toList());

        for (Task task : affectedTaskList) {
            expectedModel.untagEmployeeFromTask(task, personToDelete);
        }

        expectedModel.deletePerson(personToDelete);

        assertEquals(expectedModel.getFilteredPersonList().size(), 2);
        assertEquals(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");
        assertEquals(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");
        assertEquals(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");

        assertCommandSuccess(deleteEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(outOfBoundIndex);

        assertCommandFailure(deleteEmployeeCommand, model,
                String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        List<Task> modelFullTaskList = model.getAddressBook().getTaskList();

        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(INDEX_FIRST);

        assertEquals(model.getFilteredPersonList().size(), 1);
        assertEquals(personToDelete.getNumOfTasks(), 3);
        assertEquals(modelFullTaskList.get(INDEX_FIRST.getZeroBased()).getAssignees().toString(),
                "[Alex Yeoh; Phone: 87438807; Email: alexyeoh@example.com, "
                        + "Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");

        String expectedMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToDelete))
                .collect(Collectors.toList());

        for (Task task : affectedTaskList) {
            expectedModel.untagEmployeeFromTask(task, personToDelete);
        }

        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertEquals(expectedModel.getFilteredPersonList().size(), 0);
        assertEquals(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");
        assertEquals(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");
        assertEquals(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased()).getAssignees().toString(),
                "[Charlotte Oliveiro; Phone: 93210283; Email: charlotte@example.com]");

        assertCommandSuccess(deleteEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // Ensures that outOfBoundIndex is still in bounds of address book person list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(outOfBoundIndex);

        assertCommandFailure(deleteEmployeeCommand, model,
                String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        DeleteEmployeeCommand deleteFirstEmployeeCommand = new DeleteEmployeeCommand(INDEX_FIRST);
        DeleteEmployeeCommand deleteSecondEmployeeCommand = new DeleteEmployeeCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstEmployeeCommand.equals(deleteFirstEmployeeCommand));

        // same values -> returns true
        DeleteEmployeeCommand deleteFirstEmployeeCommandCopy = new DeleteEmployeeCommand(INDEX_FIRST);
        assertTrue(deleteFirstEmployeeCommand.equals(deleteFirstEmployeeCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstEmployeeCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstEmployeeCommand.equals(null));

        // different person (employee) -> returns false
        assertFalse(deleteFirstEmployeeCommand.equals(deleteSecondEmployeeCommand));
    }

    /**
     * Updates {@code model}'s filtered person list to show no person.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
