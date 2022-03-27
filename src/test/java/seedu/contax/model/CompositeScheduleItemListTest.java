package seedu.contax.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALONE;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.AppointmentSlot;
import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.chrono.TimeRange;

public class CompositeScheduleItemListTest {

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(null, null));
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(FXCollections.emptyObservableList(), null));
        assertThrows(NullPointerException.class, ()
            -> new CompositeObservableList<>(null, FXCollections.emptyObservableList()));
    }

    @Test
    public void testListChangeNotify() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        ObservableList<AppointmentSlot> slotList = FXCollections.observableArrayList();
        CompositeObservableList<ScheduleItem> compositeList =
                new CompositeObservableList<>(appointmentList, slotList);
        LocalDateTime refTime = APPOINTMENT_ALONE.getStartDateTime();
        AppointmentSlot slot = new AppointmentSlot(
                new TimeRange(refTime.plusMinutes(120), refTime.plusMinutes(180)));

        assertEquals(List.of(), compositeList.getUnmodifiableList());
        appointmentList.add(APPOINTMENT_ALONE);
        slotList.add(slot);
        assertEquals(List.of(APPOINTMENT_ALONE, slot), compositeList.getUnmodifiableList());

        AppointmentSlot slotBefore = new AppointmentSlot(
                new TimeRange(refTime.minusMinutes(120), refTime.minusMinutes(20)));
        slotList.set(0, slotBefore);
        assertEquals(List.of(slotBefore, APPOINTMENT_ALONE), compositeList.getUnmodifiableList());
    }
}
