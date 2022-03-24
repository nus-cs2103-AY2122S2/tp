package unibook.storage.adaptedmodeltypes;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javafx.collections.FXCollections;
import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.Module;
import unibook.model.module.group.Group;
import unibook.model.person.Name;
import unibook.model.person.Phone;
import unibook.storage.util.LocalDateTimeDeserializer;
import unibook.storage.util.LocalDateTimeSerializer;

/**
 * Jackson-friendly version of Group.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String groupName;
    @JsonSerialize(contentUsing = LocalDateTimeSerializer.class)
    @JsonDeserialize(contentUsing = LocalDateTimeDeserializer.class)
    private final Set<LocalDateTime> meetingTimes;

    /**
     * Creates a JsonAdaptedGroup object using json properties.
     * @param groupName
     * @param meetingTimes
     */
    @JsonCreator
    public JsonAdaptedGroup(@JsonProperty("groupName") String groupName,
                            @JsonProperty("meetingTimes") Set<LocalDateTime> meetingTimes) {
        this.groupName = groupName;
        this.meetingTimes = meetingTimes;
    }

    /**
     * Creates a JsonAdaptedGroup object using group object.
     * @param source
     */
    public JsonAdaptedGroup(Group source) {
        this.groupName = source.getGroupName();
        this.meetingTimes = new HashSet<>(source.getMeetingTimes());
    }


    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @param module that this group is inside.
     * @throws IllegalValueException if there were any data constraints violated in the adapted Group.
     */
    public Group toModelType(Module module) throws IllegalValueException {

        if (groupName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (meetingTimes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }

        return new Group(groupName, module, FXCollections.observableArrayList(meetingTimes));
    }

}
