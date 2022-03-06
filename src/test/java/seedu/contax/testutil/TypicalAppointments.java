package seedu.contax.testutil;

import static seedu.contax.testutil.TypicalPersons.ALICE;

import java.time.LocalDateTime;

import seedu.contax.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT_ALICE = new AppointmentBuilder()
            .withName("First meeting with alice")
            .withStartDateTime(LocalDateTime.parse("2022-11-20T10:25:00"))
            .withDuration(30)
            .withPerson(ALICE).build();

    public static final Appointment APPOINTMENT_ALONE = new AppointmentBuilder()
            .withName("Do some work alone")
            .withStartDateTime(LocalDateTime.parse("2022-10-07T22:50:00"))
            .withDuration(60).build();
}
