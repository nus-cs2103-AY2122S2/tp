package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * Jackson-friendly version of {@link Meeting}.
 */
public class JsonAdaptedMeeting {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Meeting's %s field is missing!";

    private final String startDateTime;
    private final String endDateTime;
    private final JsonAdaptedClient client;
    private final String label;

    /**
     * Constructs a {@code JsonAdaptedMeeting} with the given meeting details.
     */
    @JsonCreator
    JsonAdaptedMeeting(@JsonProperty("startDateTime") String startDateTime,
                       @JsonProperty("endDateTime") String endDateTime,
                       @JsonProperty("client") JsonAdaptedClient client,
                       @JsonProperty("label") String label) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.client = client;
        this.label = label;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedMeeting(Meeting meeting) {
        startDateTime = meeting.getStartDateTime().toString();
        endDateTime = meeting.getEndDateTime().toString();
        client = new JsonAdaptedClient(meeting.getClient());
        label = meeting.getLabel();
    }

    /**
     * Converts this Jackson-friendly adapted Meeting object into the model's {@code Meeting} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted meeting.
     */
    public Meeting toModelType() throws IllegalValueException {
        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        LocalDateTime modelStartDateTime;
        try {
            modelStartDateTime = LocalDateTime.parse(startDateTime);
        } catch (DateTimeParseException err) {
            throw new IllegalValueException(Meeting.DATETIME_MESSAGE_CONSTRAINTS);
        }

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        LocalDateTime modelEndDateTime;
        try {
            modelEndDateTime = LocalDateTime.parse(endDateTime);
        } catch (DateTimeParseException err) {
            throw new IllegalValueException(Meeting.DATETIME_MESSAGE_CONSTRAINTS);
        }

        if (client == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Client.class.getSimpleName()));
        }
        final Client modelClient = client.toModelType();

        return new Meeting(modelStartDateTime, modelEndDateTime, modelClient, label);
    }

}
