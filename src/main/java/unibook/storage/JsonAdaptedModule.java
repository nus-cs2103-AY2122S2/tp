package unibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;
import unibook.model.person.Name;
import unibook.model.person.Phone;



/**
 * Jackson-friendly version of {@link Module}.
 */
class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String moduleName;
    private final String moduleCode;
    //todo: add support for list of students/professors in module


    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleName") String moduleName,
                             @JsonProperty("moduleCode") String moduleCode) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
    }

    @JsonCreator
    public JsonAdaptedModule(Module source) {
        this.moduleName = source.getModuleName().toString();
        this.moduleCode = source.getModuleCode().toString();
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Module toModelType() throws IllegalValueException {

        if (moduleName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (moduleCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        //Todo add support for existing list and student/professor list functionality
        return new Module(new ModuleName(moduleName), new ModuleCode(moduleCode));
    }

}
