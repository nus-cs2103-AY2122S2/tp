package seedu.contax.testutil;

import java.time.LocalDateTime;

import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.Priority;
import seedu.contax.model.appointment.StartDateTime;
import seedu.contax.model.person.Person;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_NAME = "Work Meeting with 2 Bosses";
    public static final LocalDateTime DEFAULT_START_DATETIME = LocalDateTime.parse("2020-10-30T12:34:56");
    public static final int DEFAULT_DURATION = 150; // 2.5 hours

    private Name name;
    private StartDateTime startDateTime;
    private Duration duration;
    private Person person;
    private Priority priority;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        name = new Name(DEFAULT_NAME);
        startDateTime = new StartDateTime(DEFAULT_START_DATETIME);
        duration = new Duration(DEFAULT_DURATION);
        person = null;
        priority = Priority.LOW;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        name = appointmentToCopy.getName();
        startDateTime = appointmentToCopy.getStartDateTimeObject();
        duration = appointmentToCopy.getDuration();
        person = appointmentToCopy.getPerson();
        priority = appointmentToCopy.getPriority();
    }

    /**
     * Sets the {@code Name} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code StartDateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartDateTime(LocalDateTime dateTime) {
        this.startDateTime = new StartDateTime(dateTime);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDuration(int duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPerson(Person person) {
        this.person = person;
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public Appointment build() {
        return new Appointment(name, startDateTime, duration, person);
    }

    public Appointment buildWithPriority() {
        return new Appointment(name, startDateTime, duration, person, priority);
    }
}
