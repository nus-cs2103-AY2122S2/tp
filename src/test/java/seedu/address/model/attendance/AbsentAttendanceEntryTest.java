package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class AbsentAttendanceEntryTest {
    public static final LocalDate DATE_TODAY = LocalDate.now();
    public static final LocalTime PICKUP_TIME = LocalTime.of(9, 0);
    public static final LocalTime DROPOFF_TIME = LocalTime.of(18, 0);

    public static final AbsentAttendanceEntry ABSENT_ATTENDANCE_ENTRY =
            new AbsentAttendanceEntry(DATE_TODAY);
    public static final PresentAttendanceEntry PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);
    @Test
    public void getPickUpTime() {
        assertEquals(ABSENT_ATTENDANCE_ENTRY.getPickUpTime(), Optional.empty());
    }

    @Test
    public void getDropOffTime() {
        assertEquals(ABSENT_ATTENDANCE_ENTRY.getDropOffTime(), Optional.empty());
    }

    @Test
    public void equals() {
        AbsentAttendanceEntry similarAbsentAttendanceEntry = new AbsentAttendanceEntry(DATE_TODAY);
        AbsentAttendanceEntry differentAbsentAttendanceEntry = new AbsentAttendanceEntry(DATE_TODAY.plusDays(1));

        assertTrue(ABSENT_ATTENDANCE_ENTRY.equals(ABSENT_ATTENDANCE_ENTRY));
        assertTrue(ABSENT_ATTENDANCE_ENTRY.equals(similarAbsentAttendanceEntry));

        assertFalse(ABSENT_ATTENDANCE_ENTRY.equals(differentAbsentAttendanceEntry));
        assertFalse(ABSENT_ATTENDANCE_ENTRY.equals(PRESENT_ATTENDANCE_ENTRY));
        assertFalse(ABSENT_ATTENDANCE_ENTRY.equals(null));
    }
}
