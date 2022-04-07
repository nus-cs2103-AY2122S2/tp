package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * Jackson-friendly version of {@link TaModule}.
 */
class JsonAdaptedTaModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "TaModule's %s field is missing!";

    private final String moduleName;
    private final String moduleCode;
    private final String academicYear;
    private final List<String> moduleStudentIds = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTaModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedTaModule(@JsonProperty("moduleName") String moduleName,
                               @JsonProperty("moduleCode") String moduleCode,
                               @JsonProperty("academicYear") String academicYear,
                               @JsonProperty("moduleStudentIds") List<String> studentIds) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.academicYear = academicYear;
        if (!studentIds.isEmpty()) {
            this.moduleStudentIds.addAll(studentIds);
        }
    }

    /**
     * Converts a given {@code TaModule} into this class for Jackson use.
     */
    public JsonAdaptedTaModule(TaModule source) {
        moduleName = source.getModuleName().value;
        moduleCode = source.getModuleCode().value;
        academicYear = source.getAcademicYear().value;
        moduleStudentIds.addAll(source.getStudents().stream()
                .map(student -> student.getStudentId().value)
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

        final ModuleCode modelModuleCode = StorageUtil.checkAndReturnModuleCode(moduleCode,
                MISSING_FIELD_MESSAGE_FORMAT);

        final AcademicYear modelAcademicYear = StorageUtil.checkAndReturnAcademicYear(academicYear,
                MISSING_FIELD_MESSAGE_FORMAT);

        final UniqueStudentList modelStudents = new UniqueStudentList();
        for (String s : moduleStudentIds) {
            Student sObj = StorageUtil.getStudentByStudentId(studentList, s, MISSING_FIELD_MESSAGE_FORMAT);
            modelStudents.add(sObj);
        }

        return new TaModule(modelModuleName, modelModuleCode, modelAcademicYear, modelStudents);
    }
}
