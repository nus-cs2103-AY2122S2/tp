package unibook.storage.adaptedmodeltypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.ModuleCode;
import unibook.model.tag.Tag;

public class JsonAdaptedModuleCode {
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedModuleCode} with the given {@code moduleCode}.
     */
    @JsonCreator
    public JsonAdaptedModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * Converts a given {@code ModuleCode} into this class for Jackson use.
     */
    public JsonAdaptedModuleCode(ModuleCode source) {
        moduleCode = source.moduleCode;
    }

    @JsonValue
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code ModuleCode} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public ModuleCode toModelType() throws IllegalValueException {
        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new ModuleCode(moduleCode);
    }
}
