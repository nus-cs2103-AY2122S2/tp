package seedu.contax.model.chrono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TimeRangeTest {

    private static final LocalDateTime REF_TIME = LocalDateTime.parse("2022-12-12T12:30");

    @Test
    public void constructorTest_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeRange(REF_TIME, null));
        assertThrows(NullPointerException.class, () -> new TimeRange(null, REF_TIME));
    }

    @Test
    public void constructorTest_endBeforeStart_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TimeRange(REF_TIME.plusMinutes(1), REF_TIME));
    }

    @Test
    public void getterTest_validDates_success() {
        final int duration = 123;
        TimeRange timeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(duration));
        assertEquals(REF_TIME, timeRange.getStartDateTime());
        assertEquals(REF_TIME.plusMinutes(duration), timeRange.getEndDateTime());
        assertEquals(duration, timeRange.getDuration());
        assertEquals(REF_TIME, timeRange.getComparableDateTime());
    }

    @Test
    public void getDuration_zeroMinute_success() {
        TimeRange timeRange = new TimeRange(REF_TIME, REF_TIME);
        assertEquals(0, timeRange.getDuration());
    }

    @Test
    public void compareTo() {
        TimeRange refTimeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(2));
        TimeRange sameStart = new TimeRange(REF_TIME, REF_TIME.plusMinutes(10));
        TimeRange laterStart = new TimeRange(REF_TIME.plusMinutes(1), REF_TIME.plusMinutes(5));
        TimeRange earlierStart = new TimeRange(REF_TIME.minusMinutes(1), REF_TIME.plusMinutes(100));

        assertEquals(0, refTimeRange.compareTo(sameStart));
        assertTrue(refTimeRange.compareTo(earlierStart) > 0);
        assertTrue(refTimeRange.compareTo(laterStart) < 0);
    }

    @Test
    public void equals() {
        TimeRange refTimeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(2));
        TimeRange copyTimeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(2));

        // same object -> returns true
        assertTrue(refTimeRange.equals(refTimeRange));

        // same value -> returns true
        assertTrue(refTimeRange.equals(copyTimeRange));

        // null -> returns false
        assertFalse(refTimeRange.equals(null));

        // different type -> returns false
        assertFalse(refTimeRange.equals(5));

        // different start time -> returns false
        assertFalse(refTimeRange.equals(new TimeRange(REF_TIME.plusMinutes(1), REF_TIME.plusMinutes(2))));

        // different end time -> returns false
        assertFalse(refTimeRange.equals(new TimeRange(REF_TIME, REF_TIME.plusMinutes(3))));
    }

    @Test
    public void hashCodeTest() {
        TimeRange refTimeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(2));
        TimeRange copyTimeRange = new TimeRange(REF_TIME, REF_TIME.plusMinutes(2));

        // Same variables
        assertTrue(refTimeRange.hashCode() == copyTimeRange.hashCode());
        assertTrue(refTimeRange.hashCode() == refTimeRange.hashCode());

        // Different variables
        assertFalse(refTimeRange.hashCode()
                == new TimeRange(REF_TIME, REF_TIME.plusMinutes(1)).hashCode());

        // different end time -> returns false
        assertFalse(refTimeRange.hashCode()
                == new TimeRange(REF_TIME.plusMinutes(1), REF_TIME.plusMinutes(2)).hashCode());
    }
}
