package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.company.ReadOnlyRoleList;
import seedu.address.model.company.RoleList;
import seedu.address.model.role.Role;

/**
 * An Immutable RoleList that is serializable to JSON format.
 */
@JsonRootName(value = "roleList")
class JsonSerializableRoleList {

    public static final String MESSAGE_DUPLICATE_ROLE = "Role list contains duplicate role(s).";

    private final List<JsonAdaptedRole> roles = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableRoleList} with the given roles.
     */
    @JsonCreator
    public JsonSerializableRoleList(@JsonProperty("roles") List<JsonAdaptedRole> roles) {
        this.roles.addAll(roles);
    }

    /**
     * Converts a given {@code ReadOnlyRoleList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableRoleList}.
     */
    public JsonSerializableRoleList(ReadOnlyRoleList source) {
        roles.addAll(source.getRoleList().stream().map(JsonAdaptedRole::new).collect(Collectors.toList()));
    }

    /**
     * Converts this role list into the company's {@code RoleList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public RoleList toModelType() throws IllegalValueException {
        RoleList roleList = new RoleList();
        for (JsonAdaptedRole jsonAdaptedRole : roles) {
            Role role = jsonAdaptedRole.toModelType();
            if (roleList.hasRole(role)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ROLE);
            }
            roleList.addRole(role);
        }
        return roleList;
    }

}
