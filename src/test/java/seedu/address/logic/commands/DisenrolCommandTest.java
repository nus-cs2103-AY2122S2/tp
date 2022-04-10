package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureUnfiltered;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_ENTITY;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.TaModule;
import seedu.address.storage.Storage;

//@@author wxliong
public class DisenrolCommandTest {
    private Storage storage;
    private Model model = new ModelManager(getTypicalTAssist(), new UserPrefs());
    private List<Student> students = new ArrayList<>();
    private List<StudentId> studentsId = new ArrayList<>();

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_success() throws CommandException {
        Student disenrolledStudent = model.getUnfilteredStudentList().get(INDEX_SECOND_ENTITY.getZeroBased());
        students.add(disenrolledStudent);
        DisenrolCommand disenrolCommand = new DisenrolCommand(INDEX_FIRST_ENTITY, students);
//        System.out.println(model.getUnfilteredStudentList());
//        System.out.println("TEST 1 execute");
        CommandResult cr = disenrolCommand.execute(model);
//        System.out.println("TEST 1 done");
        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FIRST_ENTITY.getZeroBased());

        String expectedMessage = String.format(DisenrolCommand.MESSAGE_DISENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        assertFalse(classGroup.hasStudent(disenrolledStudent));
        assertFalse(newModule.hasStudent(disenrolledStudent));
    }

    @Test
    public void execute_validClassIndexAndStudentIdsUnfilteredList_success() throws CommandException {
        studentsId.add(getTypicalStudents().get(INDEX_FIRST_ENTITY.getZeroBased()).getStudentId());
        ObservableList<Student> enrolledStudent = model.getStudentListByStudentIds(studentsId);
        for (Student s : enrolledStudent) {
            students.add(s);
        }
        DisenrolCommand disenrolCommand = new DisenrolCommand(INDEX_THIRD_ENTITY, students);
        System.out.println("TEST 2 execute");
        CommandResult cr = disenrolCommand.execute(model);
        System.out.println("TEST 2 done");

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_THIRD_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FOURTH_ENTITY.getZeroBased());

        String expectedMessage = String.format(DisenrolCommand.MESSAGE_DISENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        for (Student s : enrolledStudent) {
            assertFalse(classGroup.hasStudent(s));
            assertFalse(newModule.hasStudent(s));
        }
    }

    @Test
    public void execute_validClassIndexAndAllStudentsUnfilteredList_success() throws CommandException {
        students = model.getUnfilteredStudentList();
        DisenrolCommand disenrolCommand = new DisenrolCommand(INDEX_SIXTH_ENTITY, students);
        CommandResult cr = disenrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_SIXTH_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_SECOND_ENTITY.getZeroBased());

        String expectedMessage = String.format(DisenrolCommand.MESSAGE_DISENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        String actualMessage = cr.getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
        for (Student s : students) {
            assertFalse(classGroup.hasStudent(s));
            assertFalse(newModule.hasStudent(s));
        }
    }

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_someSuccess() throws CommandException {
        Student disenrolledStudent = model.getUnfilteredStudentList().get(INDEX_THIRD_ENTITY.getZeroBased());
        Student disenrolledNonExistingStudent = model.getUnfilteredStudentList().get(INDEX_FIRST_ENTITY.getZeroBased());
        students.add(disenrolledStudent);
        students.add(disenrolledNonExistingStudent);
        DisenrolCommand disenrolCommand = new DisenrolCommand(INDEX_FOURTH_ENTITY, students);
        CommandResult cr = disenrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FOURTH_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_FIFTH_ENTITY.getZeroBased());

        String existStudent = String.format("\t%s (%s)\n", disenrolledNonExistingStudent.getName(),
                disenrolledNonExistingStudent.getStudentId());
        String existStudentMessage = String.format(DisenrolCommand.NONEXISTENT_STUDENT_CG,
                existStudent);
        String expectedMessage = String.format(DisenrolCommand.MESSAGE_DISENROL_SOME,
                classGroup.getClassGroupId(), classGroup.getClassGroupType()) + existStudentMessage;
        String actualMessage = cr.getFeedbackToUser();

        assertEquals(expectedMessage, actualMessage);
        assertFalse(classGroup.hasStudent(disenrolledStudent));
        assertFalse(newModule.hasStudent(disenrolledStudent));
        //ensure nothing happens to the existing student
        assertFalse(classGroup.hasStudent(disenrolledNonExistingStudent));
        assertFalse(newModule.hasStudent(disenrolledNonExistingStudent));
    }

    @Test
    public void execute_validClassAndStudentIndexUnfilteredList_failure() throws CommandException {
        Student disenrolledExistingStudent = model.getUnfilteredStudentList().get(INDEX_SECOND_ENTITY.getZeroBased());
        students.add(disenrolledExistingStudent);
        DisenrolCommand disenrolCommand = new DisenrolCommand(INDEX_FIFTH_ENTITY, students);
        CommandResult cr = disenrolCommand.execute(model);

        ClassGroup classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIFTH_ENTITY.getZeroBased());
        TaModule newModule = model.getUnfilteredModuleList().get(INDEX_THIRD_ENTITY.getZeroBased());

        String existStudent = String.format("\t%s (%s)\n", disenrolledExistingStudent.getName(),
                disenrolledExistingStudent.getStudentId());
        String existStudentMessage = String.format(DisenrolCommand.NONEXISTENT_STUDENT_CG,
                existStudent);
        String expectedMessage = String.format(DisenrolCommand.MESSAGE_DISENROL_FAILURE,
                classGroup.getClassGroupId(), classGroup.getClassGroupType()) + existStudentMessage;
        String actualMessage = cr.getFeedbackToUser();

        assertEquals(expectedMessage, actualMessage);
        assertFalse(classGroup.hasStudent(disenrolledExistingStudent));
        assertFalse(newModule.hasStudent(disenrolledExistingStudent));
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
