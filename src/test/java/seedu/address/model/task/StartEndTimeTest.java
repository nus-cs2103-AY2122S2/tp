package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StartEndTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        //Start Time
        assertThrows(NullPointerException.class, () -> new StartTime(null));
        //End Time
        assertThrows(NullPointerException.class, () -> new EndTime(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        //Start Time
        assertThrows(IllegalArgumentException.class, () -> new StartTime(invalidTime));
        //End Time
        assertThrows(IllegalArgumentException.class, () -> new EndTime(invalidTime));
    }

    @Test
    public void isValidStartEndTime() {
        // null time
        assertThrows(NullPointerException.class, () -> StartTime.isValidStartTime(null));
        assertThrows(NullPointerException.class, () -> EndTime.isValidEndTime(null));

        // invalid time
        // start time
        assertFalse(StartTime.isValidStartTime("")); // empty string
        assertFalse(StartTime.isValidStartTime(" ")); // spaces only
        assertFalse(StartTime.isValidStartTime("2:00")); // 1 digit hour format
        assertFalse(StartTime.isValidStartTime("02:3")); // 1 digit minute format
        assertFalse(StartTime.isValidStartTime("25:00")); // invalid hour range
        assertFalse(StartTime.isValidStartTime("00:60")); // invalid minute range
        // end time
        assertFalse(EndTime.isValidEndTime("")); // empty string
        assertFalse(EndTime.isValidEndTime(" ")); // spaces only
        assertFalse(EndTime.isValidEndTime("2:00")); // 1 digit hour format
        assertFalse(EndTime.isValidEndTime("02:3")); // 1 digit minute format
        assertFalse(EndTime.isValidEndTime("25:00")); // invalid hour range
        assertFalse(EndTime.isValidEndTime("00:60")); // invalid minute range

        //valid time
        //start time
        assertTrue(StartTime.isValidStartTime("02:00")); // valid hour and minute format
        //end time
        assertTrue(EndTime.isValidEndTime("02:00")); // valid hour and minute format
    }
}
