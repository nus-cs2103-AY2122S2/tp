package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

public class MeetingTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingTime(null));
    }

    @Test
    public void isValidMeetingTime() {
        // null time
        assertThrows(NullPointerException.class, () -> MeetingTime.isValidMeetingTime(null));

        // invalid time
        String invalidDateTime = "";
        assertFalse(MeetingTime.isValidMeetingTime(invalidDateTime));
        String invalidDate = "32-13-2022 12:30"; // Date is invalid
        assertFalse(MeetingTime.isValidMeetingTime(invalidDate));
        String invalidTime = "01-01-2022 34:56"; // Time is invalid
        assertFalse(MeetingTime.isValidMeetingTime(invalidTime));
        invalidDate = "31-02-2022 12:30";
        assertFalse(MeetingTime.isValidMeetingTime(invalidDate));

        // valid time
        String validTime = "01-01-2022 12:30";
        assertTrue(MeetingTime.isValidMeetingTime(validTime));
    }

    @Test
    public void prettyTimeTest() {
        LocalDateTime dateTime = LocalDateTime.of(2022, Month.MAY, 29, 19, 30, 40);
        assertEquals("07:30 PM", MeetingTime.prettyTime(dateTime));
    }

    @Test
    public void prettyDateTest() {
        LocalDateTime ldt = LocalDateTime.of(2022, Month.OCTOBER, 29, 19, 30, 40);
        assertEquals("Oct 29 2022", MeetingTime.prettyDate(ldt));
    }
}
