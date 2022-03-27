package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.model.attendance.AbsentAttendanceEntry;
import seedu.address.model.attendance.AttendanceEntry;
import seedu.address.model.attendance.PresentAttendanceEntry;

public class AttendanceHashMapTest {

    private static final LocalDate DATE_TODAY = LocalDate.now();
    private static final LocalDate DATE_TOMORROW = DATE_TODAY.plusDays(1);
    private static final LocalTime PICKUP_TIME = LocalTime.of(9, 0);
    private static final LocalTime DROPOFF_TIME = LocalTime.of(17, 30);

    private static final PresentAttendanceEntry presentAttendanceEntryWithTransport =
        new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);
    private static final AbsentAttendanceEntry absentAttendanceEntry =
        new AbsentAttendanceEntry(DATE_TODAY);
    private static final PresentAttendanceEntry presentAttendanceEntryWithoutTransport =
        new PresentAttendanceEntry(DATE_TODAY, null, null);

    private static AttendanceHashMap emptyAttendanceHashMap;
    private static AttendanceHashMap presentEntryAttendanceHashMap;
    private static AttendanceHashMap absentEntryAttendanceHashMap;

    @BeforeAll
    static void setupAttendanceHashMaps() {
        emptyAttendanceHashMap = new AttendanceHashMap();

        HashMap<LocalDate, AttendanceEntry> tempPresentHashMap = new HashMap<>();
        tempPresentHashMap.put(DATE_TODAY, presentAttendanceEntryWithTransport);
        presentEntryAttendanceHashMap = new AttendanceHashMap(tempPresentHashMap);

        HashMap<LocalDate, AttendanceEntry> tempAbsentHashMap = new HashMap<>();
        tempAbsentHashMap.put(DATE_TODAY, absentAttendanceEntry);
        absentEntryAttendanceHashMap = new AttendanceHashMap(tempAbsentHashMap);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AttendanceHashMap(null));
    }

    @Test
    public void containsAttendance() {
        // Attendance hash map with present entry that has transport arrangement
        assertTrue(presentEntryAttendanceHashMap.containsAttendance(presentAttendanceEntryWithTransport));
        assertFalse(presentEntryAttendanceHashMap.containsAttendance(presentAttendanceEntryWithoutTransport));
        assertFalse(presentEntryAttendanceHashMap.containsAttendance(absentAttendanceEntry));

        // Empty attendance hash map
        assertFalse(emptyAttendanceHashMap.containsAttendance(presentAttendanceEntryWithTransport));
        assertFalse(emptyAttendanceHashMap.containsAttendance(presentAttendanceEntryWithoutTransport));
        assertFalse(emptyAttendanceHashMap.containsAttendance(absentAttendanceEntry));

        // Attendance hash map with absent entry
        assertTrue(absentEntryAttendanceHashMap.containsAttendance(absentAttendanceEntry));
        assertFalse(absentEntryAttendanceHashMap.containsAttendance(presentAttendanceEntryWithTransport));
        assertFalse(absentEntryAttendanceHashMap.containsAttendance(presentAttendanceEntryWithoutTransport));
    }

    @Test
    public void getAttendance() {
        // Successful retrieval of attendance entry
        assertEquals(presentEntryAttendanceHashMap.getAttendance(DATE_TODAY),
            Optional.of(presentAttendanceEntryWithTransport));
        assertEquals(absentEntryAttendanceHashMap.getAttendance(DATE_TODAY),
            Optional.of(absentAttendanceEntry));

        // No entry retrieved.
        assertEquals(emptyAttendanceHashMap.getAttendance(DATE_TODAY), Optional.empty());
        assertEquals(presentEntryAttendanceHashMap.getAttendance(DATE_TOMORROW), Optional.empty());
        assertEquals(absentEntryAttendanceHashMap.getAttendance(DATE_TOMORROW), Optional.empty());
    }
}
