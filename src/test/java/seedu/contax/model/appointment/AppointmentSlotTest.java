package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
}
