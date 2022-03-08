package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;
import static seedu.contax.testutil.TypicalAppointments.getTypicalSchedule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.exceptions.OverlappingAppointmentException;
import seedu.contax.testutil.AppointmentBuilder;

public class ScheduleTest {

    private final Schedule schedule = new Schedule();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), schedule.getAppointmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> schedule.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Schedule newData = getTypicalSchedule();
        schedule.resetData(newData);
        assertEquals(newData, schedule);
    }

    @Test
    public void resetData_withOverlappingAppointments_throwsOverlappingAppointmentException() {
        // Construct two appointments that have overlapping periods
        Appointment overlappingAppointment = new AppointmentBuilder(APPOINTMENT_ALICE)
                .withStartDateTime(APPOINTMENT_ALICE.getStartDateTime().value.plusMinutes(1))
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
    }

}
