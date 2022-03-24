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
        assertTrue(PresentAttendanceEntry.isValidTimings(PICKUP_TIME, DROPOFF_TIME));
        assertTrue(PresentAttendanceEntry.isValidTimings(
            LocalTime.of(0, 0), LocalTime.of(23, 59)));
        assertTrue(PresentAttendanceEntry.isValidTimings(null, null));
    }

    @Test
    public void isValidTimings_invalidTimings_returnsFalse() {
        assertFalse(PresentAttendanceEntry.isValidTimings(DROPOFF_TIME, PICKUP_TIME));
        assertFalse(PresentAttendanceEntry.isValidTimings(
            LocalTime.of(9, 0), LocalTime.of(9, 0)));
        assertFalse(PresentAttendanceEntry.isValidTimings(
            LocalTime.of(23, 59), LocalTime.of(9, 0)
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

        assertTrue(VALID_PRESENT_ATTENDANCE_ENTRY.equals(VALID_PRESENT_ATTENDANCE_ENTRY));
        assertTrue(VALID_PRESENT_ATTENDANCE_ENTRY.equals(similarEntry));

        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(null));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(ABSENT_ATTENDANCE_ENTRY));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentDateEntry));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentPickUpTimeEntry));
        assertFalse(VALID_PRESENT_ATTENDANCE_ENTRY.equals(differentDropOffTimeEntry));
    }
}
