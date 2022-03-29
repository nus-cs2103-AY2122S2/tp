package seedu.contax.model.chrono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ScheduleItemTest {

    @Test
    public void constructorTest_nullParameters_throwsNullPointerException() {
        LocalDateTime validDateTime = LocalDateTime.MAX;
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(null));
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(validDateTime, null));
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(null, validDateTime));
    }

    @Test
    public void constructorTest_endBeforeStart_throwsIllegalArgumentException() {
        LocalDateTime refTime = LocalDateTime.parse("2022-12-12T12:30");
        assertThrows(IllegalArgumentException.class, () -> new ScheduleItemStub(refTime.plusMinutes(1), refTime));
    }

    @Test
    public void getterTest_validDates_success() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");

        ScheduleItem scheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(123));
        assertEquals(refTimeStart, scheduleItem.getStartDateTime());
        assertEquals(refTimeStart.plusMinutes(123), scheduleItem.getEndDateTime());
        assertEquals(refTimeStart, scheduleItem.getComparableDateTime());

        ScheduleItem scheduleItemCopy = new ScheduleItemStub(
                new TimeRange(refTimeStart, refTimeStart.plusMinutes(123)));

        assertEquals(scheduleItem, scheduleItemCopy);
    }

    @Test
    public void isOverlapping() {
        LocalDateTime startTime1 = LocalDateTime.parse("2020-04-23T12:34:00");
        ScheduleItem scheduleItem1 = new ScheduleItemStub(startTime1, startTime1.plusMinutes(30));

        LocalDateTime startTime2 = LocalDateTime.parse("2020-04-23T12:34:30");
        ScheduleItem scheduleItem2 = new ScheduleItemStub(startTime2, startTime2.plusMinutes(30));

        LocalDateTime startTime3 = LocalDateTime.parse("2020-04-23T13:03:00");
        ScheduleItem scheduleItem3 = new ScheduleItemStub(startTime3, startTime3.plusMinutes(30));

        LocalDateTime startTime4 = LocalDateTime.parse("2020-04-23T12:10:00");
        ScheduleItem scheduleItem4 = new ScheduleItemStub(startTime4, startTime4.plusMinutes(30));

        LocalDateTime startTime5 = LocalDateTime.parse("2020-04-23T12:40:00");
        ScheduleItem scheduleItem5 = new ScheduleItemStub(startTime5, startTime5.plusMinutes(15));

        LocalDateTime startTime6 = LocalDateTime.parse("2020-04-23T12:10:00");
        ScheduleItem scheduleItem6 = new ScheduleItemStub(startTime6, startTime6.plusMinutes(60));

        // Bounds Testing
        ScheduleItem scheduleItem7 = new ScheduleItemStub(startTime1, startTime1.plusMinutes(60));
        ScheduleItem scheduleItem8 = new ScheduleItemStub(startTime1, startTime1.plusMinutes(15));

        LocalDateTime startTime9 = LocalDateTime.parse("2020-04-23T12:44:00");
        ScheduleItem scheduleItem9 = new ScheduleItemStub(startTime9, startTime9.plusMinutes(20));

        LocalDateTime startTime10 = LocalDateTime.parse("2020-04-23T12:24:00");
        ScheduleItem scheduleItem10 = new ScheduleItemStub(startTime10, startTime10.plusMinutes(40));

        LocalDateTime startTime11 = LocalDateTime.parse("2020-04-23T13:04:00");
        ScheduleItem scheduleItem11 = new ScheduleItemStub(startTime11, startTime11.plusMinutes(30));

        LocalDateTime startTime12 = LocalDateTime.parse("2020-04-23T12:33:00");
        ScheduleItem scheduleItem12 = new ScheduleItemStub(startTime12, startTime12.plusMinutes(1));

        assertTrue(scheduleItem1.isOverlapping(scheduleItem1)); // Will overlap with itself
        assertTrue(scheduleItem1.isOverlapping(scheduleItem2)); // Will overlap since seconds are zeroed
        assertTrue(scheduleItem2.isOverlapping(scheduleItem1)); // Symmetric test
        assertTrue(scheduleItem1.isOverlapping(scheduleItem3)); // 3 starts before 1 ends
        assertTrue(scheduleItem3.isOverlapping(scheduleItem1)); // Symmetric test
        assertTrue(scheduleItem1.isOverlapping(scheduleItem4)); // 1 starts before 4 ends
        assertTrue(scheduleItem4.isOverlapping(scheduleItem1)); // Symmetric test
        assertTrue(scheduleItem1.isOverlapping(scheduleItem5)); // 5 is contained in 1
        assertTrue(scheduleItem5.isOverlapping(scheduleItem1)); // Symmetric test
        assertTrue(scheduleItem1.isOverlapping(scheduleItem6)); // 1 is contained in 6
        assertTrue(scheduleItem6.isOverlapping(scheduleItem1)); // Symmetric test

        /* Testing Overlapping Bounds */
        // 1 and 7 start at the same time, 1 is contained in 7
        assertTrue(scheduleItem1.isOverlapping(scheduleItem7));
        assertTrue(scheduleItem7.isOverlapping(scheduleItem1)); // Symmetric test

        // 1 and 8 start at the same time, 8 is contained in 1
        assertTrue(scheduleItem1.isOverlapping(scheduleItem8));
        assertTrue(scheduleItem8.isOverlapping(scheduleItem1)); // Symmetric test

        // 1 and 9 end at the same time, 9 is contained in 1
        assertTrue(scheduleItem1.isOverlapping(scheduleItem9));
        assertTrue(scheduleItem9.isOverlapping(scheduleItem1)); // Symmetric test

        // 1 and 10 end at the same time, 1 is contained in 10
        assertTrue(scheduleItem1.isOverlapping(scheduleItem10));
        assertTrue(scheduleItem10.isOverlapping(scheduleItem1)); // Symmetric test

        assertFalse(scheduleItem1.isOverlapping(scheduleItem11));
        assertFalse(scheduleItem11.isOverlapping(scheduleItem1));
        assertFalse(scheduleItem1.isOverlapping(scheduleItem12));
        assertFalse(scheduleItem12.isOverlapping(scheduleItem1));
    }

    @Test
    public void compareTo() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");

        ScheduleItem refScheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(2));
        ScheduleItem sameStart = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(10));
        ScheduleItem laterStart = new ScheduleItemStub(refTimeStart.plusMinutes(1), refTimeStart.plusMinutes(5));
        ScheduleItem earlierStart = new ScheduleItemStub(refTimeStart.minusMinutes(1), refTimeStart.plusMinutes(100));

        assertEquals(0, refScheduleItem.compareTo(sameStart));
        assertTrue(refScheduleItem.compareTo(earlierStart) > 0);
        assertTrue(refScheduleItem.compareTo(laterStart) < 0);
    }

    @Test
    public void equals() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");
        ScheduleItem refScheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(2));
        ScheduleItem copyScheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(2));

        assertTrue(refScheduleItem.equals(copyScheduleItem));

        // same object -> returns true
        assertTrue(refScheduleItem.equals(refScheduleItem));

        // null -> returns false
        assertFalse(refScheduleItem.equals(null));

        // different type -> returns false
        assertFalse(refScheduleItem.equals(5));

        // different start time -> returns false
        assertFalse(refScheduleItem.equals(new ScheduleItemStub(refTimeStart.plusMinutes(1),
                refTimeStart.plusMinutes(2))));

        // different end time -> returns false
        assertFalse(refScheduleItem.equals(new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(3))));
    }

    @Test
    public void hashCodeTest() {
        LocalDateTime refTimeStart = LocalDateTime.parse("2022-12-12T12:30");
        ScheduleItem refScheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(2));
        ScheduleItem copyScheduleItem = new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(2));

        // Same variables
        assertTrue(refScheduleItem.hashCode() == copyScheduleItem.hashCode());
        assertTrue(refScheduleItem.hashCode() == refScheduleItem.hashCode());

        // Different variables
        assertFalse(refScheduleItem.hashCode()
                == new ScheduleItemStub(refTimeStart, refTimeStart.plusMinutes(1)).hashCode());

        // different end time -> returns false
        assertFalse(refScheduleItem.hashCode()
                == new ScheduleItemStub(refTimeStart.plusMinutes(1), refTimeStart.plusMinutes(2)).hashCode());
    }

    // Stub class for testing that does not add additional variables.
    private class ScheduleItemStub extends ScheduleItem {
        ScheduleItemStub(LocalDateTime start, LocalDateTime end) {
            super(start, end);
        }

        ScheduleItemStub(TimeRange period) {
            super(period);
        }
    }

}
