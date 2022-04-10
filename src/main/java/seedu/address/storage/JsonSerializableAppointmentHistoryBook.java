package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.appointment.Appointment;

/**
 * An Immutable AppointmentHistoryBook that is serializable to JSON format.
 */
@JsonRootName(value = "appointmenthistorybook")
public class JsonSerializableAppointmentHistoryBook {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "History list contains duplicate history(s).";
    private final List<JsonAdaptedAppointmentHistory> history = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentHistoryBook} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentHistoryBook(@JsonProperty("history")
                                                          List<JsonAdaptedAppointmentHistory> history) {
        this.history.addAll(history);
    }

    /**
     * Converts this appointment history book into the model's {@code AppointmentHistoryBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentBook toModelType() throws IllegalValueException {
        AppointmentBook appointmentBook = new AppointmentBook();
        for (JsonAdaptedAppointmentHistory jsonAdaptedAppointment : history) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentBook.addAppointment(appointment);
        }
        return appointmentBook;
    }

}
