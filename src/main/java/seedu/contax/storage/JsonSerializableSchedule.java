package seedu.contax.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.contax.commons.core.LogsCenter;
import seedu.contax.commons.exceptions.IllegalValueException;
import seedu.contax.model.ReadOnlySchedule;
import seedu.contax.model.Schedule;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.contax.model.person.Person;

/**
 * An Immutable Schedule that is serializable to JSON format.
 */
@JsonRootName(value = "schedule")
class JsonSerializableSchedule {

    public static final String MESSAGE_OVERLAPPING_APPOINTMENT =
            "Schedule contains overlapping appointments.";

    private static final Logger LOGGER = LogsCenter.getLogger(JsonSerializableSchedule.class);
    private static final String MESSAGE_SKIP_APPOINTMENT = "Appointment cannot be inflated, skipping record";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSchedule} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableSchedule(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {
        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlySchedule} into this class for Jackson use.
     *
     * @param source Future changes to this will not affect the created {@code JsonSerializableSchedule}.
     */
    public JsonSerializableSchedule(ReadOnlySchedule source) {
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this schedule into the model's {@code Schedule} object.
     *
     * @param personsList An up-to-date list of Persons from the address book for looking up Person objects
     *                    associated with Appointments.
     * @throws IllegalValueException If there were any data constraints violated.
     */
    public Schedule toModelType(List<Person> personsList) throws IllegalValueException {
        Schedule schedule = new Schedule();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            try {
                Appointment appointment = jsonAdaptedAppointment.toModelType(personsList);
                schedule.addAppointment(appointment);
            } catch (OverlappingAppointmentException ex) {
                throw new IllegalValueException(MESSAGE_OVERLAPPING_APPOINTMENT);
            } catch (IllegalValueException ex) {
                LOGGER.info(MESSAGE_SKIP_APPOINTMENT);
            }
        }
        return schedule;
    }

}
