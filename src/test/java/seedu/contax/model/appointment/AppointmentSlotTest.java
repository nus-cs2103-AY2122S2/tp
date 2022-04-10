package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.contax.model.chrono.ScheduleItem;
import seedu.contax.model.chrono.TimeRange;

public class AppointmentSlotTest {

    @Test
    public void constructor_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentSlot(null));
    }

    @Test
    public void testToString() {
        LocalDateTime startTime = LocalDateTime.MIN;
        LocalDateTime endTime = LocalDateTime.MAX;
        AppointmentSlot appointmentSlot = new AppointmentSlot(new TimeRange(startTime, endTime));

        assertEquals("Start Date Time: " + startTime + "; End Date Time: " + endTime,
                appointmentSlot.toString());
    }

    @Test
    public void equals() {
        LocalDateTime startTime = LocalDateTime.MIN;
        LocalDateTime endTime = LocalDateTime.MAX;
        AppointmentSlot referenceObj = new AppointmentSlot(new TimeRange(startTime, endTime));
        AppointmentSlot copyObj = new AppointmentSlot(new TimeRange(startTime, endTime));

        assertTrue(referenceObj.equals(referenceObj));
        assertTrue(referenceObj.equals(copyObj));

        assertFalse(referenceObj.equals(null));
        assertFalse(referenceObj.equals(123));
        assertFalse(referenceObj.equals("string"));
        assertFalse(referenceObj.equals(new ScheduleItemStub(new TimeRange(startTime, endTime))));
        // Different range
        assertFalse(referenceObj.equals(new AppointmentSlot(new TimeRange(startTime, startTime))));
    }

    /** Dummy class of a different ScheduleItem type. */
    private static class ScheduleItemStub extends ScheduleItem {
        public ScheduleItemStub(TimeRange period) {
            super(period);
        }
    }
}
