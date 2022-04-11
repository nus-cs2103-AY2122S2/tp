package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103_W13_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2106_T02;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;

class DeleteTutorialGroupsFromStudentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_tutorialGroupsToDeleteWithFilteredList_studentDeletedOrEditedInMainListSuccess() throws Exception {
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showStudentAtIndex(model, INDEX_FOURTH_STUDENT);
        showStudentAtIndex(expectedModel, INDEX_FOURTH_STUDENT);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        List<TutorialGroup> tutorialGroupsToDelete = new ArrayList<>(Arrays.asList(
                new TutorialGroup(VALID_TUTORIAL_GROUP_CS2103_W13_2),
                new TutorialGroup(VALID_TUTORIAL_GROUP_CS2106_T02)));

        deleteStudentsOfExpectedModelInTestcase(expectedModel);
        updateStudentsOfExpectedModelInTestcase(expectedModel);

        String expectedMessage = String.format(DeleteTutorialGroupsFromStudentsCommand
                .MESSAGE_TUTORIAL_GROUP_DELETE_SUCCESS, tutorialGroupsToDelete);
        DeleteTutorialGroupsFromStudentsCommand deleteTutorialGroupsFromStudentsCommand =
                new DeleteTutorialGroupsFromStudentsCommand(new HashSet<>(tutorialGroupsToDelete));

        assertCommandSuccess(deleteTutorialGroupsFromStudentsCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        Set<TutorialGroup> firstTutorialGroups = new HashSet<>(
                Arrays.asList(new TutorialGroup(VALID_TUTORIAL_GROUP_CS2106_T02)));
        Set<TutorialGroup> secondTutorialGroups = new HashSet<>(
                Arrays.asList(new TutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3)));
        DeleteTutorialGroupsFromStudentsCommand deleteTutorialGroupsFirstCommand =
                new DeleteTutorialGroupsFromStudentsCommand(firstTutorialGroups);
        DeleteTutorialGroupsFromStudentsCommand deleteTutorialGroupsSecondCommand =
                new DeleteTutorialGroupsFromStudentsCommand(secondTutorialGroups);

        // same object -> returns true
        assertTrue(deleteTutorialGroupsFirstCommand.equals(deleteTutorialGroupsFirstCommand));

        // same values -> returns true
        DeleteTutorialGroupsFromStudentsCommand deleteTutorialGroupsFirstCommandCopy =
                new DeleteTutorialGroupsFromStudentsCommand(firstTutorialGroups);
        assertTrue(deleteTutorialGroupsFirstCommand.equals(deleteTutorialGroupsFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTutorialGroupsFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTutorialGroupsFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteTutorialGroupsFirstCommand.equals(deleteTutorialGroupsSecondCommand));
    }

    private void deleteStudentsOfExpectedModelInTestcase(Model expectedModel) {
        //Students have 1 out of the 2 tutorial groups to be deleted and have 0 tutorial groups after deletion.
        Student firstStudentToDelete = model.getSortedStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        expectedModel.deleteStudent(firstStudentToDelete);
        Student secondStudentToDelete = model.getSortedStudentList().get(INDEX_THIRD_STUDENT.getZeroBased());
        expectedModel.deleteStudent(secondStudentToDelete);
        Student thirdStudentToDelete = model.getSortedStudentList().get(INDEX_FOURTH_STUDENT.getZeroBased());
        expectedModel.deleteStudent(thirdStudentToDelete);
        Student fourthStudentToDelete = model.getSortedStudentList().get(INDEX_SEVENTH_STUDENT.getZeroBased());
        expectedModel.deleteStudent(fourthStudentToDelete);

        //Student have both tutorial groups to be deleted and have 0 tutorial groups after deletion.
        Student fifthStudentToDelete = model.getSortedStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        expectedModel.deleteStudent(fifthStudentToDelete);
    }

    private void updateStudentsOfExpectedModelInTestcase(Model expectedModel) {
        //Student with tutorial group modified and have 1 out of the 2 given tutorial groups.
        Student studentToRemoveOneTutorialGroup = model.getSortedStudentList()
                .get(INDEX_SIXTH_STUDENT.getZeroBased());
        Student studentWithOneTutorialGroupRemoved = new StudentBuilder(studentToRemoveOneTutorialGroup)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        expectedModel.setStudent(studentToRemoveOneTutorialGroup, studentWithOneTutorialGroupRemoved);

        //Student with tutorial group modified and have both of the given tutorial group.
        Student studentToRemoveTwoTutorialGroup = model.getSortedStudentList()
                .get(INDEX_FIFTH_STUDENT.getZeroBased());
        Student studentWithTwoTutorialGroupRemoved = new StudentBuilder(studentToRemoveTwoTutorialGroup)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build();
        expectedModel.setStudent(studentToRemoveTwoTutorialGroup, studentWithTwoTutorialGroupRemoved);

    }
}
