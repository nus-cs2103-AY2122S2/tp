package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UnmarkCommand.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.UnmarkCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.UnmarkCommand.TASK_ALREADY_NOT_DONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;


public class UnmarkCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId studentIdBenson = TypicalPersons.BENSON.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final Index indexOne = Index.fromOneBased(1);
    private final Index invalidIndex = Index.fromZeroBased(2000);

    @Test
    public void equals_sameObject_success() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdBenson, indexOne);
        assertEquals(unmarkCommand, unmarkCommand);
    }

    @Test
    public void equals_sameValues_success() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdBenson, indexOne);
        UnmarkCommand unmarkCommandCopy = new UnmarkCommand(studentIdBenson, indexOne);
        assertEquals(unmarkCommandCopy, unmarkCommand);
    }

    @Test
    public void equals_differentType_failure() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdBenson, indexOne);
        assertNotEquals(unmarkCommand, 1);
    }

    @Test
    public void constructor_nullStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnmarkCommand(null, indexOne));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnmarkCommand(studentIdBenson, null));
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        UnmarkCommand unmarkCommand = new UnmarkCommand(notPresentStudentId, indexOne);
        assertCommandFailure(unmarkCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdBenson, invalidIndex);
        assertCommandFailure(unmarkCommand, model, INVALID_TASK_INDEX);
    }

    @Test
    public void execute_taskAlreadyNotComplete_throwsCommandException() {
        Person aliceCopy = new PersonBuilder()
                .withStudentId(ALICE.getStudentId().id)
                .withName(ALICE.getName().toString())
                .withModuleCode(ALICE.getModuleCode().moduleCode)
                .withPhone(ALICE.getPhone().value)
                .withTelegramHandle(ALICE.getTelegramHandle().telegramHandle)
                .withEmail(ALICE.getEmail().value)
                .withTaskList("Task A", false)
                .build();

        AddressBook aliceCopyAb = new AddressBookBuilder().withPerson(aliceCopy).build();

        Model model = new ModelManager(aliceCopyAb, new UserPrefs());

        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdAlice, indexOne);
        assertCommandFailure(unmarkCommand, model, TASK_ALREADY_NOT_DONE);
    }

    @Test
    public void execute_taskMarkAsNotDone_success() {
        UnmarkCommand unmarkCommand = new UnmarkCommand(studentIdBenson, indexOne);
        Person benson = new PersonBuilder(BENSON).build();
        Person expectedBensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList("Task B", false)
                .build();

        AddressBook bensonAb = new AddressBookBuilder().withPerson(benson).build();
        AddressBook expectedBensonCopyAb = new AddressBookBuilder().withPerson(expectedBensonCopy).build();

        Model model = new ModelManager(bensonAb, new UserPrefs());
        Model expectedModel = new ModelManager(expectedBensonCopyAb, new UserPrefs());

        assertCommandSuccess(unmarkCommand, model, String.format(UnmarkCommand.MARKED_TASK_SUCCESS, studentIdBenson),
                expectedModel);
    }
}
