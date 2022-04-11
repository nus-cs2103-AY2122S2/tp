package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.assessment.GradeTest.INT_MAX_PLUS_ONE_STRING;
import static seedu.address.storage.JsonAdaptedAttempt.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssessments.TYPICAL_ASSESSMENT_ONE_STUDENT_ALICE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.TaModule;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalStudents;

public class JsonAdaptedAttemptTest {

    private static final String INVALID_ID = "e012345a";
    private static final String VALID_ID = TypicalStudents.ALICE.getStudentId().toString();
    private static final String VALID_GRADE = "1";
    private static final List<Student> students = new ArrayList<>(Arrays.asList(TypicalStudents.ALICE));
    private static final Map.Entry<Student, Grade> ALICE_ENTRY = TYPICAL_ASSESSMENT_ONE_STUDENT_ALICE.entrySet()
            .stream().findFirst().get();
    private static final TaModule VALID_MODULE = TypicalModules.getModule(3);

    @Test
    public void toModelType_validAttemptDetails_returnsAttempt() throws Exception {
        JsonAdaptedAttempt sa = new JsonAdaptedAttempt(TYPICAL_ASSESSMENT_ONE_STUDENT_ALICE.entrySet()
                .iterator().next());
        assertEquals(ALICE_ENTRY, sa.toModelType(VALID_MODULE, students));
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedAttempt sa =
                new JsonAdaptedAttempt(INVALID_ID, VALID_GRADE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(VALID_MODULE, students));
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedAttempt sa = new JsonAdaptedAttempt(null, VALID_GRADE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(VALID_MODULE, students));
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedAttempt sa =
                new JsonAdaptedAttempt(VALID_ID, INT_MAX_PLUS_ONE_STRING);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(VALID_MODULE, students));
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedAttempt sa = new JsonAdaptedAttempt(VALID_ID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(VALID_MODULE, students));
    }

    @Test
    public void toModelType_nonExistentStudent_throwsIllegalValueException() {
        List<Student> studentsList = new ArrayList<>(Arrays.asList(TypicalStudents.BENSON));
        JsonAdaptedAttempt sa =
                new JsonAdaptedAttempt(VALID_ID, VALID_GRADE);
        assertThrows(IllegalValueException.class, () -> sa.toModelType(VALID_MODULE, studentsList));
    }
}
