package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103_W13_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2106_T02;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;

public class DeleteTutorialGroupCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidStudentIndexWithFilteredList_failure() {
        // filtered list size of 1
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        // index is larger than size of filteredList
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());

        DeleteTutorialGroupCommand deleteTutorialGroupCommand = new DeleteTutorialGroupCommand(
                outOfBoundIndex, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08));

        assertCommandFailure(deleteTutorialGroupCommand, model,
            DeleteTutorialGroupCommand.MESSAGE_INDEX_OUT_OF_RANGE);
    }

    @Test
    public void execute_deleteTutorialGroup_success() {
        Index indexSecondStudent = INDEX_SECOND_STUDENT;
        Student secondStudent = model.getFilteredStudentList().get(indexSecondStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(secondStudent);
        Student editedStudent = studentInList.withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103_W13_2).build();

        DeleteTutorialGroupCommand deleteTutorialGroupCommand =
                new DeleteTutorialGroupCommand(indexSecondStudent, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2106_T02));

        String expectedMessage = String.format(DeleteTutorialGroupCommand.MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS,
                                               editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStudent(secondStudent, editedStudent);

        assertCommandSuccess(deleteTutorialGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noSuchTutorialGroup_failure() {
        Index indexSecondStudent = INDEX_SECOND_STUDENT;

        DeleteTutorialGroupCommand deleteTutorialGroupCommand =
                new DeleteTutorialGroupCommand(indexSecondStudent, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08));

        assertCommandFailure(deleteTutorialGroupCommand, model,
                DeleteTutorialGroupCommand.MESSAGE_NO_SUCH_TUTORIAL_GROUP);
    }

    @Test
    public void execute_onlyTutorialGroup_failure() {
        Index indexFirstStudent = INDEX_FIRST_STUDENT;

        DeleteTutorialGroupCommand deleteTutorialGroupCommand =
                new DeleteTutorialGroupCommand(indexFirstStudent, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2103_W13_2));

        assertCommandFailure(deleteTutorialGroupCommand, model,
                DeleteTutorialGroupCommand.MESSAGE_CANNOT_DELETE_ONLY_TUTORIAL_GROUP);
    }

    @Test
    public void equals() {
        final DeleteTutorialGroupCommand standardCommand =
                new DeleteTutorialGroupCommand(
                        INDEX_FIRST_STUDENT, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08));

        // same values -> return true
        DeleteTutorialGroupCommand commandWithSameValues =
                new DeleteTutorialGroupCommand(
                        INDEX_FIRST_STUDENT, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteTutorialGroupCommand(
                INDEX_SECOND_STUDENT, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08))));

        // different tutorial group -> returns false
        assertFalse(standardCommand.equals(new DeleteTutorialGroupCommand(
                INDEX_FIRST_STUDENT, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2106_T02))));

    }
}
