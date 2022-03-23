package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewStudentInfoCommand}.
 */
public class ViewStudentInfoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student selectedStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        ViewStudentInfoCommand viewCommand = new ViewStudentInfoCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ViewStudentInfoCommand.MESSAGE_VIEW_SUCCESS, selectedStudent.getName());

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.setSelectedStudent(selectedStudent);

        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfiliteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ViewStudentInfoCommand viewCommand = new ViewStudentInfoCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewStudentInfoCommand firstCommand = new ViewStudentInfoCommand(INDEX_FIRST_STUDENT);
        ViewStudentInfoCommand secondCommand = new ViewStudentInfoCommand(INDEX_SECOND_STUDENT);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ViewStudentInfoCommand firstCommandCopy = new ViewStudentInfoCommand(INDEX_FIRST_STUDENT);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different student -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
