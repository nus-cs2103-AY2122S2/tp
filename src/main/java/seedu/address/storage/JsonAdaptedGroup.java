package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String groupName;
    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName,
                            @JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.groupName = groupName;
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(seedu.address.model.group.Group source) {
        groupName = String.valueOf(source.getGroupName());
        persons.addAll(source.getPersons().stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Group toModelType() throws IllegalValueException {

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupName.class.getSimpleName()));
        }

        if (!GroupName.isValidGroupName(groupName)) {
            throw new IllegalValueException(GroupName.MESSAGE_CONSTRAINTS);
        }
        final GroupName modelGroupName = new GroupName(groupName);

        Group modelGroup = new Group(modelGroupName);

        for (JsonAdaptedPerson person: persons) {
            modelGroup.assignPerson(person.toModelType());
        }
        return modelGroup;
    }
}
