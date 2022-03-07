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

    @Test
    public void getEndDateTime() {
        Appointment appointment1 = new AppointmentBuilder()
                .withDuration(30)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:34:44")).build();
        Appointment appointment2 = new AppointmentBuilder()
                .withDuration(1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T23:59:22")).build();

        appointment1.getEndDateTime().equals(LocalDateTime.parse("2020-04-23T13:04:00"));
        appointment2.getEndDateTime().equals(LocalDateTime.parse("2020-04-24T00:00:00"));
    }

    @Test
    public void isOverlapping() {
        Appointment appointment1 = new AppointmentBuilder()
                .withDuration(30)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:34:00")).build();
        Appointment appointment2 = new AppointmentBuilder(appointment1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:34:30")).build();
        Appointment appointment3 = new AppointmentBuilder(appointment1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T13:03:00")).build();
        Appointment appointment4 = new AppointmentBuilder(appointment1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:10:00")).build();
        Appointment appointment5 = new AppointmentBuilder(appointment1)
                .withDuration(15)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:40:00")).build();
        Appointment appointment6 = new AppointmentBuilder(appointment1)
                .withDuration(60)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:10:00")).build();

        // Bounds Testing
        Appointment appointment7 = new AppointmentBuilder(appointment1).withDuration(60).build();
        Appointment appointment8 = new AppointmentBuilder(appointment1).withDuration(15).build();

        Appointment appointment9 = new AppointmentBuilder(appointment1)
                .withDuration(20)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:44:00")).build();
        Appointment appointment10 = new AppointmentBuilder(appointment1)
                .withDuration(40)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:24:00")).build();

        Appointment appointment11 = new AppointmentBuilder(appointment1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T13:04:00")).build();
        Appointment appointment12 = new AppointmentBuilder(appointment1)
                .withDuration(1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:33:00")).build();

        assertTrue(appointment1.isOverlapping(appointment1)); // Will overlap with itself
        assertTrue(appointment1.isOverlapping(appointment2)); // Will overlap since seconds are zeroed
        assertTrue(appointment2.isOverlapping(appointment1)); // Symmetric test
        assertTrue(appointment1.isOverlapping(appointment3)); // 3 starts before 1 ends
        assertTrue(appointment3.isOverlapping(appointment1)); // Symmetric test
        assertTrue(appointment1.isOverlapping(appointment4)); // 1 starts before 4 ends
        assertTrue(appointment4.isOverlapping(appointment1)); // Symmetric test
        assertTrue(appointment1.isOverlapping(appointment5)); // 5 is contained in 1
        assertTrue(appointment5.isOverlapping(appointment1)); // Symmetric test
        assertTrue(appointment1.isOverlapping(appointment6)); // 1 is contained in 6
        assertTrue(appointment6.isOverlapping(appointment1)); // Symmetric test

        /* Testing Overlapping Bounds */
        // 1 and 7 start at the same time, 1 is contained in 7
        assertTrue(appointment1.isOverlapping(appointment7));
        assertTrue(appointment7.isOverlapping(appointment1)); // Symmetric test

        // 1 and 8 start at the same time, 8 is contained in 1
        assertTrue(appointment1.isOverlapping(appointment8));
        assertTrue(appointment8.isOverlapping(appointment1)); // Symmetric test

        // 1 and 9 end at the same time, 9 is contained in 1
        assertTrue(appointment1.isOverlapping(appointment9));
        assertTrue(appointment9.isOverlapping(appointment1)); // Symmetric test

        // 1 and 10 end at the same time, 1 is contained in 10
        assertTrue(appointment1.isOverlapping(appointment10));
        assertTrue(appointment10.isOverlapping(appointment1)); // Symmetric test

        assertFalse(appointment1.isOverlapping(appointment11));
        assertFalse(appointment11.isOverlapping(appointment1));
        assertFalse(appointment1.isOverlapping(appointment12));
        assertFalse(appointment12.isOverlapping(appointment1));
    }
}
