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
import static seedu.address.testutil.TypicalPersons.ANDY;
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
import seedu.address.model.person.exceptions.PartialDuplicateTaskException;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class AssignCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final ModuleCode moduleCodeAlice = TypicalPersons.ALICE.getModuleCode();
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

    @Test
    public void execute_partialDuplicateTaskException_success() {

        Person andy = new PersonBuilder(ANDY).build();

        Person andyCopy = new PersonBuilder(ANDY).build();

        Person alice = new PersonBuilder(ALICE).build();

        AddressBook andyAndAmyAb = new AddressBookBuilder().withPerson(andy).withPerson(alice).build();
        AddressBook andyAndAmyAbCopy = new AddressBookBuilder().withPerson(andyCopy).withPerson(ALICE).build();

        Model model = new ModelManager(andyAndAmyAb, new UserPrefs());
        Model expectedModel = new ModelManager(andyAndAmyAbCopy, new UserPrefs());

        // Assign task that has already been assigned to some students will throw exception, but will still return.
        try {
            expectedModel.assignTaskToAllInModule(moduleCodeAlice, taskAlice);
        } catch (PartialDuplicateTaskException e) {
            // Do nothing
        }

        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, taskAlice);

        String expectedMessage = String.format(MESSAGE_SUCCESS, taskAlice.getTaskName())
                + ", to some students with Module Code: " + moduleCodeAlice.toString();

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskByStudentId_success() {
        AssignCommand assignCommand = new AssignCommand(studentIdAlice, validTask);

        Person alice = new PersonBuilder(ALICE).build();

        Person expectedAliceCopy = new PersonBuilder(ALICE).build();

        AddressBook aliceAb = new AddressBookBuilder().withPerson(alice).build();
        AddressBook expectedAliceCopAb = new AddressBookBuilder().withPerson(expectedAliceCopy).build();

        Model model = new ModelManager(aliceAb, new UserPrefs());
        Model expectedModel = new ModelManager(expectedAliceCopAb, new UserPrefs());
        expectedModel.assignTaskToPerson(ALICE.getStudentId(), new Task("Cry"));

        String expectedMessage = String.format(MESSAGE_SUCCESS, "Cry" + ", to student with ID: "
                + studentIdAlice);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignTaskByModuleCode_success() {

        // Creates new Person with same Module Code as Alice, but does was not assigned the task: Cry
        Person andy = new PersonBuilder(ANDY).build();

        // Creates a copy of andy, which will be assigned the task: Cry
        Person andyCopy = new PersonBuilder(ANDY).build();

        Person alice = new PersonBuilder(ALICE).build();

        Person expectedAliceCopy = new PersonBuilder(ALICE).build();

        AddressBook andyAndAmyAb = new AddressBookBuilder().withPerson(andy).withPerson(alice).build();
        AddressBook andyAndAmyAbCopy = new AddressBookBuilder().withPerson(andyCopy).withPerson(expectedAliceCopy)
                .build();

        Model model = new ModelManager(andyAndAmyAb, new UserPrefs());
        Model expectedModel = new ModelManager(andyAndAmyAbCopy, new UserPrefs());
        expectedModel.assignTaskToAllInModule(moduleCodeAlice, new Task("Cry"));

        AssignCommand assignCommand = new AssignCommand(moduleCodeAlice, validTask);

        String expectedMessage = String.format(MESSAGE_SUCCESS, validTask.getTaskName()
                + ", to all students with Module Code: " + moduleCodeAlice);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

}
