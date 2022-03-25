package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lab.Lab;
import seedu.address.model.lab.LabList;
import seedu.address.model.lab.LabMark;
import seedu.address.model.lab.LabStatus;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class GradeLabCommandTest {

    private static final int VALID_LABNUMBER = 1;
    private static final LabMark VALID_LABMARK = new LabMark("10");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validParameters_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student personToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(personToEdit).build();
        LabList listToEdit = editedPerson.getLabs();
        Lab labToEdit = listToEdit.getLab(VALID_LABNUMBER);
        listToEdit.setLab(labToEdit, new Lab(String.valueOf(VALID_LABNUMBER)).of(VALID_LABMARK));

        GradeLabCommand command = new GradeLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, new LabMark("10"));

        String expectedMessage = String.format(GradeLabCommand.MESSAGE_GRADE_LAB_SUCCESS,
                VALID_LABNUMBER, personToEdit.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedPerson);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        GradeLabCommand command = new GradeLabCommand(outOfBoundIndex, VALID_LABNUMBER, VALID_LABMARK);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        GradeLabCommand command = new GradeLabCommand(outOfBoundIndex, VALID_LABNUMBER, VALID_LABMARK);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLabNumber_throwsCommandException() {
        int invalidLabNumber = 0;
        GradeLabCommand command = new GradeLabCommand(INDEX_FIRST_STUDENT, invalidLabNumber, VALID_LABMARK);

        assertCommandFailure(command, model, MESSAGE_INVALID_LAB);
    }

    @Test
    public void execute_invalidMark_throwsCommandException() {
        String invalidMark = "01";
        GradeLabCommand command = new GradeLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, new LabMark(invalidMark));

        assertCommandFailure(command, model, LabMark.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_labStatusAlreadyGraded_throwsCommandException() {
        Lab labToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased())
                .getLabs().getLab(VALID_LABNUMBER);
        Lab gradedLab = labToEdit.of(VALID_LABMARK);
        model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()).getLabs().setLab(labToEdit, gradedLab);
        GradeLabCommand command = new GradeLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_LABMARK);

        assertCommandFailure(command, model, GradeLabCommand.MESSAGE_LAB_ALREADY_GRADED);
    }

    @Test
    public void isValidCommand() {
        assertTrue((new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED,
                VALID_LABMARK)).isValidCommand());

        // marks not initialized -> returns false
        assertFalse((new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED,
                new LabMark())).isValidCommand());

        // status not GRADED -> returns false
        assertFalse((new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED)).isValidCommand());
    }

    @Test
    public void equals() {
        GradeLabCommand standardCommand = new GradeLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_LABMARK);
        GradeLabCommand changeIndexCommand = new GradeLabCommand(INDEX_SECOND_STUDENT, VALID_LABNUMBER, VALID_LABMARK);
        GradeLabCommand changeLabCommand = new GradeLabCommand(INDEX_FIRST_STUDENT, 2, VALID_LABMARK);
        GradeLabCommand changeMarkCommand = new GradeLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER,
                new LabMark("20"));
        EditLabCommand differentTypeCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED, VALID_LABMARK);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        GradeLabCommand standardCommandCopy = new GradeLabCommand(INDEX_FIRST_STUDENT, 1, new LabMark("10"));
        assertTrue(standardCommand.equals(standardCommandCopy));
        assertTrue(standardCommand.equals(differentTypeCommand));

        // different values -> returns false
        assertFalse(standardCommand.equals(changeIndexCommand));
        assertFalse(standardCommand.equals(changeLabCommand));
        assertFalse(standardCommand.equals(changeMarkCommand));
        assertFalse(changeIndexCommand.equals(changeLabCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }
}
