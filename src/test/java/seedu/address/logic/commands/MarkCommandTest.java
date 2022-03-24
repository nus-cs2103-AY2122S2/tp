package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkCommand.INVALID_TASK_INDEX;
import static seedu.address.logic.commands.MarkCommand.MARKED_TASK_SUCCESS;
import static seedu.address.logic.commands.MarkCommand.MESSAGE_PERSON_NOT_FOUND;
import static seedu.address.logic.commands.MarkCommand.TASK_ALREADY_DONE;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.testutil.TypicalPersons;


public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final StudentId studentIdAlice = new StudentId(TypicalPersons.ALICE.getStudentId().toString());
    private final StudentId studentIdBenson = new StudentId(TypicalPersons.BENSON.getStudentId().toString());
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
        MarkCommand markCommand = new MarkCommand(notPresentStudentId, indexOne);
        assertCommandFailure(markCommand, model, MESSAGE_PERSON_NOT_FOUND);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, invalidIndex);
        assertCommandFailure(markCommand, model, INVALID_TASK_INDEX);
    }

    @Test
    public void execute_taskAlreadyComplete_throwsCommandException() {
        MarkCommand markCommand = new MarkCommand(studentIdBenson, indexOne);
        assertCommandFailure(markCommand, model, TASK_ALREADY_DONE);
    }

    @Test
    public void execute_taskMarkAsDone_success() {
        MarkCommand markCommand = new MarkCommand(studentIdAlice, indexOne);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        //Marks Bob's first task as done in the expected model.
        expectedModel.markTaskOfPerson(studentIdAlice, indexOne);

        assertCommandSuccess(markCommand, model, String.format(MARKED_TASK_SUCCESS, studentIdAlice), expectedModel);
    }
}
