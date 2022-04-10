package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME_AMELIA;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalPersons.ALICE;
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
                new Appointment(reference.getName(), reference.getStartDateTimeObject(), null, null));
        assertThrows(NullPointerException.class, () ->
                new Appointment(reference.getName(), null, reference.getDuration(), null));
        assertThrows(NullPointerException.class, () ->
                new Appointment(null, reference.getStartDateTimeObject(), reference.getDuration(), null));
    }

    @Test
    public void constructor_nullPerson_success() {
        Appointment reference = new AppointmentBuilder().build();
        Appointment p = new Appointment(reference.getName(), reference.getStartDateTimeObject(),
                reference.getDuration(), null);
        assertNull(p.getPerson());
    }

    @Test
    public void equals() {
        Appointment aliceCopy = new AppointmentBuilder(APPOINTMENT_ALICE).build();

        // same object -> returns true
        assertTrue(APPOINTMENT_ALICE.equals(APPOINTMENT_ALICE));

        // same values -> returns true
        assertTrue(APPOINTMENT_ALICE.equals(aliceCopy));

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

        // different priority -> returns false
        Appointment aliceApptHigh = APPOINTMENT_ALICE.withPriority(Priority.HIGH);
        Appointment aliceApptLow = APPOINTMENT_ALICE.withPriority(Priority.LOW);
        assertFalse(APPOINTMENT_ALICE.equals(aliceApptHigh));
        assertFalse(aliceApptHigh.equals(aliceApptLow));
    }

    @Test
    public void getEndDateTime() {
        Appointment appointment1 = new AppointmentBuilder()
                .withDuration(30)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T12:34:44")).build();
        Appointment appointment2 = new AppointmentBuilder()
                .withDuration(1)
                .withStartDateTime(LocalDateTime.parse("2020-04-23T23:59:22")).build();

        assertEquals(LocalDateTime.parse("2020-04-23T13:04:00"), appointment1.getEndDateTime());
        assertEquals(LocalDateTime.parse("2020-04-24T00:00:00"), appointment2.getEndDateTime());
    }

    @Test
    public void isOverlapping() {
        // This is an integration test, more detailed tests are done in ScheduleItemTest
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

        assertTrue(appointment1.isOverlapping(appointment1)); // Will overlap with itself
        assertTrue(appointment1.isOverlapping(appointment2)); // Will overlap since seconds are zeroed
        assertTrue(appointment1.isOverlapping(appointment3)); // 3 starts before 1 ends
        assertTrue(appointment1.isOverlapping(appointment4)); // 1 starts before 4 ends
        assertTrue(appointment1.isOverlapping(appointment5)); // 5 is contained in 1
        assertTrue(appointment1.isOverlapping(appointment6)); // 1 is contained in 6
    }

    @Test
    public void withPerson() {
        Appointment refAppointment = new AppointmentBuilder().withDuration(30).build();
        Appointment noPersonAppointment = new AppointmentBuilder().withDuration(30).withPerson(null).build();
        Appointment bobAppointment = new AppointmentBuilder().withDuration(30).withPerson(BOB).build();

        assertEquals(noPersonAppointment, refAppointment.withPerson(null));
        assertEquals(bobAppointment, refAppointment.withPerson(BOB));
    }

    @Test
    public void withPriority() {
        Appointment appointmentHigh = new AppointmentBuilder(APPOINTMENT_ALICE).withPriority(Priority.HIGH).build();
        Appointment appointmentLow = new AppointmentBuilder(APPOINTMENT_ALONE).withPriority(Priority.LOW).build();

        assertEquals(appointmentHigh, APPOINTMENT_ALICE.withPriority(Priority.HIGH));
        assertEquals(appointmentLow, APPOINTMENT_ALONE.withPriority(Priority.LOW));
    }

    @Test
    public void toStringTest() {
        LocalDateTime startDate = LocalDateTime.parse("2022-02-11T12:30:00");
        Appointment appointment = new AppointmentBuilder().withName("Test Meeting")
                .withStartDateTime(startDate).withDuration(20).withPerson(ALICE).build();

        assertEquals("**Name:** "
                        + appointment.getName()
                        + "\n **Start Date Time:** "
                        + appointment.getStartDateTimeObject()
                        + "\n **Duration:** "
                        + appointment.getDuration()
                        + "\n **Person:** Alice Pauline"
                        + "\n **Priority:** None",
                appointment.toString());
    }

    @Test
    public void comparisonTest() {
        Appointment refAppointment = new AppointmentBuilder()
                .withStartDateTime(LocalDateTime.parse("2020-10-30T12:34:00")).build();
        Appointment appointmentBefore = new AppointmentBuilder()
                .withStartDateTime(LocalDateTime.parse("2020-10-30T12:33:00")).build();
        Appointment appointmentAfter = new AppointmentBuilder()
                .withStartDateTime(LocalDateTime.parse("2020-10-30T12:35:00")).build();
        Appointment appointmentDifferentSeconds = new AppointmentBuilder()
                .withStartDateTime(LocalDateTime.parse("2020-10-30T12:34:54")).build();

        assertEquals(0, refAppointment.compareTo(refAppointment));
        assertEquals(0, refAppointment.compareTo(appointmentDifferentSeconds));
        assertTrue(refAppointment.compareTo(appointmentBefore) > 0);
        assertTrue(refAppointment.compareTo(appointmentAfter) < 0);
    }

    @Test
    public void hashCodeTest() {
        Appointment appointment = new AppointmentBuilder().withName("Test Meeting")
                .withStartDateTime(LocalDateTime.parse("2022-02-11T12:30:00"))
                .withDuration(20)
                .withPerson(ALICE)
                .withPriority(Priority.HIGH)
                .build();

        Appointment copyAppointment = new AppointmentBuilder(appointment).build();
        Appointment diffAppointment = new AppointmentBuilder(appointment).withName("Another").build();

        assertEquals(appointment.hashCode(), copyAppointment.hashCode());
        assertNotEquals(appointment.hashCode(), diffAppointment.hashCode());
    }
}
