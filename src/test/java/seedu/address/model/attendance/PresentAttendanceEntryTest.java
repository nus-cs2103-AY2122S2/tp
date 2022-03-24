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

    // Valid attendance entries
    public static final PresentAttendanceEntry VALID_PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);
    public static final PresentAttendanceEntry NO_TIMINGS_PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, null, null);
    public static final PresentAttendanceEntry EDGE_CASE_PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, LocalTime.of(0, 0), LocalTime.of(23, 59));

    // Invalid attendance entries
    public static final PresentAttendanceEntry INVALID_PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, LocalTime.of(15, 0), LocalTime.of(9, 0));
    public static final PresentAttendanceEntry SAME_TIMING_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, PICKUP_TIME);
    public static final PresentAttendanceEntry OVERNIGHT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, LocalTime.of(1, 0), LocalTime.of(0, 30));

    @Test
    public void getPickUpTime() {
        assertEquals(VALID_PRESENT_ATTENDANCE_ENTRY.getPickUpTime(), Optional.of(PICKUP_TIME));
    }

    @Test
    public void getDropOffTime() {
        assertEquals(VALID_PRESENT_ATTENDANCE_ENTRY.getDropOffTime(), Optional.of(DROPOFF_TIME));
    }

    @Test
    public void isValidTimings_validTimings_returnsTrue() {
        assertTrue(NO_TIMINGS_PRESENT_ATTENDANCE_ENTRY.isValidTimings());
        assertTrue(EDGE_CASE_PRESENT_ATTENDANCE_ENTRY.isValidTimings());
        assertTrue(VALID_PRESENT_ATTENDANCE_ENTRY.isValidTimings());
    }

    @Test
    public void isValidTimings_invalidTimings_returnsFalse() {
        assertFalse(INVALID_PRESENT_ATTENDANCE_ENTRY.isValidTimings());
        assertFalse(SAME_TIMING_ATTENDANCE_ENTRY.isValidTimings());
        assertFalse(OVERNIGHT_ATTENDANCE_ENTRY.isValidTimings());
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

        assertTrue(VALID_PRESENT_ATTENDANCE_ENTRY.equals(VALID_PRESENT_ATTENDANCE_ENTRY));
        assertTrue(VALID_PRESENT_ATTENDANCE_ENTRY.equals(similarEntry));

        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(null));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(ABSENT_ATTENDANCE_ENTRY));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentDateEntry));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentPickUpTimeEntry));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentDropOffTimeEntry));
    }
}
