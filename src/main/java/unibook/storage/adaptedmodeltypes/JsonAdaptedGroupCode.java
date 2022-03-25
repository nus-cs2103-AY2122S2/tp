package unibook.storage.adaptedmodeltypes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.UniBook;
import unibook.model.module.exceptions.GroupNotFoundException;
import unibook.model.module.exceptions.ModuleNotFoundException;
import unibook.model.module.group.Group;

/**
 * Jackson-friendly group code comprising the module and group name of a group.
 * Used for identifying the groups that a student is in.
 */
public class JsonAdaptedGroupCode {

    public static final String GROUP_MODULE_DOES_NOT_EXIST = "Module with code %s does not exist in UniBook";
    public static final String GROUP_NAME_DOES_NOT_EXIST = "Group with code %s does not exist in UniBook";
    private final JsonAdaptedModuleCode moduleCode;
    private final String groupName;

    /**
     * Instantiates a JsonAdaptedGroupCode class.
     *
     * @param moduleCode module code of the module that contains the group.
     * @param groupName  name of the group.
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
     * Returns the group converted to its model type.
     *
     * @throws IllegalValueException if given group does not exist in UniBook.
     */
    public Group toModelType(UniBook uniBook) throws IllegalValueException {
        try {
            return uniBook.getGroupByModuleCodeAndGroupName(moduleCode.toModelType(), groupName);
        } catch (ModuleNotFoundException m) {
            throw new IllegalValueException(String.format(GROUP_MODULE_DOES_NOT_EXIST, moduleCode.getModuleCode()));
        } catch (GroupNotFoundException g) {
            throw new IllegalValueException(String.format(GROUP_NAME_DOES_NOT_EXIST, groupName));
        }
    }
}
