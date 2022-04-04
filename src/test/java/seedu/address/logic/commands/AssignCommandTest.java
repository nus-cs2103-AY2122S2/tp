package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.AssignCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.AssignCommand.MODULE_CODE_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

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

public class AssignCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId studentIdBenson = TypicalPersons.BENSON.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final ModuleCode moduleCodeAlice = TypicalPersons.ALICE.getModuleCode();
    private final ModuleCode moduleCodeBenson = TypicalPersons.BENSON.getModuleCode();
    private final ModuleCode invalidModuleCode = new ModuleCode("CSGO");

    private final Task taskAlice = ALICE.getTaskList().getTaskList().get(0);
    private final Task validTask = new Task("Cry");

    @Test
    public void equals_sameObjectByStudentId_success() {
        AssignCommand assignCommand = new AssignCommand(studentIdAlice, validTask);
        assertEquals(assignCommand, assignCommand);
    }

    @Test
    public void equals_sameObjectByModuleCode_success() {
        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, validTask);
        assertEquals(assignCommand, assignCommand);
    }

    @Test
    public void equals_sameValuesByStudentId_success() {
        AssignCommand assignCommand = new AssignCommand(studentIdAlice, validTask);
        AssignCommand assignCommandCopy = new AssignCommand(studentIdAlice, validTask);
        assertEquals(assignCommand, assignCommandCopy);
    }

    @Test
    public void equals_sameValuesByModuleCode_success() {
        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, validTask);
        AssignCommand assignCommandCopy = new AssignCommand(moduleCodeAlice, validTask);
        assertEquals(assignCommand, assignCommandCopy);
    }

    @Test
    public void equals_differentTypeByStudentId_failure() {
        AssignCommand assignCommand = new AssignCommand(studentIdAlice, validTask);
        assertNotEquals(assignCommand, 1);
    }

    @Test
    public void equals_differentTypeByModuleCode_failure() {
        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, validTask);
        assertNotEquals(assignCommand, 1);
    }

    @Test
    public void constructor_nullTaskByStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(studentIdAlice, null));
    }

    @Test
    public void constructor_nullTaskByModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(moduleCodeAlice, null));
    }

    @Test
    public void constructor_nullStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand((StudentId) null, validTask));
    }

    @Test
    public void constructor_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand((ModuleCode) null, validTask));
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(notPresentStudentId, validTask);

        assertCommandFailure(assignCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_moduleCodeNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(invalidModuleCode, validTask);

        assertCommandFailure(assignCommand, model, MODULE_CODE_NOT_FOUND);
    }

    @Test
    public void execute_duplicateTaskByStudentId_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(studentIdAlice, taskAlice);

        assertCommandFailure(assignCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskByModuleCode_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, taskAlice);

        assertCommandFailure(assignCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    // Fix this test case
    @Test
    public void execute_partialDuplicateTaskException_success() {

        // Creates new Person with same Module Code as Alice, but does was not assigned her task (TASK A).
        Person andy = new PersonBuilder()
                .withStudentId("A1111111Z")
                .withName("Andy")
                .withModuleCode("CS2101")
                .withPhone("10000000")
                .withTelegramHandle("andyyy")
                .withEmail("andyyy@u.nus.edu")
                .build();

        // Creates a copy of andy, which has been assigned the task, Task A.
        Person andyCopy = new PersonBuilder()
                .withStudentId("A1111111Z")
                .withName("Andy")
                .withModuleCode("CS2101")
                .withPhone("10000000")
                .withTelegramHandle("andyyy")
                .withEmail("andyyy@u.nus.edu")
                .withTaskList("Task A", false)
                .build();

        Person alice = new PersonBuilder(ALICE).build();

        AddressBook andyAndAmyAb = new AddressBookBuilder().withPerson(andy).withPerson(alice).build();
        AddressBook andyAndAmyAbCopy = new AddressBookBuilder().withPerson(andyCopy).withPerson(ALICE).build();

        Model model = new ModelManager(andyAndAmyAb, new UserPrefs());
        Model expectedModel = new ModelManager(andyAndAmyAbCopy, new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, taskAlice);

        String expectedMessage = String.format(MESSAGE_SUCCESS, taskAlice.getTaskName())
                + ", to some students with Module Code: " + moduleCodeAlice.toString();

        System.out.println(expectedMessage);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskByStudentId_success() {
        AssignCommand assignCommand = new AssignCommand(studentIdAlice, validTask);

        Person alice = new PersonBuilder(ALICE).build();

        Person expectedAliceCopy = new PersonBuilder()
                .withStudentId(ALICE.getStudentId().id)
                .withName(ALICE.getName().toString())
                .withModuleCode(ALICE.getModuleCode().moduleCode)
                .withPhone(ALICE.getPhone().value)
                .withTelegramHandle(ALICE.getTelegramHandle().telegramHandle)
                .withEmail((ALICE.getEmail().value))
                .withTaskList("Task A", false)
                .withTaskList("Cry", false) // This is validTask
                .build();

        AddressBook aliceAb = new AddressBookBuilder().withPerson(alice).build();
        AddressBook expectedAliceCopAb = new AddressBookBuilder().withPerson(expectedAliceCopy).build();

        Model model = new ModelManager(aliceAb, new UserPrefs());
        Model expectedModel = new ModelManager(expectedAliceCopAb, new UserPrefs());

        String expectedMessage = String.format(MESSAGE_SUCCESS, taskAlice.getTaskName() + ", to student with ID: "
                + studentIdAlice);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskByModuleCode_success() {

        // Creates new Person with same Module Code as Alice, but does was not assigned the task: Cry
        Person andy = new PersonBuilder()
                .withStudentId("A1111111Z")
                .withName("Andy")
                .withModuleCode("CS2101")
                .withPhone("10000000")
                .withTelegramHandle("andyyy")
                .withEmail("andyyy@u.nus.edu")
                .build();

        // Creates a copy of andy, which has been assigned the task: Cry
        Person andyCopy = new PersonBuilder()
                .withStudentId("A1111111Z")
                .withName("Andy")
                .withModuleCode("CS2101")
                .withPhone("10000000")
                .withTelegramHandle("andyyy")
                .withEmail("andyyy@u.nus.edu")
                .withTaskList("Cry", false)
                .build();

        Person alice = new PersonBuilder(ALICE).build();

        Person expectedAliceCopy = new PersonBuilder()
                .withStudentId(ALICE.getStudentId().id)
                .withName(ALICE.getName().toString())
                .withModuleCode(ALICE.getModuleCode().moduleCode)
                .withPhone(ALICE.getPhone().value)
                .withTelegramHandle(ALICE.getTelegramHandle().telegramHandle)
                .withEmail((ALICE.getEmail().value))
                .withTaskList("Task A", false)
                .withTaskList("Cry", false) // This is validTask
                .build();


        AddressBook andyAndAmyAb = new AddressBookBuilder().withPerson(andy).withPerson(alice).build();
        AddressBook andyAndAmyAbCopy = new AddressBookBuilder().withPerson(andyCopy).withPerson(expectedAliceCopy)
                .build();

        Model model = new ModelManager(andyAndAmyAb, new UserPrefs());
        Model expectedModel = new ModelManager(andyAndAmyAbCopy, new UserPrefs());

        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, validTask);

        String expectedMessage = String.format(MESSAGE_SUCCESS, validTask.getTaskName()
                + ", to all students with Module Code: " + moduleCodeAlice);

        System.out.println(expectedMessage);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }
}
