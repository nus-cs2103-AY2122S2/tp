package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_AMELIA;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalPersons.BOB;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.contax.testutil.AppointmentBuilder;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Appointment reference = new AppointmentBuilder().build();

        assertThrows(NullPointerException.class, () ->
                new Appointment(null, null, null, null));
        assertThrows(NullPointerException.class, () ->
                new Appointment(reference.getName(), reference.getStartDateTime(), null, null));
        assertThrows(NullPointerException.class, () ->
                new Appointment(reference.getName(), null, reference.getDuration(), null));
        assertThrows(NullPointerException.class, () ->
                new Appointment(null, reference.getStartDateTime(), reference.getDuration(), null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Appointment aliceCopy = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        assertTrue(APPOINTMENT_ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(APPOINTMENT_ALICE.equals(APPOINTMENT_ALICE));

        // null -> returns false
        assertFalse(APPOINTMENT_ALICE.equals(null));

        // different type -> returns false
        assertFalse(APPOINTMENT_ALICE.equals(5));

        // different appointment -> returns false
        assertFalse(APPOINTMENT_ALICE.equals(APPOINTMENT_ALONE));

        // different name -> returns false
        Appointment editedAliceAppt = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withName(VALID_APPOINTMENT_NAME_AMELIA).build();
        assertFalse(APPOINTMENT_ALICE.equals(editedAliceAppt));

        // different startDateTime -> returns false
        editedAliceAppt = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:34:44")).build();
        assertFalse(APPOINTMENT_ALICE.equals(editedAliceAppt));

        // different duration -> returns false
        editedAliceAppt = new AppointmentBuilder(APPOINTMENT_ALICE).withDuration(123).build();
        assertFalse(APPOINTMENT_ALICE.equals(editedAliceAppt));

        // different person -> returns false
        editedAliceAppt = new AppointmentBuilder(APPOINTMENT_ALICE).withPerson(BOB).build();
        assertFalse(APPOINTMENT_ALICE.equals(editedAliceAppt));
    }
}
