package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTaModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.testutil.TypicalStudents;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_CODE = "A123A";
    private static final String INVALID_MODULE_NAME = "";
    private static final String INVALID_ACADEMIC_YEAR = "21S9";
    private static final List<String> INVALID_STUDENT_IDS = List.of("a0123456", "a12345678");

    private static final String VALID_MODULE_CODE = CS2103T.getModuleCode().toString();
    private static final String VALID_MODULE_NAME = CS2103T.getModuleName().toString();
    private static final String VALID_ACADEMIC_YEAR = CS2103T.getAcademicYear().toString();
    private static final List<Student> VALID_STUDENTS = TypicalStudents.getTypicalStudents();
    private static final List<String> VALID_STUDENT_IDS = VALID_STUDENTS.stream().map(x -> x.getStudentId().toString())
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedTaModule module = new JsonAdaptedTaModule(CS2103T_WITH_STUDENT);
        assertEquals(CS2103T_WITH_STUDENT, module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_validModuleDetailsWithoutStudents_returnsModule() throws Exception {
        JsonAdaptedTaModule student = new JsonAdaptedTaModule(CS2101);
        assertEquals(CS2101, student.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_invalidTaModuleCode_throwsIllegalValueException() {
        JsonAdaptedTaModule module =
                new JsonAdaptedTaModule(VALID_MODULE_NAME, INVALID_MODULE_CODE, VALID_ACADEMIC_YEAR, VALID_STUDENT_IDS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_nullTaModuleCode_throwsIllegalValueException() {
        JsonAdaptedTaModule module = new JsonAdaptedTaModule(VALID_MODULE_NAME,
                null, VALID_ACADEMIC_YEAR, new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedTaModule module =
                new JsonAdaptedTaModule(INVALID_MODULE_NAME, VALID_MODULE_CODE, VALID_ACADEMIC_YEAR, VALID_STUDENT_IDS);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_nullModuleName_throwsIllegalValueException() {
        JsonAdaptedTaModule module = new JsonAdaptedTaModule(null, VALID_MODULE_CODE,
                VALID_ACADEMIC_YEAR, VALID_STUDENT_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_invalidAcademicYear_throwsIllegalValueException() {
        JsonAdaptedTaModule module =
                new JsonAdaptedTaModule(VALID_MODULE_NAME, VALID_MODULE_CODE, INVALID_ACADEMIC_YEAR, VALID_STUDENT_IDS);
        String expectedMessage = AcademicYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_nullAcademicYear_throwsIllegalValueException() {
        JsonAdaptedTaModule module =
                new JsonAdaptedTaModule(VALID_MODULE_NAME, VALID_MODULE_CODE, null, VALID_STUDENT_IDS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AcademicYear.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

    @Test
    public void toModelType_invalidStudentIds_throwsIllegalValueException() {
        JsonAdaptedTaModule module =
                new JsonAdaptedTaModule(VALID_MODULE_NAME, VALID_MODULE_CODE, VALID_ACADEMIC_YEAR, INVALID_STUDENT_IDS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> module.toModelType(VALID_STUDENTS));
    }

}
