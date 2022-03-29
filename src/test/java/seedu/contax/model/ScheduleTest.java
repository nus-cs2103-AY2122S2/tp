package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.contax.model.chrono.TimeRange;
import seedu.contax.testutil.AppointmentBuilder;
import seedu.contax.testutil.ScheduleBuilder;

public class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), schedule.getAppointmentList());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule(null));
    }

    @Test
    public void constructor_validScheduleInput_throwsNullPointerException() {
        Schedule schedule = getTypicalSchedule();
        Schedule createdSchedule = new Schedule(schedule);

        assertEquals(schedule, createdSchedule);
    }

    @Test
    public void setAppointments_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Schedule().setAppointments(null));
    }

    @Test
    public void setAppointments_emptyList_returnsEmptySchedule() {
        Schedule createdSchedule = new Schedule();
        createdSchedule.setAppointments(List.of());

        assertEquals(new Schedule(), createdSchedule);
    }

    @Test
    public void setAppointments_validList_returnsSchedule() {
        Schedule createdSchedule = new Schedule();
        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(APPOINTMENT_ALICE)
                .withAppointment(APPOINTMENT_ALONE).build();

        createdSchedule.setAppointments(List.of(APPOINTMENT_ALICE, APPOINTMENT_ALONE));
        assertEquals(expectedSchedule, createdSchedule);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlySchedule_replacesData() {
        Schedule newData = getTypicalSchedule();
        schedule.resetData(newData);
        assertEquals(newData, schedule);
    }

    @Test
    public void resetData_withOverlappingAppointments_throwsOverlappingAppointmentException() {
        // Construct two appointments that have overlapping periods
        Appointment overlappingAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTimeObject().value.plusMinutes(1))
                .build();
        List<Appointment> newAppointments = Arrays.asList(APPOINTMENT_ALICE, overlappingAppointment);
        ScheduleStub newData = new ScheduleStub(newAppointments);

        assertThrows(OverlappingAppointmentException.class, () -> schedule.resetData(newData));
    }

    @Test
    public void hasAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.hasAppointment(null));
    }

    @Test
    public void hasAppointment_appointmentNotInSchedule_returnsFalse() {
        assertFalse(schedule.hasAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void hasAppointment_appointmentInSchedule_returnsTrue() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        assertTrue(schedule.hasAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void hasAppointment_duplicatedAppointment_returnsTrue() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        Appointment clonedAlice = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        assertTrue(schedule.hasAppointment(clonedAlice));
    }

    @Test
    public void hasOverlappingAppointment_duplicatedAppointmentInList_returnsTrue() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        Appointment duplicate = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        assertTrue(schedule.hasOverlappingAppointment(duplicate));
    }

    @Test
    public void hasOverlappingAppointment_disjointAppointmentsInList_returnsFalse() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        assertFalse(schedule.hasOverlappingAppointment(APPOINTMENT_ALONE));
    }

    @Test
    public void getAppointmentsList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> schedule.getAppointmentList().remove(0));
    }

    @Test
    public void setAppointment_nullArguments_throwsNullPointException() {
        assertThrows(NullPointerException.class, ()
            -> schedule.setAppointment(null, null));
        assertThrows(NullPointerException.class, ()
            -> schedule.setAppointment(APPOINTMENT_ALICE, null));
        assertThrows(NullPointerException.class, ()
            -> schedule.setAppointment(null, APPOINTMENT_ALICE));
    }

    @Test
    public void setAppointment_targetAppointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, ()
            -> schedule.setAppointment(APPOINTMENT_ALICE, APPOINTMENT_ALONE));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        schedule.setAppointment(APPOINTMENT_ALICE, APPOINTMENT_ALICE);
        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(APPOINTMENT_ALICE).build();

        assertEquals(expectedSchedule, schedule);
    }

    @Test
    public void setAppointment_editedAppointmentIsOverlappingWithOnlyTarget_success() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        Appointment editedAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withName("Another Meeting")
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTimeObject().value.plusMinutes(1)).build();

        schedule.setAppointment(APPOINTMENT_ALICE, editedAppointment);
        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(editedAppointment).build();
        assertEquals(expectedSchedule, schedule);
    }

    @Test
    public void setAppointment_editedAppointmentDisjoint_success() {
        schedule.addAppointment(APPOINTMENT_ALICE);
        Appointment disjointAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withName("Another Meeting")
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTimeObject().value.plusYears(1)).build();

        schedule.setAppointment(APPOINTMENT_ALICE, disjointAppointment);
        Schedule expectedSchedule = new ScheduleBuilder().withAppointment(disjointAppointment).build();
        assertEquals(expectedSchedule, schedule);
    }

    @Test
    public void setAppointment_editedAppointmentOverlaps_throwsOverlappingAppointmentException() {
        Appointment disjointAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withName("Another Meeting")
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTimeObject().value.plusYears(1)).build();
        schedule.addAppointment(APPOINTMENT_ALICE);
        schedule.addAppointment(disjointAppointment);

        assertThrows(OverlappingAppointmentException.class, ()
            -> schedule.setAppointment(APPOINTMENT_ALICE, disjointAppointment));
    }

    @Test
    public void removeAppointment_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.removeAppointment(null));
    }

    @Test
    public void removeAppointment_appointmentNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, ()
            -> schedule.removeAppointment(APPOINTMENT_ALICE));
    }

    @Test
    public void removeAppointment_appointmentInList_success() {
        schedule.addAppointment(APPOINTMENT_ALONE);
        schedule.removeAppointment(APPOINTMENT_ALONE);
        assertEquals(new Schedule(), schedule);
    }

    @Test
    public void findSlotsBetweenAppointments_nullInputs_throwsNullPointerException() {
        LocalDateTime dummyTime = LocalDateTime.parse("2022-12-12T12:30");
        assertThrows(NullPointerException.class, ()
            -> schedule.findSlotsBetweenAppointments(null, dummyTime, 1));
        assertThrows(NullPointerException.class, ()
            -> schedule.findSlotsBetweenAppointments(dummyTime, null, 1));
    }

    @Test
    public void findSlotsBetweenAppointments_validInputs_success() {
        // This is an integration test, more thorough tests are performed in DisjointAppointmentListTest
        LocalDateTime rangeStart = LocalDateTime.parse("2022-12-12T12:30");
        LocalDateTime rangeEnd = LocalDateTime.parse("2022-12-12T14:30");

        Appointment appointment1 = new AppointmentBuilder(APPOINTMENT_ALONE)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T12:00"))
                .withDuration(30).build();
        Appointment appointment2 = new AppointmentBuilder(APPOINTMENT_ALONE)
                .withStartDateTime(LocalDateTime.parse("2022-12-12T14:30"))
                .withDuration(30).build();

        schedule.addAppointment(appointment1);
        schedule.addAppointment(appointment2);

        List<TimeRange> expectedList = List.of(new TimeRange(rangeStart, rangeEnd));

        assertEquals(expectedList, schedule.findSlotsBetweenAppointments(rangeStart, rangeEnd, 120));
        assertEquals(expectedList, schedule.findSlotsBetweenAppointments(rangeStart, rangeEnd, 60));
        assertEquals(expectedList, schedule.findSlotsBetweenAppointments(rangeStart, rangeEnd, 1));
        assertEquals(List.of(), schedule.findSlotsBetweenAppointments(rangeStart, rangeEnd, 121));
    }

    @Test
    public void equals() {
        Schedule refSchedule = new Schedule();
        Schedule otherSchedule = getTypicalSchedule();

        assertTrue(refSchedule.equals(refSchedule));
        assertTrue(refSchedule.equals(new Schedule()));
        assertTrue(otherSchedule.equals(new ScheduleBuilder(getTypicalSchedule()).build()));

        assertFalse(refSchedule.equals(null));
        assertFalse(refSchedule.equals(0));
        assertFalse(refSchedule.equals(otherSchedule));
    }

    @Test
    public void hashCodeTest() {
        Schedule refSchedule = new Schedule();
        Schedule otherSchedule = getTypicalSchedule();

        assertEquals(refSchedule.hashCode(), refSchedule.hashCode());
        assertEquals(refSchedule.hashCode(), new Schedule().hashCode());
        assertEquals(otherSchedule.hashCode(), getTypicalSchedule().hashCode());

        assertNotEquals(refSchedule.hashCode(), otherSchedule.hashCode());
    }

    /**
     * A stub ReadOnlySchedule whose appointments list can violate interface constraints.
     */
    private static class ScheduleStub implements ReadOnlySchedule {
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        ScheduleStub(Collection<Appointment> appointments) {
            this.appointments.setAll(appointments);
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public boolean hasOverlappingAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<TimeRange> findSlotsBetweenAppointments(LocalDateTime start, LocalDateTime end,
                                                            int minimumDuration) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
