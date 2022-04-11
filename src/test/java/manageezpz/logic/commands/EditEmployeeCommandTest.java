package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_DUPLICATE_PERSON;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static manageezpz.logic.commands.CommandTestUtil.DESC_AMY;
import static manageezpz.logic.commands.CommandTestUtil.DESC_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.logic.commands.CommandTestUtil.showPersonAtIndex;
import static manageezpz.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import static manageezpz.logic.commands.EditEmployeeCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static manageezpz.logic.commands.EditEmployeeCommand.MESSAGE_USAGE;
import static manageezpz.logic.commands.EditEmployeeCommand.createEditedEmployee;
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
import manageezpz.testutil.EditEmployeeDescriptorBuilder;
import manageezpz.testutil.EventBuilder;
import manageezpz.testutil.PersonBuilder;
import manageezpz.testutil.TodoBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code EditEmployeeCommand}.
 */
public class EditEmployeeCommandTest {

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
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        EditEmployeeCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder(new PersonBuilder().build()).build();
        Person editedPerson = createEditedEmployee(personToEdit, descriptor);

        EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToEdit))
                .collect(Collectors.toList());

        assertEquals(affectedTaskList.size(), 3);

        for (Task task : affectedTaskList) {
            List<Person> assignees = task.getAssignees();

            for (Person assignee : assignees) {
                if (assignee.equals(personToEdit)) {
                    Task taskToUpdate = expectedModelFullTaskList.get(expectedModelFullTaskList.indexOf(task));
                    expectedModel.updateTaskWithEditedPerson(taskToUpdate, assignees.indexOf(assignee), editedPerson);
                }
            }
        }

        assertFalse(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(personToEdit));
        assertFalse(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(personToEdit));
        assertFalse(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(personToEdit));

        assertTrue(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(editedPerson));

        assertCommandSuccess(editEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPersonToEdit = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        EditEmployeeCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        Person editedPerson = createEditedEmployee(lastPersonToEdit, descriptor);

        EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(lastPersonToEdit, editedPerson);

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(lastPersonToEdit))
                .collect(Collectors.toList());

        assertEquals(affectedTaskList.size(), 3);

        for (Task task : affectedTaskList) {
            List<Person> assignees = task.getAssignees();

            for (Person assignee : assignees) {
                if (assignee.equals(lastPersonToEdit)) {
                    Task taskToUpdate = expectedModelFullTaskList.get(expectedModelFullTaskList.indexOf(task));
                    expectedModel.updateTaskWithEditedPerson(taskToUpdate, assignees.indexOf(assignee), editedPerson);
                }
            }
        }

        assertFalse(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(lastPersonToEdit));
        assertFalse(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(lastPersonToEdit));
        assertFalse(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(lastPersonToEdit));

        assertTrue(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(editedPerson));

        assertCommandSuccess(editEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = createEditedEmployee(personToEdit, new EditEmployeeCommand.EditEmployeeDescriptor());

        assertEquals(personToEdit, editedPerson);

        EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand(INDEX_FIRST,
                new EditEmployeeCommand.EditEmployeeDescriptor());

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToEdit))
                .collect(Collectors.toList());

        assertEquals(affectedTaskList.size(), 3);

        for (Task task : affectedTaskList) {
            List<Person> assignees = task.getAssignees();

            for (Person assignee : assignees) {
                if (assignee.equals(personToEdit)) {
                    Task taskToUpdate = expectedModelFullTaskList.get(expectedModelFullTaskList.indexOf(task));
                    expectedModel.updateTaskWithEditedPerson(taskToUpdate, assignees.indexOf(assignee), editedPerson);
                }
            }
        }

        assertTrue(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(personToEdit));
        assertTrue(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(personToEdit));
        assertTrue(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(personToEdit));

        assertTrue(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(editedPerson));

        assertCommandSuccess(editEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Person personToEditInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        EditEmployeeCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build();
        Person editedPerson = createEditedEmployee(personToEditInFilteredList, descriptor);

        EditEmployeeCommand editEmployeeCommand = new EditEmployeeCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.setPerson(personToEditInFilteredList, editedPerson);

        List<Task> expectedModelFullTaskList = expectedModel.getAddressBook().getTaskList();

        List<Task> affectedTaskList = expectedModelFullTaskList.stream()
                .filter(task -> task.getAssignees().contains(personToEditInFilteredList))
                .collect(Collectors.toList());

        assertEquals(affectedTaskList.size(), 3);

        for (Task task : affectedTaskList) {
            List<Person> assignees = task.getAssignees();

            for (Person assignee : assignees) {
                if (assignee.equals(personToEditInFilteredList)) {
                    Task taskToUpdate = expectedModelFullTaskList.get(expectedModelFullTaskList.indexOf(task));
                    expectedModel.updateTaskWithEditedPerson(taskToUpdate, assignees.indexOf(assignee), editedPerson);
                }
            }
        }

        assertFalse(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(personToEditInFilteredList));
        assertFalse(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(personToEditInFilteredList));
        assertFalse(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(personToEditInFilteredList));

        assertTrue(expectedModelFullTaskList.get(INDEX_FIRST.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_SECOND.getZeroBased())
                .getAssignees().contains(editedPerson));
        assertTrue(expectedModelFullTaskList.get(INDEX_THIRD.getZeroBased())
                .getAssignees().contains(editedPerson));

        assertCommandSuccess(editEmployeeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(firstPerson).build();
        EditEmployeeCommand editCommand = new EditEmployeeCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_DUPLICATE_PERSON, MESSAGE_USAGE));
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);

        // Edit person in filtered list into a duplicate in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_SECOND.getZeroBased());
        EditEmployeeCommand editCommand = new EditEmployeeCommand(INDEX_FIRST,
                new EditEmployeeDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, String.format(MESSAGE_DUPLICATE_PERSON, MESSAGE_USAGE));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        EditEmployeeCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditEmployeeCommand editCommand = new EditEmployeeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model,
                String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;

        // Ensures that outOfBoundIndex is still in bounds of address book person list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        EditEmployeeCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditEmployeeCommand editCommand = new EditEmployeeCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model,
                String.format(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, MESSAGE_USAGE));
    }

    @Test
    public void equals() {
        final EditEmployeeCommand standardCommand = new EditEmployeeCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditEmployeeCommand.EditEmployeeDescriptor copyDescriptor =
                new EditEmployeeCommand.EditEmployeeDescriptor(DESC_AMY);
        EditEmployeeCommand commandWithSameValues = new EditEmployeeCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEmployeeCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEmployeeCommand(INDEX_FIRST, DESC_BOB)));
    }
}
