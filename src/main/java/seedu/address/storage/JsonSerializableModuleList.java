package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Mod;
import seedu.address.model.person.UniqueModuleList;

/**
 * An Immutable ModuleList that is serializable to JSON format.
 */
@JsonRootName(value = "modulelist")
class JsonSerializableModuleList {

    public static final String MESSAGE_DUPLICATE_TASK = "Module list contains duplicate modules.";

    private final List<JsonAdaptedModule> moduleList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableModuleList} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableModuleList(@JsonProperty("moduleList") List<JsonAdaptedModule> modules) {
        this.moduleList.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyModuleList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableModuleList}.
     */
    public JsonSerializableModuleList(UniqueModuleList source) {
        moduleList.addAll(source.getInternalList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this module list into the model's {@code UniqueModuleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniqueModuleList toModelType() throws IllegalValueException {
        UniqueModuleList uniqueModuleList = new UniqueModuleList();
        for (JsonAdaptedModule jsonAdaptedModule : moduleList) {
            Mod mod = jsonAdaptedModule.toModelType();
            if (uniqueModuleList.contains(mod)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            uniqueModuleList.add(mod);
        }
        return uniqueModuleList;
    }
}

