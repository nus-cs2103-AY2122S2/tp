package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkCommand.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.MarkCommand.MARKED_TASK_SUCCESS;
import static seedu.address.logic.commands.MarkCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.MarkCommand.TASK_ALREADY_DONE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalPersons;


public class MarkCommandTest {

    private final StudentId studentIdAlice = TypicalPersons.ALICE.getStudentId();
    private final StudentId studentIdBenson = TypicalPersons.BENSON.getStudentId();
    private final StudentId notPresentStudentId = new StudentId("A0000000Z");

    private final Index indexOne = Index.fromOneBased(1);
    private final Index indexTwo = Index.fromOneBased(2);
    private final Index invalidIndex = Index.fromZeroBased(2000);


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
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        MarkCommand markCommand = new MarkCommand(studentIdBenson, indexOne);
        assertCommandFailure(markCommand, model, TASK_ALREADY_DONE);
    }

    @Test
    public void execute_taskMarkAsDone_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person bensonCopy = expectedModel.getFilteredPersonList().get(1);

        //Marks Benson's first task as done in the expected model.
        bensonCopy.markTaskAsComplete(0);

        assertCommandSuccess(markCommand, model, String.format(MARKED_TASK_SUCCESS, studentIdAlice), expectedModel);
    }
}
