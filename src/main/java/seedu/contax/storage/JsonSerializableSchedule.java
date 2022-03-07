package seedu.contax.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.Schedule;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;

/**
 * An Immutable Schedule that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
class JsonSerializableSchedule {

    public static final String MESSAGE_OVERLAPPING_APPOINTMENT =
            "Schedule contains overlapping appointments.";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlySchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSchedule}.
     */
    public JsonSerializableSchedule(ReadOnlySchedule source) {
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this schedule into the model's {@code Schedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Schedule toModelType(AddressBook addressBook) throws IllegalValueException {
        Schedule schedule = new Schedule();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType(addressBook);
            try {
                schedule.addAppointment(appointment);
            } catch (OverlappingAppointmentException ex) {
                throw new IllegalValueException(MESSAGE_OVERLAPPING_APPOINTMENT);
            }
        }
        return schedule;
    }

}
