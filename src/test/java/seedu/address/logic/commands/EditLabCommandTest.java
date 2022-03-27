package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_LAB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.logic.commands.EditLabCommand.MESSAGE_INVALID_COMBINATION;
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

public class EditLabCommandTest {

    private static final int VALID_LABNUMBER = 1;
    private static final LabMark VALID_LABMARK = new LabMark("10");
    private static final LabMark VALID_EDITED_LABMARK = new LabMark("20");

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_editStatus_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit).build();
        LabList listToEdit = editedStudent.getLabs();
        listToEdit.setLab(listToEdit.getLab(VALID_LABNUMBER),
                new Lab(String.valueOf(VALID_LABNUMBER)).of(LabStatus.SUBMITTED));

        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, VALID_LABNUMBER,
                studentToEdit.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editMarks_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        LabList listToEdit = studentToEdit.getLabs();
        listToEdit.setLab(listToEdit.getLab(VALID_LABNUMBER),
                new Lab(String.valueOf(VALID_LABNUMBER)).of(VALID_LABMARK)); // grading the Lab first

        Student editedStudent = new StudentBuilder(studentToEdit).build();
        listToEdit = editedStudent.getLabs();
        listToEdit.setLab(listToEdit.getLab(VALID_LABNUMBER),
                new Lab(String.valueOf(VALID_LABNUMBER)).of(VALID_EDITED_LABMARK));

        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_EDITED_LABMARK);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, VALID_LABNUMBER,
                studentToEdit.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editStatusAndMark_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder(studentToEdit).build();
        LabList listToEdit = editedStudent.getLabs();
        Lab labToEdit = listToEdit.getLab(VALID_LABNUMBER);
        listToEdit.setLab(labToEdit, labToEdit.of(VALID_LABMARK));

        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER,
                LabStatus.GRADED, VALID_LABMARK);

        String expectedMessage = String.format(EditLabCommand.MESSAGE_EDIT_LAB_SUCCESS, VALID_LABNUMBER,
                studentToEdit.getName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditLabCommand command = new EditLabCommand(outOfBoundIndex, VALID_LABNUMBER, LabStatus.SUBMITTED);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        EditLabCommand command = new EditLabCommand(outOfBoundIndex, VALID_LABNUMBER, LabStatus.SUBMITTED);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidCommandSubmitWithMarks_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED,
                VALID_LABMARK);

        assertCommandFailure(command, model, MESSAGE_INVALID_COMBINATION);
    }

    @Test
    public void execute_invalidCommandGradeWithoutMarks_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED);

        assertCommandFailure(command, model, MESSAGE_INVALID_COMBINATION);
    }

    @Test
    public void execute_invalidLabNumber_throwsCommandException() {
        int invalidLabNumber = 0;
        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, invalidLabNumber, LabStatus.SUBMITTED);

        assertCommandFailure(command, model, MESSAGE_INVALID_LAB);
    }

    @Test
    public void execute_sameLabStatus_throwsCommandException() {
        LabStatus currentStatus = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased())
                .getLabs().getLab(VALID_LABNUMBER).labStatus;
        EditLabCommand command = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, currentStatus);

        assertCommandFailure(command, model, EditLabCommand.MESSAGE_IDENTICAL_LAB);
    }

    @Test
    public void execute_sameLabMarks_throwsCommandException() {
        LabList listToEdit = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased()).getLabs();
        Lab labToEdit = listToEdit.getLab(VALID_LABNUMBER);
        listToEdit.setLab(labToEdit, labToEdit.of(VALID_LABMARK));

        EditLabCommand command = new EditLabCommand(INDEX_SECOND_STUDENT, VALID_LABNUMBER, VALID_LABMARK);

        assertCommandFailure(command, model, EditLabCommand.MESSAGE_IDENTICAL_LAB);
    }

    @Test
    public void isValidCommand() {
        EditLabCommand stub = new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_LABMARK);

        assertTrue(stub.isValidCommand(new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED,
                VALID_LABMARK)));
        assertTrue(stub.isValidCommand(new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED)));

        // marks not initialized -> returns false
        assertFalse(stub.isValidCommand(new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.GRADED)));

        // not graded but marks initialized -> returns false
        assertFalse(stub.isValidCommand(new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED,
                new LabMark("10"))));
    }

    @Test
    public void equals() {
        EditLabCommand standardCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED);
        EditLabCommand changeIndexCommand =
                new EditLabCommand(INDEX_SECOND_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED);
        EditLabCommand changeLabCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, 2, LabStatus.SUBMITTED);
        EditLabCommand changeStatusCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.UNSUBMITTED);
        EditLabCommand changeMarksCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_LABMARK);
        EditLabCommand differentMarksCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, VALID_EDITED_LABMARK);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        EditLabCommand standardCommandCopy =
                new EditLabCommand(INDEX_FIRST_STUDENT, 1, LabStatus.SUBMITTED);
        assertTrue(standardCommand.equals(standardCommandCopy));

        // different values -> returns false
        assertFalse(standardCommand.equals(changeIndexCommand));
        assertFalse(standardCommand.equals(changeLabCommand));
        assertFalse(standardCommand.equals(changeStatusCommand));
        assertFalse(changeIndexCommand.equals(changeLabCommand));
        assertFalse(standardCommand.equals(changeMarksCommand));
        assertFalse(changeMarksCommand.equals(differentMarksCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }
}
