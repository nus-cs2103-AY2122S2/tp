package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * Jackson-friendly version of {@link TaModule}.
 */
class JsonAdaptedTaModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TaModule's %s field is missing!";
    public static final String NONEXISTENT_STUDENT = "Student does not exist!";

    private final String moduleName;
    private final String moduleCode;
    private final String academicYear;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTaModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedTaModule(@JsonProperty("moduleName") String moduleName,
                               @JsonProperty("moduleCode") String moduleCode,
                               @JsonProperty("academicYear") String academicYear,
                               @JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.academicYear = academicYear;
        if (students != null) {
            this.students.addAll(students);
        }
    }

    /**
     * Converts a given {@code TaModule} into this class for Jackson use.
     */
    public JsonAdaptedTaModule(TaModule source) {
        moduleName = source.getModuleName().value;
        moduleCode = source.getModuleCode().value;
        academicYear = source.getAcademicYear().value;
        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /*
     * Converts this Jackson-friendly adapted TAModule object into the model's {@code TaModule} object.
     * Checks that the student tied to the module already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public TaModule toModelType(List<Student> studentList) throws IllegalValueException {
        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleName.class.getSimpleName()));
        }
        if (!ModuleName.isValidModuleName(moduleName)) {
            throw new IllegalValueException(ModuleName.MESSAGE_CONSTRAINTS);
        }
        final ModuleName modelModuleName = new ModuleName(moduleName);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ModuleCode.class.getSimpleName()));
        }
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        if (academicYear == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AcademicYear.class.getSimpleName()));
        }
        if (!AcademicYear.isValidAcademicYear(academicYear)) {
            throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        final AcademicYear modelAcademicYear = new AcademicYear(academicYear);

        final List<Student> modelStudents = new ArrayList<>();
        for (JsonAdaptedStudent s : students) {
            Student sObj = s.toModelType();
            if (!studentList.contains(s)) {
                throw new IllegalValueException(NONEXISTENT_STUDENT);
            }
            modelStudents.add(sObj);
        }

        return new TaModule(modelModuleName, modelModuleCode, modelAcademicYear, modelStudents);
    }
}
