package seedu.contax.model.chrono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TimeRangeTest {

    @Test
    public void constructorTest_nullParameters_throwsNullPointerException() {
        LocalDateTime validDateTime = LocalDateTime.MAX;
        assertThrows(NullPointerException.class, () -> new TimeRange(validDateTime, null));
        assertThrows(NullPointerException.class, () -> new TimeRange(null, validDateTime));
    }

    @Test
    public void constructorTest_endBeforeStart_throwsIllegalArgumentException() {
        LocalDateTime refTime = LocalDateTime.parse("2022-12-12T12:30");
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(refTime.plusMinutes(1), refTime));
    }

    @Test
    public void getterTest_validDates_success() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");

        TimeRange timeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(123));
        assertEquals(refTimeStart, timeRange.getStartDateTime());
        assertEquals(refTimeStart.plusMinutes(123), timeRange.getEndDateTime());
        assertEquals(123, timeRange.getDuration());
        assertEquals(refTimeStart, timeRange.getComparableDateTime());
    }

    @Test
    public void getDuration_zeroMinute_success() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");

        TimeRange timeRange = new TimeRange(refTimeStart, refTimeStart);
        assertEquals(0, timeRange.getDuration());
    }

    @Test
    public void compareTo() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");

        TimeRange refTimeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(2));
        TimeRange sameStart = new TimeRange(refTimeStart, refTimeStart.plusMinutes(10));
        TimeRange laterStart = new TimeRange(refTimeStart.plusMinutes(1), refTimeStart.plusMinutes(5));
        TimeRange earlierStart = new TimeRange(refTimeStart.minusMinutes(1), refTimeStart.plusMinutes(100));

        assertEquals(0, refTimeRange.compareTo(sameStart));
        assertTrue(refTimeRange.compareTo(earlierStart) > 0);
        assertTrue(refTimeRange.compareTo(laterStart) < 0);
    }

    @Test
    public void equals() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");
        TimeRange refTimeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(2));
        TimeRange copyTimeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(2));

        assertTrue(refTimeRange.equals(copyTimeRange));

        // same object -> returns true
        assertTrue(refTimeRange.equals(refTimeRange));

        // null -> returns false
        assertFalse(refTimeRange.equals(null));

        // different type -> returns false
        assertFalse(refTimeRange.equals(5));

        // different start time -> returns false
        assertFalse(refTimeRange.equals(new TimeRange(refTimeStart.plusMinutes(1), refTimeStart.plusMinutes(2))));

        // different end time -> returns false
        assertFalse(refTimeRange.equals(new TimeRange(refTimeStart, refTimeStart.plusMinutes(3))));
    }

    @Test
    public void hashCodeTest() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");
        TimeRange refTimeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(2));
        TimeRange copyTimeRange = new TimeRange(refTimeStart, refTimeStart.plusMinutes(2));

        // Same variables
        assertTrue(refTimeRange.hashCode() == copyTimeRange.hashCode());
        assertTrue(refTimeRange.hashCode() == refTimeRange.hashCode());

        // Different variables
        assertFalse(refTimeRange.hashCode()
                == new TimeRange(refTimeStart, refTimeStart.plusMinutes(1)).hashCode());

        // different end time -> returns false
        assertFalse(refTimeRange.hashCode()
                == new TimeRange(refTimeStart.plusMinutes(1), refTimeStart.plusMinutes(2)).hashCode());
    }
}
