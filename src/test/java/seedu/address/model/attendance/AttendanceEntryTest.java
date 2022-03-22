package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class AttendanceEntryTest {
    public static final LocalDate DATE_TODAY = LocalDate.now();
    public static final LocalTime PICKUP_TIME = LocalTime.of(9, 0);
    public static final LocalTime DROPOFF_TIME = LocalTime.of(18, 0);

    public static final AttendanceEntry PRESENT_ATTENDANCE_ENTRY =
            new PresentAttendanceEntry(DATE_TODAY, PICKUP_TIME, DROPOFF_TIME);
    public static final AttendanceEntry ABSENT_ATTENDANCE_ENTRY =
            new AbsentAttendanceEntry(DATE_TODAY);

    @Test
    public void getAttendanceDate_presentAttendanceEntry_returnsCorrectDate() {
        assertEquals(PRESENT_ATTENDANCE_ENTRY.getAttendanceDate(), DATE_TODAY);
    }

    @Test
    public void getAttendanceDate_absentAttendanceEntry_returnsCorrectDate() {
        assertEquals(ABSENT_ATTENDANCE_ENTRY.getAttendanceDate(), DATE_TODAY);
    }
}
