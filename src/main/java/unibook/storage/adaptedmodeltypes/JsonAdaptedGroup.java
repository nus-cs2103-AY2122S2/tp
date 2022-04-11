package unibook.storage.adaptedmodeltypes;

import static unibook.model.module.group.Group.NAME_CONSTRAINT_MESSAGE;
import static unibook.model.module.group.Group.isValidName;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unibook.commons.exceptions.IllegalValueException;
import unibook.model.module.Module;
import unibook.model.module.exceptions.DuplicateMeetingTimeException;
import unibook.model.module.group.Group;
import unibook.model.person.Name;
import unibook.model.person.Phone;

/**
 * Jackson-friendly version of Group.
 */
public class JsonAdaptedGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";
    public static final String DUPLICATE_KEY_EVENT_MESSAGE_FORMAT = "Duplicate key event on %s found in group %s";

    private final String groupName;
    private final Set<LocalDateTime> meetingTimes;

    /**
     * Creates a JsonAdaptedGroup object using json properties.
     *
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
     *
     * @param source group object to use to create the {@code JsonAdaptedGroup}
     */
    public JsonAdaptedGroup(Group source) {
        this.groupName = source.getGroupName();
        this.meetingTimes = new HashSet<>(source.getMeetingTimes());
    }

    /**
     *  Returns group name.
     */
    public String getGroupName() {
        return this.groupName;
    }


    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
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
        if (!isValidName(groupName)) {
            throw new IllegalValueException(NAME_CONSTRAINT_MESSAGE);
        }

        Group group = new Group(groupName, module);

        for (LocalDateTime meetingTime : meetingTimes) {
            try {
                group.addMeetingTime(meetingTime);
            } catch (DuplicateMeetingTimeException e) {
                throw new IllegalValueException(String.format(DUPLICATE_KEY_EVENT_MESSAGE_FORMAT,
                        meetingTime.toString(), group.getGroupName()));
            }
        }

        return group;
    }

}
