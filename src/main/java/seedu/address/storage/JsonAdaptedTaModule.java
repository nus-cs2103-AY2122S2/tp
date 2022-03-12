package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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

    /**
     * Constructs a {@code JsonAdaptedTaModule} with the given module details.
     */
    @JsonCreator
    public JsonAdaptedTaModule(@JsonProperty("moduleName") String moduleName,
                               @JsonProperty("moduleCode") String moduleCode,
                               @JsonProperty("email") String academicYear) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.academicYear = academicYear;
    }

    /**
     * Converts a given {@code TaModule} into this class for Jackson use.
     */
    public JsonAdaptedTaModule(TaModule source) {
        moduleName = source.getModuleName().value;
        moduleCode = source.getModuleCode().value;
        academicYear = source.getAcademicYear().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code TaModule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public TaModule toModelType() throws IllegalValueException {
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

        return new TaModule(modelModuleName, modelModuleCode, modelAcademicYear);
    }

}
