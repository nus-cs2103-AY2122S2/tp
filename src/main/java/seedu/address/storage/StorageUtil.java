package seedu.address.storage;

import java.util.List;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.TaModule;

public class StorageUtil {
    public static final String NONEXISTENT_STUDENT = "Student does not exist!";
    public static final String NONEXISTENT_MODULE = "Module does not exist!";


    /**
     * Returns the {@code Student} object found in {@code studentList} by comparing the student ID.
     * Ensures that the specified {@code studentId} is a valid {@code StudentId} before attempting to find the student
     * in the given student list.
     *
     * @throws IllegalValueException if {@code studentId} is missing or invalid, and if student is not found in the
     * {@code studentList}
     */
    public static Student getStudentByStudentId(List<Student> studentList, String studentId, String errorMsgFormat)
            throws IllegalValueException {
        if (studentId == null) {
            throw new IllegalValueException(
                    String.format(errorMsgFormat, StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);
        final Student modelStudent = studentList.stream()
                .filter(student -> student.isSameStudent(modelStudentId)).findFirst()
                .orElseThrow(() -> new IllegalValueException(NONEXISTENT_STUDENT));
        return modelStudent;
    }

    /**
     * Returns the {@code TaModule} object found in {@code moduleList} by comparing the module code and academic year.
     * Ensures that specified {@code moduleCode} and {@code academicYear} are valid before attempting to find the module
     * in the given module list.
     *
     * @throws IllegalValueException if {@code moduleCode} or {@code academicYear} are missing/invalid, and if module
     * is not found in the {@code moduleList}
     */
    public static TaModule getModuleByCodeAndAcadYear(List<TaModule> moduleList, String moduleCode, String academicYear,
                                                      String errorMsgFormat) throws IllegalValueException {
        final ModuleCode modelModuleCode = StorageUtil.checkAndReturnModuleCode(moduleCode, errorMsgFormat);

        final AcademicYear modelAcademicYear = StorageUtil.checkAndReturnAcademicYear(academicYear, errorMsgFormat);

        final TaModule modelModule = moduleList.stream()
                .filter(module -> module.isSameModule(modelModuleCode, modelAcademicYear)).findFirst()
                .orElseThrow(() -> new IllegalValueException(NONEXISTENT_MODULE));
        return modelModule;
    }

    /**
     * Returns {@code ModuleCode} object if it is valid.
     *
     * @throws IllegalValueException if {@code moduleCode} is missing/invalid.
     */
    public static ModuleCode checkAndReturnModuleCode(String moduleCode, String errorMsgFormat)
        throws IllegalValueException {
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(errorMsgFormat,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }

    /**
     * Returns {@code AcademicYear} object if it is valid.
     *
     * @throws IllegalValueException if {@code academicYear} is missing/invalid.
     */
    public static AcademicYear checkAndReturnAcademicYear(String academicYear, String errorMsgFormat)
            throws IllegalValueException {
        if (academicYear == null) {
            throw new IllegalValueException(String.format(errorMsgFormat,
                    AcademicYear.class.getSimpleName()));
        }
        if (!AcademicYear.isValidAcademicYear(academicYear)) {
            throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
        }

        return new AcademicYear(academicYear);
    }
}
