package seedu.address.storage.meeting;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Id;

/**
 * Jackson-friendly version of {@link Id}.
 */
public class JsonAdaptedAttendee {

    private final String attendeeId;

    /**
     * Constructs a {@code JsonAdaptedAttendee} with the given {@code attendeeId}.
     */
    @JsonCreator
    public JsonAdaptedAttendee(UUID attendeeId) {
        this.attendeeId = attendeeId.toString();
    }

    /**
     * Converts a given {@code Id} into this class for Jackson use.
     */
    public JsonAdaptedAttendee(Id source) {
        this.attendeeId = source.id.toString();
    }

    @JsonValue
    public String getAttendeeId() {
        return attendeeId;
    }

    /**
     * Converts this Jackson-friendly adapted attendee object into the model's {@code Id} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendee.
     */
    public Id toModelType() throws IllegalValueException {
        if (!Id.isValidId(attendeeId)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        return new Id(attendeeId);
    }

    @Override
    public String toString() {
        return "JsonAdaptedAttendee: " + attendeeId;
    }
}
