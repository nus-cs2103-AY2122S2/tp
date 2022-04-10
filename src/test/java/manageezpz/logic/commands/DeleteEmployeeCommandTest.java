package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showPersonAtIndex;
import static manageezpz.logic.commands.DeleteEmployeeCommand.MESSAGE_DELETE_PERSON_SUCCESS;
import static manageezpz.logic.commands.DeleteEmployeeCommand.MESSAGE_USAGE;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        // Create persons Alex and Bob
        Person personAlex = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_AMY).build();
        Person personBob = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();

        // Create tasks
        Task taskToDo = new TodoBuilder().withDescription("Review Monthly Finance KPI")
                .build();
        Task taskDeadline = new DeadlineBuilder().withDescription("Finish Client Proposal")
                .withDate("2022-03-15").withTime("1800").build();
        Task taskEvent = new EventBuilder().withDescription("Meeting with Client")
                .withDate("2022-03-15").withStartTime("1300").withEndTime("1400").build();

        // Tag tasks to Alex
        taskToDo.assignedTo(personAlex);
        taskDeadline.assignedTo(personAlex);
        taskEvent.assignedTo(personAlex);

        // Add persons to the new address book
        model.addPerson(personAlex);
        model.addPerson(personBob);

        // Add tasks to the new address book
        model.addTask(taskToDo);
        model.addTask(taskDeadline);
        model.addTask(taskEvent);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

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
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(INDEX_FIRST);

        String expectedMessage = String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

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
