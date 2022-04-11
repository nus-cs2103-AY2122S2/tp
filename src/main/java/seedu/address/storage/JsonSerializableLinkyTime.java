package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LinkyTime;
import seedu.address.model.ReadOnlyLinkyTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;

/**
 * An immutable LinkyTime that is serializable to JSON format.
 */
@JsonRootName(value = "linkytime")
class JsonSerializableLinkyTime {
    public static final String MESSAGE_DUPLICATE_MEETING = "Meetings list contains duplicate meeting(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Modules list contains duplicate module(s).";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Meeting assigned to module that does not exist.";

    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableLinkyTime} with the given meetings.
     */
    @JsonCreator
    public JsonSerializableLinkyTime(@JsonProperty("meetings") List<JsonAdaptedMeeting> meetings,
                                     @JsonProperty("modules") List<JsonAdaptedModule> modules) {
        this.meetings.addAll(meetings);
        this.modules.addAll(modules);
    }

    /**
     * Converts a given {@code ReadOnlyLinkyTime} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableLinkyTime}.
     */
    public JsonSerializableLinkyTime(ReadOnlyLinkyTime source) {
        meetings.addAll(
                source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
        modules.addAll(
                source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
    }

    /**
     * Converts this LinkyTime into the model's {@code LinkyTime} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LinkyTime toModelType() throws IllegalValueException {
        final LinkyTime linkyTime = new LinkyTime();
        final HashSet<Module> moduleHashSet = new HashSet<>();

        for (final JsonAdaptedModule jsonAdaptedModule : modules) {
            final Module module = jsonAdaptedModule.toModelType();
            if (linkyTime.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            linkyTime.addModule(module);
            moduleHashSet.add(module);
        }

        for (final JsonAdaptedMeeting jsonAdaptedMeeting : meetings) {
            final Meeting meeting = jsonAdaptedMeeting.toModelType();
            if (linkyTime.hasMeeting(meeting)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MEETING);
            }
            if (!moduleHashSet.contains(meeting.getModule())) {
                throw new IllegalValueException(MESSAGE_MODULE_NOT_FOUND);
            }
            linkyTime.addMeeting(meeting);
        }

        return linkyTime;
    }
}
