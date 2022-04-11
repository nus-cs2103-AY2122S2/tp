package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.DeleteTaskCommand.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_MODULE_CODE_NOT_FOUND;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_TASK_NOT_FOUND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ANDY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTaskIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


public class DeleteTaskCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final ModuleCode moduleCodeAlice = TypicalPersons.ALICE.getModuleCode();
    private final ModuleCode invalidModuleCode = new ModuleCode("CSGO");

    private final Task taskAlice = ALICE.getTaskList().getTaskList().get(0);
    private final Task taskToDelete = new Task("Cry");

    private final Index invalidTaskIndexAlice = Index.fromOneBased(ALICE.getTaskList().getNumberOfTasks() + 1);

    @Test
    public void equals_sameObjectByStudentIdAndIndex_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(studentIdAlice, INDEX_FIRST_TASK);
        assertEquals(deleteTaskCommand, deleteTaskCommand);
    }

    @Test
    public void equals_sameObjectByModuleCodeAndTask_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(moduleCodeAlice, taskAlice);
        assertEquals(deleteTaskCommand, deleteTaskCommand);
    }

    @Test
    public void equals_sameValuesByStudentIdAndIndex_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(studentIdAlice, INDEX_FIRST_TASK);
        DeleteTaskCommand deleteTaskCommandCopy = new DeleteTaskCommand(studentIdAlice, INDEX_FIRST_TASK);
        assertEquals(deleteTaskCommand, deleteTaskCommandCopy);
    }

    @Test
    public void equals_sameValuesByModuleCodeAndTask_success() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(moduleCodeAlice, taskAlice);
        DeleteTaskCommand deleteTaskCommandCopy = new DeleteTaskCommand(moduleCodeAlice, taskAlice);
        assertEquals(deleteTaskCommand, deleteTaskCommandCopy);
    }

    @Test
    public void equals_differentTypeByStudentIdAndIndex_failure() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(studentIdAlice, INDEX_FIRST_TASK);
        assertNotEquals(deleteTaskCommand, 1);
    }

    @Test
    public void equals_differentTypeByModuleCode_failure() {
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(moduleCodeAlice, taskAlice);
        assertNotEquals(deleteTaskCommand, 1);
    }

    @Test
    public void constructor_nullIndexByStudentIdAndIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(studentIdAlice, null));
    }

    @Test
    public void constructor_nullStudentIdByStudentIdAndIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, INDEX_FIRST_TASK));
    }

    @Test
    public void constructor_nullTaskByModuleCodeAndTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(moduleCodeAlice, null));
    }

    @Test
    public void constructor_nullModuleCodeByModuleCodeAndTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTaskCommand(null, taskAlice));
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(notPresentStudentId, INDEX_FIRST_TASK);

        assertCommandFailure(deleteTaskCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_moduleCodeNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(invalidModuleCode, taskAlice);

        assertCommandFailure(deleteTaskCommand, model, MESSAGE_MODULE_CODE_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(studentIdAlice, invalidTaskIndexAlice);

        assertCommandFailure(deleteTaskCommand, model, INVALID_TASK_INDEX);

    }

    @Test
    public void execute_taskNotFound_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(moduleCodeAlice, taskToDelete);

        assertCommandFailure(deleteTaskCommand, model, MESSAGE_TASK_NOT_FOUND);

    }

    @Test
    public void execute_deleteTaskByStudentIdAndIndex_success() {

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(studentIdAlice, INDEX_FIRST_TASK);

        Person alice = new PersonBuilder(ALICE).build();

        Person expectedAliceCopy = new PersonBuilder(ALICE).build();

        AddressBook aliceAb = new AddressBookBuilder().withPerson(alice).build();
        AddressBook expectedAliceCopAb = new AddressBookBuilder().withPerson(expectedAliceCopy).build();

        Model model = new ModelManager(aliceAb, new UserPrefs());
        Model expectedModel = new ModelManager(expectedAliceCopAb, new UserPrefs());
        expectedModel.deleteTaskOfPerson(ALICE.getStudentId(), INDEX_FIRST_TASK);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, studentIdAlice);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteTaskByModuleCodeAndTask_success() {
        // Creates new Person with same Module Code as Alice, but does was not assigned the task: Cry
        Person andy = new PersonBuilder(ANDY).build();

        // Creates a copy of andy
        Person andyCopy = new PersonBuilder(ANDY).build();

        Person alice = new PersonBuilder(ALICE).build();
        alice.addTask(taskToDelete);

        Person expectedAliceCopy = new PersonBuilder(ALICE).build();
        expectedAliceCopy.addTask(taskToDelete);

        AddressBook andyAndAmyAb = new AddressBookBuilder().withPerson(andy).withPerson(alice).build();
        AddressBook andyAndAmyAbCopy = new AddressBookBuilder().withPerson(andyCopy).withPerson(expectedAliceCopy)
                .build();

        Model model = new ModelManager(andyAndAmyAb, new UserPrefs());
        Model expectedModel = new ModelManager(andyAndAmyAbCopy, new UserPrefs());
        expectedModel.deleteTaskForAllInModule(moduleCodeAlice, new Task("Cry"));

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(moduleCodeAlice, taskToDelete);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, moduleCodeAlice);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }



}
