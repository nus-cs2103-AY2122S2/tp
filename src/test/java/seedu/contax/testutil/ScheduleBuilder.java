package seedu.contax.testutil;

import seedu.contax.model.Schedule;
import seedu.contax.model.appointment.Appointment;

/**
 * A utility class to help with building Schedule objects.
 * Example usage: <br>
 *     {@code Schedule ab = new ScheduleBuilder().withAppointment(new Appointment(...)).build();}
 */
public class ScheduleBuilder {

    private final Schedule schedule;

    public ScheduleBuilder() {
        schedule = new Schedule();
    }

    public ScheduleBuilder(Schedule schedule) {
        this.schedule = schedule;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public ScheduleBuilder withAppointment(Appointment appointment) {
        schedule.addAppointment(appointment);
        return this;
    }

    public Schedule build() {
        return schedule;
    }
}
