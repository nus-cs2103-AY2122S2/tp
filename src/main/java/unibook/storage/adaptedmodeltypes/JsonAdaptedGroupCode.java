package unibook.storage.adaptedmodeltypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jackson-friendly group code comprising the module and group name of a group.
 * Used for identifying the groups that a student is in.
 */
public class JsonAdaptedGroupCode {

    private final JsonAdaptedModuleCode moduleCode;
    private final String groupName;

    /**
     * Instantiates a JsonAdaptedGroupCode class.
     * @param moduleCode module code of the module that contains the group.
     * @param groupName name of the group.
     */
    @JsonCreator
    public JsonAdaptedGroupCode(@JsonProperty("moduleCode") JsonAdaptedModuleCode moduleCode,
                                @JsonProperty("groupName") String groupName) {
        this.moduleCode = moduleCode;
        this.groupName = groupName;
    }

    /**
     * Returns the json adapted module code of the module group is associated with.
     *
     * @return json adapted module code.
     */
    public JsonAdaptedModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns the name of the group.
     *
     * @return name of the group.
     */
    public String getGroupName() {
        return groupName;
    }
}
