package unibook.storage.adaptedmodeltypes;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.Module;
import unibook.model.module.ModuleCode;
import unibook.model.module.ModuleName;


/**
 * Jackson-friendly version of {@link Module}.
 */
public class JsonAdaptedModule {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Modules's %s field is missing!";

    private final String moduleName;
    private final String moduleCode;
    private final Set<JsonAdaptedGroup> groups;

    /**
     * Creates a JsonAdaptedModule object using json properties.
     *
     * @param moduleName
     * @param moduleCode
     * @param groups
     */
    @JsonCreator
    public JsonAdaptedModule(@JsonProperty("moduleName") String moduleName,
                             @JsonProperty("moduleCode") String moduleCode,
                             @JsonProperty("groups") Set<JsonAdaptedGroup> groups) {
        this.moduleName = moduleName;
        this.moduleCode = moduleCode;
        this.groups = groups;
    }

    /**
     * Creates a JsonAdaptedModule object using module object.
     *
     * @param source
     */
    public JsonAdaptedModule(Module source) {
        this.moduleName = source.getModuleName().toString();
        this.moduleCode = source.getModuleCode().toString();
        this.groups = new HashSet<>(source.getGroups().stream()
            .map(grp -> new JsonAdaptedGroup(grp.getGroupName(), new HashSet<>(grp.getMeetingTimes())))
            .collect(Collectors.toSet()));
    }


    /**
     * Converts this Jackson-friendly adapted module object into the model's {@code Module} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted module.
     */
    public Module toModelType() throws IllegalValueException {

        if (moduleName == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName()));
        }
        if (moduleCode == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }
        if (groups == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Groups"));
        }

        Module module = new Module(new ModuleName(moduleName), new ModuleCode(moduleCode));
        for (JsonAdaptedGroup jsonGroup : groups) {
            module.getGroups().add(jsonGroup.toModelType(module));
        }
        return module;
    }

}
