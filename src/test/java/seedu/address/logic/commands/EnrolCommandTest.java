package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureFiltered;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureUnfiltered;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEntityAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTITY;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.TaModule;
import seedu.address.storage.Storage;
import seedu.address.testutil.TypicalStudents;

import static seedu.address.testutil.TypicalStudents.*;

//@@author wxliong
public class EnrolCommandTest {
    private Model model = new ModelManager(getTypicalTAssist(), new UserPrefs());
    private List<Student> students = new ArrayList<>();
    private List<StudentId> studentsId = new ArrayList<>();

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_success() throws CommandException {
        Student enrolledStudent = model.getUnfilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
        students.add(enrolledStudent);
        EnrolCommand enrolCommand = new EnrolCommand(INDEX_FIRST_ENTITY, students);
        CommandResult cr = enrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FIRST_ENTITY.getZeroBased());

        String expectedMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        assertTrue(classGroup.hasStudent(enrolledStudent));
        assertTrue(newModule.hasStudent(enrolledStudent));
    }

    @Test
    public void execute_validClassIndexAndStudentIdsUnfilteredList_success() throws CommandException {
        studentsId.add(getTypicalStudents().get(2).getStudentId());
        studentsId.add(getTypicalStudents().get(3).getStudentId());
        ObservableList<Student> enrolledStudent = model.getStudentListByStudentIds(studentsId);
        for (Student s : enrolledStudent) {
            students.add(s);
        }
        EnrolCommand enrolCommand = new EnrolCommand(INDEX_FIRST_ENTITY, students);
        CommandResult cr = enrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FIRST_ENTITY.getZeroBased());

        String expectedMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        for (Student s : enrolledStudent) {
            assertTrue(classGroup.hasStudent(s));
            assertTrue(newModule.hasStudent(s));
        }
    }

    @Test
    public void execute_validClassIndexAndAllStudentsUnfilteredList_success() throws CommandException {
        students = model.getUnfilteredStudentList();
        EnrolCommand enrolCommand = new EnrolCommand(INDEX_SECOND_ENTITY, students);
        CommandResult cr = enrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_SECOND_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FIRST_ENTITY.getZeroBased());

        String expectedMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        for (Student s : students) {
            assertTrue(classGroup.hasStudent(s));
            assertTrue(newModule.hasStudent(s));
        }
    }

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_someSuccess() throws CommandException {
        Student enrolledStudent = model.getUnfilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
        Student enrolledExistingStudent = model.getUnfilteredStudentList().get(INDEX_SECOND_ENTITY.getZeroBased());
        students.add(enrolledStudent);
        students.add(enrolledExistingStudent);
        EnrolCommand enrolCommand = new EnrolCommand(INDEX_FIRST_ENTITY, students);
        CommandResult cr = enrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        TaModule newModule = classGroup.getModule();

        String existStudent = String.format("\t%s (%s)\n", enrolledExistingStudent.getName(),
                enrolledExistingStudent.getStudentId());
        String existStudentMessage = String.format(EnrolCommand.STUDENT_EXISTS_CG,
                existStudent);
        String expectedMessage = String.format(EnrolCommand.MESSAGE_ENROL_SOME,
                classGroup.getClassGroupId(), classGroup.getClassGroupType()) + existStudentMessage;
        String actualMessage = cr.getFeedbackToUser();

        assertEquals(expectedMessage, actualMessage);
        assertTrue(classGroup.hasStudent(enrolledStudent));
        assertTrue(newModule.hasStudent(enrolledStudent));
        //ensure nothing happens to the existing student
        assertTrue(classGroup.hasStudent(enrolledExistingStudent));
        assertTrue(newModule.hasStudent(enrolledExistingStudent));
    }

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_failure() throws CommandException {
        Student enrolledExistingStudent = model.getUnfilteredStudentList().get(INDEX_SECOND_ENTITY.getZeroBased());
        students.add(enrolledExistingStudent);
        EnrolCommand enrolCommand = new EnrolCommand(INDEX_FIRST_ENTITY, students);
        CommandResult cr = enrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        TaModule newModule = classGroup.getModule();

        String existStudent = String.format("\t%s (%s)\n", enrolledExistingStudent.getName(),
                enrolledExistingStudent.getStudentId());
        String existStudentMessage = String.format(EnrolCommand.STUDENT_EXISTS_CG,
                existStudent);
        String expectedMessage = String.format(EnrolCommand.MESSAGE_ENROL_FAILURE,
                classGroup.getClassGroupId(), classGroup.getClassGroupType()) + existStudentMessage;
        String actualMessage = cr.getFeedbackToUser();

        assertEquals(expectedMessage, actualMessage);
        assertTrue(classGroup.hasStudent(enrolledExistingStudent));
        assertTrue(newModule.hasStudent(enrolledExistingStudent));
    }

    @Test
    public void execute_invalidClassIndexAndValidStudentUnfilteredList_success() {
        Index invalidIndex = Index.fromOneBased(model.getUnfilteredClassGroupList().size() + 1);
        Student enrolledStudent = model.getUnfilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
        students.add(enrolledStudent);
        EnrolCommand enrolCommand = new EnrolCommand(invalidIndex, students);
        String expectedMessage = String.format(EnrolCommand.NONEXISTENT_CG,
                invalidIndex.getOneBased(), EnrolCommand.MESSAGE_USAGE);
        assertCommandFailureUnfiltered(
                enrolCommand, model, EntityType.CLASS_GROUP, expectedMessage);
    }
}
