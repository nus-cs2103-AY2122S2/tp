package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;
import seedu.address.model.person.Name;

/**
 * Jackson-friendly version of {@link Event}.
 */
public class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Event's %s field is missing!";

    private final String eventName;
    private final String info;
    private final Set<JsonAdaptedName> participants = new HashSet<>();
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("name") String eventName,
                            @JsonProperty("info") String info,
                            @JsonProperty("participants") List<JsonAdaptedName> participants,
                            @JsonProperty("dateTime") String dateTime) {
        this.eventName = eventName;
        this.info = info;
        this.participants.addAll(participants);
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        this.eventName = source.getEventName().value;
        this.info = source.getEventInfo().value;
        this.participants.addAll(new HashSet<>(source.getParticipants()).stream()
                .map(JsonAdaptedName::new)
                .collect(Collectors.toList()));
        this.dateTime = source.getDateTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    public Event toModelType() throws IllegalValueException {
        Set<Name> modelParticipants = new HashSet<>();

        if (eventName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    EventName.class.getSimpleName()));
        }
        if (!EventName.isValidEventName(eventName)) {
            throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
        }
        final EventName modelEventName = new EventName(eventName);

        if (info == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Information.class.getSimpleName()));
        }
        if (!Information.isValidInformation(info)) {
            throw new IllegalValueException(Information.MESSAGE_CONSTRAINTS);
        }
        final Information modelInfo = new Information(info);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }
        int[] dateTimeArr = convertDateTime(dateTime);
        final DateTime modelDateTime = new DateTime(dateTimeArr[0], dateTimeArr[1], dateTimeArr[2], dateTimeArr[3],
                dateTimeArr[4]);

        for (JsonAdaptedName curr : participants) {
            if (curr == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        EventName.class.getSimpleName()));
            }
            if (!Name.isValidName(curr.getName())) {
                throw new IllegalValueException(EventName.MESSAGE_CONSTRAINTS);
            }
            modelParticipants.add(new Name(curr.getName()));
        }
        return new Event(modelEventName, modelInfo, new ArrayList<>(modelParticipants), modelDateTime);
    }

    /**
     * Converts the date time into an int array that splits into year, month, day, hour and min.
     * @param dateTime the string input of the date and time
     * @return int array that contains year, month, day, hour and min
     * @throws IllegalValueException if date or time is invalid
     */
    public int[] convertDateTime(String dateTime) throws IllegalValueException {
        String[] tempDateTime = dateTime.split(" ");
        String date = tempDateTime[0];
        String time = tempDateTime[1];

        if (!DateTime.isValidDate(date)) {
            throw new IllegalValueException(DateTime.DATE_MESSAGE_CONSTRAINTS);
        }
        if (!DateTime.isValidTime(time)) {
            throw new IllegalValueException(DateTime.TIME_MESSAGE_CONSTRAINTS);
        }
        String[] validDate = date.split("-");
        String[] validTime = time.split(":");
        int[] outputDateTime = new int[5];
        outputDateTime[0] = Integer.parseInt(validDate[0]);
        outputDateTime[1] = Integer.parseInt(validDate[1]);
        outputDateTime[2] = Integer.parseInt(validDate[2]);
        outputDateTime[3] = Integer.parseInt(validTime[0]);
        outputDateTime[4] = Integer.parseInt(validTime[1]);

        return outputDateTime;
    }
}
