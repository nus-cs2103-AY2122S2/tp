package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TaskCommand}.
 */
public class TaskCommandTest {
    @Test
    public void execute_validStudentId_success() {
        // Student has both completed and incomplete tasks.
        Person bensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList("Task A", true)
                .withTaskList("Task B", false)
                .build();
        AddressBook bensonCopyAb = new AddressBookBuilder().withPerson(bensonCopy).build();
        Model model = new ModelManager(bensonCopyAb, new UserPrefs());
        TaskCommand taskCommand = new TaskCommand(bensonCopy.getStudentId());

        String expectedMessage = String.format(TaskCommand.MESSAGE_SUCCESS, bensonCopy.getStudentId(),
                "Incomplete tasks:\n" + "1. Task B\n" + "\n" + "Completed tasks:\n" + "1. Task A\n");
        ModelManager expectedModel = new ModelManager(bensonCopyAb, new UserPrefs());

        assertCommandSuccess(taskCommand, model, expectedMessage, expectedModel);

        // Student has only completed tasks
        bensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList("Task A", true)
                .build();
        bensonCopyAb = new AddressBookBuilder().withPerson(bensonCopy).build();
        model = new ModelManager(bensonCopyAb, new UserPrefs());
        taskCommand = new TaskCommand(bensonCopy.getStudentId());

        expectedMessage = String.format(TaskCommand.MESSAGE_SUCCESS, bensonCopy.getStudentId(),
                "Completed tasks:\n" + "1. Task A\n");
        expectedModel = new ModelManager(bensonCopyAb, new UserPrefs());

        assertCommandSuccess(taskCommand, model, expectedMessage, expectedModel);

        // Student has only incomplete tasks
        bensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList("Task A", false)
                .build();
        bensonCopyAb = new AddressBookBuilder().withPerson(bensonCopy).build();
        model = new ModelManager(bensonCopyAb, new UserPrefs());
        taskCommand = new TaskCommand(bensonCopy.getStudentId());

        expectedMessage = String.format(TaskCommand.MESSAGE_SUCCESS, bensonCopy.getStudentId(),
                "Incomplete tasks:\n" + "1. Task A\n");
        expectedModel = new ModelManager(bensonCopyAb, new UserPrefs());

        assertCommandSuccess(taskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        TaskCommand taskCommand = new TaskCommand(new StudentId("A0000000Z"));
        assertCommandFailure(taskCommand, model, TaskCommand.MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_emptyTaskList_throwsCommandException() {
        Person bensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .build();
        AddressBook bensonCopyAb = new AddressBookBuilder().withPerson(bensonCopy).build();
        Model model = new ModelManager(bensonCopyAb, new UserPrefs());
        TaskCommand taskCommand = new TaskCommand(bensonCopy.getStudentId());
        assertCommandFailure(taskCommand, model, TaskCommand.MESSAGE_EMPTY_TASKLIST);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder(ALICE).build();
        Person bob = new PersonBuilder(BOB).build();
        TaskCommand taskAliceCommand = new TaskCommand(alice.getStudentId());
        TaskCommand taskBobCommand = new TaskCommand(bob.getStudentId());

        // same object -> returns true
        assertTrue(taskAliceCommand.equals(taskAliceCommand));

        // same values -> returns true
        TaskCommand taskAliceCommandCopy = new TaskCommand(alice.getStudentId());
        assertTrue(taskAliceCommand.equals(taskAliceCommandCopy));

        // different types -> returns false
        assertFalse(taskAliceCommand.equals(1));

        // null -> returns false
        assertFalse(taskAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(taskAliceCommand.equals(taskBobCommand));
    }
}
