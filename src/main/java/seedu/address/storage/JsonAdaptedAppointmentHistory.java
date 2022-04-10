package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.history.AppointmentHistory;

/**
 * Jackson-friendly version of {@link AppointmentHistory}.
 */
public class JsonAdaptedAppointmentHistory {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    private final String description;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment history details.
     */
    @JsonCreator
    public JsonAdaptedAppointmentHistory(@JsonProperty("description") String description,
                                  @JsonProperty("dateTime") String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Appointment History} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentHistory(Appointment source) {
        description = source.getDescription().toString();
        dateTime = source.getDateTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment history object
     * into the model's {@code Appointment History } object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment history.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        final Description modelDescription = new Description(description);

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateTime.class.getSimpleName()));
        }

        if (DateTime.validateDateTime(dateTime) == null) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        return new Appointment(modelDescription, modelDateTime);
    }
}
