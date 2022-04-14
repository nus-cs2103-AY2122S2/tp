package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.Task;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ProgressCommand}.
 */
public class ProgressCommandTest {
    @Test
    public void execute_noResults_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        // Case 1: no students with the specified moduleCode
        // as well as the specified task in his/her tasklist.
        ModuleCode moduleCode = new ModuleCode("CS0000"); // No students in the typical AB is taking this module.
        Task task = new Task("Task A");
        ProgressCommand progressCommand = new ProgressCommand(moduleCode, task);
        assertCommandFailure(progressCommand, model, ProgressCommand.MESSAGE_NO_RESULTS_FOUND);

        // Case 2: there are students with the specified moduleCode,
        // but all of these students do not have the specified task in their tasklist.
        moduleCode = ALICE.getModuleCode();
        task = BENSON.getTaskList().getTaskList().get(0);
        progressCommand = new ProgressCommand(moduleCode, task);
        assertCommandFailure(progressCommand, model, ProgressCommand.MESSAGE_NO_RESULTS_FOUND);
    }

    @Test
    public void execute_allFilteredStudentsComplete_success() {
        // Case 1: all of the filtered students have completed the task.
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person bensonCopy = new PersonBuilder(BENSON).withStudentId("A0000000Z").withName("Copy").build();
        model.addPerson(bensonCopy);
        ModuleCode moduleCode = BENSON.getModuleCode();
        Task task = BENSON.getTaskList().getTaskList().get(0);
        ProgressCommand progressCommand = new ProgressCommand(moduleCode, task);

        AddressBook bensonCopyAb = new AddressBookBuilder(getTypicalAddressBook()).build();
        StringBuilder result = new StringBuilder();
        List<Person> expectedResultingList = new ArrayList<>(Arrays.asList(BENSON, bensonCopy));
        String tick = "\u2713";
        for (Person i : expectedResultingList) {
            result.append(i.getName()).append(" (").append(i.getStudentId()).append("): ");
            result.append(tick).append("\n");
        }

        String expectedMessage = String.format(ProgressCommand.MESSAGE_SUCCESS, task.getTaskName(), moduleCode, result);
        ModelManager expectedModel = new ModelManager(bensonCopyAb, new UserPrefs());
        expectedModel.addPerson(bensonCopy);

        assertCommandSuccess(progressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFilteredStudentsIncomplete_success() {
        // Case 2: all of the filtered students have not complete the task.
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person aliceCopy = new PersonBuilder(ALICE).withStudentId("A0000000Z").withName("Copy").build();
        model.addPerson(aliceCopy);
        ModuleCode moduleCode = ALICE.getModuleCode();
        Task task = ALICE.getTaskList().getTaskList().get(0);
        ProgressCommand progressCommand = new ProgressCommand(moduleCode, task);

        AddressBook aliceCopyAb = new AddressBookBuilder(getTypicalAddressBook()).build();
        StringBuilder result = new StringBuilder();
        List<Person> expectedResultingList = new ArrayList<>(Arrays.asList(ALICE, aliceCopy));
        String cross = "\u274C";
        for (Person i : expectedResultingList) {
            result.append(i.getName()).append(" (").append(i.getStudentId()).append("): ");
            result.append(cross).append("\n");
        }

        String expectedMessage = String.format(ProgressCommand.MESSAGE_SUCCESS, task.getTaskName(), moduleCode, result);
        ModelManager expectedModel = new ModelManager(aliceCopyAb, new UserPrefs());
        expectedModel.addPerson(aliceCopy);

        assertCommandSuccess(progressCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFilteredStudentsCompleteAndIncomplete_success() {
        // Case 3: all of the filtered students have/have not complete the task (mix of case 1 and 2).
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        ModuleCode moduleCode = ALICE.getModuleCode();
        Task task = ALICE.getTaskList().getTaskList().get(0);

        // bensonCopy => Same module and tasklist as ALICE, but the task is completed instead.
        Person bensonCopy = new PersonBuilder().withStudentId("A9999999Z")
                .withName("Benson Copy")
                .withModuleCode(ALICE.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList(task.getTaskName(), true).build();
        model.addPerson(bensonCopy);

        ProgressCommand progressCommand = new ProgressCommand(moduleCode, task);

        AddressBook bensonCopyAb = new AddressBookBuilder(getTypicalAddressBook()).build();
        StringBuilder result = new StringBuilder();
        List<Person> expectedResultingList = new ArrayList<>(Arrays.asList(ALICE, bensonCopy));
        String tick = "\u2713";
        String cross = "\u274C";
        for (Person i : expectedResultingList) {
            result.append(i.getName()).append(" (").append(i.getStudentId()).append("): ");
            if (i.getTaskList().getTaskList().get(0).isTaskComplete()) {
                result.append(tick).append("\n");
            } else {
                result.append(cross).append("\n");
            }
        }

        String expectedMessage = String.format(ProgressCommand.MESSAGE_SUCCESS, task.getTaskName(), moduleCode, result);
        ModelManager expectedModel = new ModelManager(bensonCopyAb, new UserPrefs());
        expectedModel.addPerson(bensonCopy);

        assertCommandSuccess(progressCommand, model, expectedMessage, expectedModel);
    }
}
