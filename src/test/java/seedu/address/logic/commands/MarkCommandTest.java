package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkCommand.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.MarkCommand.MARKED_TASK_SUCCESS;
import static seedu.address.logic.commands.MarkCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.MarkCommand.TASK_ALREADY_DONE;
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


public class MarkCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId studentIdBenson = TypicalPersons.BENSON.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final Index indexOne = Index.fromOneBased(1);
    private final Index invalidIndex = Index.fromZeroBased(2000);

    @Test
    public void equals_sameObject_success() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);
        assertTrue(markCommand.equals(markCommand));
    }

    @Test
    public void equals_sameValues_success() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);
        MarkCommand markCommandCopy = new MarkCommand(studentIdAlice, indexOne);
        assertTrue(markCommand.equals(markCommandCopy));
    }

    @Test
    public void equals_differentType_failure() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);
        assertFalse(markCommand.equals(1));
    }

    @Test
    public void constructor_nullStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkCommand(null, indexOne));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkCommand(studentIdAlice, null));
    }

    @Test
    public void execute_studentNotPresent_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        MarkCommand markCommand = new MarkCommand(notPresentStudentId, indexOne);
        assertCommandFailure(markCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        MarkCommand markCommand = new MarkCommand(studentIdAlice, invalidIndex);
        assertCommandFailure(markCommand, model, INVALID_TASK_INDEX);
    }

    @Test
    public void execute_taskAlreadyComplete_throwsCommandException() {
        Person bensonCopy = new PersonBuilder()
                .withStudentId(BENSON.getStudentId().id)
                .withName(BENSON.getName().toString())
                .withModuleCode(BENSON.getModuleCode().moduleCode)
                .withPhone(BENSON.getPhone().value)
                .withTelegramHandle(BENSON.getTelegramHandle().telegramHandle)
                .withEmail(BENSON.getEmail().value)
                .withTaskList("Task B", true)
                .build();

        AddressBook bensonCopyAb = new AddressBookBuilder().withPerson(bensonCopy).build();

        Model model = new ModelManager(bensonCopyAb, new UserPrefs());

        MarkCommand markCommand = new MarkCommand(studentIdBenson, indexOne);
        assertCommandFailure(markCommand, model, TASK_ALREADY_DONE);
    }

    @Test
    public void execute_taskMarkAsDone_success() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);
        Person alice = new PersonBuilder(ALICE).build();
        Person expectedAliceCopy = new PersonBuilder()
                .withStudentId(ALICE.getStudentId().id)
                .withName(ALICE.getName().toString())
                .withModuleCode(ALICE.getModuleCode().moduleCode)
                .withPhone(ALICE.getPhone().value)
                .withTelegramHandle(ALICE.getTelegramHandle().telegramHandle)
                .withEmail(ALICE.getEmail().value)
                .withTaskList("Task A", true)
                .build();

        AddressBook aliceAb = new AddressBookBuilder().withPerson(alice).build();
        AddressBook expectedAliceCopyAb = new AddressBookBuilder().withPerson(expectedAliceCopy).build();

        Model model = new ModelManager(aliceAb, new UserPrefs());
        Model expectedModel = new ModelManager(expectedAliceCopyAb, new UserPrefs());

        assertCommandSuccess(markCommand, model, String.format(MARKED_TASK_SUCCESS, studentIdAlice), expectedModel);
    }
}
