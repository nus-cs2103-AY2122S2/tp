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
import seedu.address.model.lab.LabStatus;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class SubmitLabCommandTest {

    private static final int VALID_LABNUMBER = 1;

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validParameters_success() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Student personToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedPerson = new StudentBuilder(personToEdit).build();
        LabList listToEdit = editedPerson.getLabs();
        Lab labToEdit = listToEdit.getLab(VALID_LABNUMBER);
        listToEdit.setLab(labToEdit, new Lab(String.valueOf(VALID_LABNUMBER)).of(LabStatus.SUBMITTED));

        SubmitLabCommand command = new SubmitLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER);

        String expectedMessage = String.format(SubmitLabCommand.MESSAGE_SUBMIT_LAB_SUCCESS,
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
        SubmitLabCommand command = new SubmitLabCommand(outOfBoundIndex, VALID_LABNUMBER);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);

        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        SubmitLabCommand command = new SubmitLabCommand(outOfBoundIndex, VALID_LABNUMBER);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLabNumber_throwsCommandException() {
        int invalidLabNumber = 0;
        SubmitLabCommand command = new SubmitLabCommand(INDEX_FIRST_STUDENT, invalidLabNumber);

        assertCommandFailure(command, model, MESSAGE_INVALID_LAB);
    }

    @Test
    public void execute_labStatusNotUnsubmitted_throwsCommandException() {
        LabList listToEdit = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased()).getLabs();
        Lab labToEdit = listToEdit.getLab(VALID_LABNUMBER);
        listToEdit.setLab(labToEdit, labToEdit.editLabStatus(LabStatus.SUBMITTED));
        SubmitLabCommand command = new SubmitLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER);

        assertCommandFailure(command, model, String.format(EditLabCommand.MESSAGE_CURRENT_LABSTATUS_INVALID,
                labToEdit.labNumber, LabStatus.SUBMITTED));
    }

    @Test
    public void equals() {
        SubmitLabCommand standardCommand = new SubmitLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER);
        SubmitLabCommand changeIndexCommand = new SubmitLabCommand(INDEX_SECOND_STUDENT, VALID_LABNUMBER);
        SubmitLabCommand changeLabCommand = new SubmitLabCommand(INDEX_FIRST_STUDENT, 2);
        EditLabCommand differentTypeCommand =
                new EditLabCommand(INDEX_FIRST_STUDENT, VALID_LABNUMBER, LabStatus.SUBMITTED);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        SubmitLabCommand standardCommandCopy = new SubmitLabCommand(INDEX_FIRST_STUDENT, 1);
        assertTrue(standardCommand.equals(standardCommandCopy));
        assertTrue(standardCommand.equals(differentTypeCommand));

        // different values -> returns false
        assertFalse(standardCommand.equals(changeIndexCommand));
        assertFalse(standardCommand.equals(changeLabCommand));
        assertFalse(changeIndexCommand.equals(changeLabCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }
}
