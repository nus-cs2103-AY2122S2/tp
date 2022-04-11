package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAssessment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;
import seedu.address.testutil.TypicalAssessments;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalStudents;

public class JsonAdaptedAssessmentTest {
    private static final String INVALID_ASSESSMENT_NAME = "Participation!";
    private static final String INVALID_SIMPLE_NAME = "class part";

    private static final String VALID_ASSESSMENT_NAME = TypicalAssessments.LAB1;
    private static final String VALID_SIMPLE_NAME = TypicalAssessments.LAB1_SIMPLE_NAME;
    private static final String VALID_MODULE_CODE = TypicalModules.CS2030.getModuleCode().toString();
    private static final String VALID_ACAD_YEAR = TypicalModules.CS2030.getAcademicYear().toString();

    private static final List<TaModule> modules = new ArrayList<>(List.of(TypicalModules.getModule(2)));
    private static final List<Student> students = new ArrayList<>(List.of(TypicalStudents.getStudent(0),
            TypicalStudents.getStudent(4), TypicalStudents.getStudent(5)));
    private static final List<JsonAdaptedAttempt> attempts = TypicalAssessments.getAttempts(4).entrySet().stream()
            .map(JsonAdaptedAttempt::new).collect(Collectors.toList());

    @Test
    public void toModelType_validAssessmentDetails_returnsAssessment() throws Exception {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, attempts);

        assertEquals(TypicalAssessments.CS2030_LAB1_WITH_ATTEMPT, assessment.toModelType(modules, students));
    }

    @Test
    public void toModelType_invalidAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                INVALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, attempts);
        String expectedMessage = AssessmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType(modules, students));
    }

    @Test
    public void toModelType_nullAssessmentName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                null, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, attempts);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AssessmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType(modules, students));
    }

    @Test
    public void toModelType_invalidSimpleName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, INVALID_SIMPLE_NAME, attempts);
        String expectedMessage = SimpleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType(modules, students));
    }

    @Test
    public void toModelType_nullSimpleName_throwsIllegalValueException() {
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, null, attempts);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SimpleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> assessment.toModelType(modules, students));
    }

    @Test
    public void toModelType_nonExistentModule_throwsIllegalValueException() {
        List<TaModule> modulesList = new ArrayList<>(Arrays.asList(TypicalModules.CS2105));
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, attempts);
        assertThrows(IllegalValueException.class, () -> assessment.toModelType(modulesList, students));
    }

    @Test
    public void toModelType_nonExistentStudent_throwsIllegalValueException() {
        List<Student> studentsList = new ArrayList<>(Arrays.asList(TypicalStudents.ALICE));
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, attempts);
        assertThrows(IllegalValueException.class, () -> assessment.toModelType(modules, studentsList));
    }

    @Test
    public void toModelType_nonExistentStudentInAttempts_throwsIllegalValueException() {
        List<JsonAdaptedAttempt> invalidAttempts = TypicalAssessments.getAttempts(3).entrySet().stream()
                .map(JsonAdaptedAttempt::new).collect(Collectors.toList());
        JsonAdaptedAssessment assessment = new JsonAdaptedAssessment(
                VALID_ASSESSMENT_NAME, VALID_MODULE_CODE, VALID_ACAD_YEAR, VALID_SIMPLE_NAME, invalidAttempts);
        assertThrows(IllegalValueException.class, () -> assessment.toModelType(modules, students));
    }
}
