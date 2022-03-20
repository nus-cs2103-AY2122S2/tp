package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PresentAttendanceEntryTest {
    public static final LocalDate DATE_TODAY = LocalDate.now();
    public static final LocalTime PICKUP_TIME = LocalTime.of(9, 0);
    public static final LocalTime DROPOFF_TIME = LocalTime.of(18, 0);

    public static final AbsentAttendanceEntry ABSENT_ATTENDANCE_ENTRY =
            new AbsentAttendanceEntry(DATE_TODAY);
    public static final PresentAttendanceEntry PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);

    @Test
    public void getPickUpTime() {
        assertEquals(PRESENT_ATTENDANCE_ENTRY.getPickUpTime(), Optional.of(PICKUP_TIME));
    }

    @Test
    public void getDropOffTime() {
        assertEquals(PRESENT_ATTENDANCE_ENTRY.getDropOffTime(), Optional.of(DROPOFF_TIME));
    }

    @Test
    public void isValidTimings_validTimings_returnsTrue() {
        assertTrue(PresentAttendanceEntry.isValidTimings(
                LocalTime.of(7,0),
                LocalTime.of(17,30)
        ));
        assertTrue(PresentAttendanceEntry.isValidTimings(
                LocalTime.of(0,0),
                LocalTime.of(23,59)
        ));
    }

    @Test
    public void isValidTimings_invalidTimings_returnsFalse() {
        assertFalse(PresentAttendanceEntry.isValidTimings(
                LocalTime.of(15,0),
                LocalTime.of(9,0)
        ));
        assertFalse(PresentAttendanceEntry.isValidTimings(
                LocalTime.of(14,0),
                LocalTime.of(14,0)
        ));
        assertFalse(PresentAttendanceEntry.isValidTimings(
                LocalTime.of(1,0),
                LocalTime.of(0,30)
        ));
    }

    @Test
    public void equals() {
        PresentAttendanceEntry similarEntry =
                new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);
        PresentAttendanceEntry differentDateEntry =
                new PresentAttendanceEntry(DATE_TODAY.plusDays(4), PICKUP_TIME, DROPOFF_TIME);
        PresentAttendanceEntry differentPickUpTimeEntry =
                new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME.plusHours(1), DROPOFF_TIME);
        PresentAttendanceEntry differentDropOffTimeEntry =
                new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME.plusHours(2));

        assertTrue(PRESENT_ATTENDANCE_ENTRY.equals(PRESENT_ATTENDANCE_ENTRY));
        assertTrue(PRESENT_ATTENDANCE_ENTRY.equals(similarEntry));

        assertFalse(PRESENT_ATTENDANCE_ENTRY.equals(null));
        assertFalse(PRESENT_ATTENDANCE_ENTRY.equals(ABSENT_ATTENDANCE_ENTRY));
        assertFalse(PRESENT_ATTENDANCE_ENTRY.equals(differentDateEntry));
        assertFalse(PRESENT_ATTENDANCE_ENTRY.equals(differentPickUpTimeEntry));
        assertFalse(PRESENT_ATTENDANCE_ENTRY.equals(differentDropOffTimeEntry));
    }
}
