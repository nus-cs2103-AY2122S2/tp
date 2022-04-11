package seedu.contax.model.chrono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ScheduleItemTest {

    private static final LocalDateTime REF_TIME = LocalDateTime.parse("2020-04-23T12:34:00");
    private static final ScheduleItem REF_SCHEDULE_ITEM = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(30));

    @Test
    public void constructorTest_nullParameters_throwsNullPointerException() {
        LocalDateTime validDateTime = REF_TIME;
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(null));
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(validDateTime, null));
        assertThrows(NullPointerException.class, () -> new ScheduleItemStub(null, validDateTime));
    }

    @Test
    public void constructorTest_endBeforeStart_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ScheduleItemStub(REF_TIME.plusMinutes(1), REF_TIME));
    }

    @Test
    public void constructorTest_validParameters_success() {
        assertEquals(new ScheduleItemStub(new TimeRange(REF_TIME, REF_TIME.plusMinutes(1))),
                new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(1)));
    }

    @Test
    public void getterTest_validDates_success() {
        final int duration = 123;

        ScheduleItem scheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(duration));
        assertEquals(REF_TIME, scheduleItem.getStartDateTime());
        assertEquals(REF_TIME.plusMinutes(duration), scheduleItem.getEndDateTime());
        assertEquals(REF_TIME, scheduleItem.getComparableDateTime());
    }

    @Test
    public void isOverlapping_identity_overlapping() {
        // Identity Test
        ScheduleItem differentSeconds = new ScheduleItemStub(REF_TIME.plusSeconds(30),
                REF_TIME.plusSeconds(30).plusMinutes(30));
        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(REF_SCHEDULE_ITEM)); // Will overlap with itself
        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(differentSeconds)); // Will overlap since seconds are zeroed
        assertTrue(differentSeconds.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
    }

    @Test
    public void isOverlapping_boundsTest_success() {
        ScheduleItem startBeforeEnd = new ScheduleItemStub(REF_TIME.plusMinutes(29), REF_TIME.plusMinutes(59));
        ScheduleItem startAtEnd = new ScheduleItemStub(REF_TIME.plusMinutes(30), REF_TIME.plusMinutes(45));
        ScheduleItem startAfterEnd = new ScheduleItemStub(REF_TIME.plusMinutes(31), REF_TIME.plusMinutes(45));

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(startBeforeEnd)); // Starts before reference ends
        assertTrue(startBeforeEnd.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertFalse(REF_SCHEDULE_ITEM.isOverlapping(startAtEnd)); // Starts when reference ends -> OK
        assertFalse(startAtEnd.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertFalse(REF_SCHEDULE_ITEM.isOverlapping(startAfterEnd)); // Starts after reference ends -> OK
        assertFalse(startAfterEnd.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test

        ScheduleItem endAfterStart = new ScheduleItemStub(REF_TIME.minusMinutes(29), REF_TIME.plusMinutes(1));
        ScheduleItem endAtStart = new ScheduleItemStub(REF_TIME.minusMinutes(30), REF_TIME);
        ScheduleItem endBeforeStart = new ScheduleItemStub(REF_TIME.minusMinutes(31), REF_TIME.minusMinutes(1));

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(endAfterStart)); // Ends after reference starts
        assertTrue(endAfterStart.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertFalse(REF_SCHEDULE_ITEM.isOverlapping(endAtStart)); // Ends when reference starts -> OK
        assertFalse(endAtStart.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertFalse(REF_SCHEDULE_ITEM.isOverlapping(endBeforeStart)); // Ends before reference starts -> OK
        assertFalse(endBeforeStart.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
    }

    @Test
    public void isOverlapping_contained_overlapping() {
        ScheduleItem shorterItem = new ScheduleItemStub(REF_TIME.plusMinutes(10), REF_TIME.plusMinutes(20));
        ScheduleItem longerItem = new ScheduleItemStub(REF_TIME.minusMinutes(10), REF_TIME.plusMinutes(40));

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(shorterItem)); // Contained in reference
        assertTrue(shorterItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(longerItem)); // Reference in this
        assertTrue(longerItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
    }

    @Test
    public void isOverlapping_containedBoundary_overlapping() {
        // Start at same time
        ScheduleItem shorterItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(20));
        ScheduleItem longerItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(60));

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(shorterItem)); // Contained in reference
        assertTrue(shorterItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(longerItem)); // Reference in this
        assertTrue(longerItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test

        // End at same time
        shorterItem = new ScheduleItemStub(REF_TIME.plusMinutes(10), REF_TIME.plusMinutes(30));
        longerItem = new ScheduleItemStub(REF_TIME.minusMinutes(10), REF_TIME.plusMinutes(30));

        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(shorterItem)); // Contained in reference
        assertTrue(shorterItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
        assertTrue(REF_SCHEDULE_ITEM.isOverlapping(longerItem)); // Reference in this
        assertTrue(longerItem.isOverlapping(REF_SCHEDULE_ITEM)); // Symmetric test
    }

    @Test
    public void compareTo() {
        ScheduleItem refScheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(2));
        ScheduleItem sameStart = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(10));
        ScheduleItem laterStart = new ScheduleItemStub(REF_TIME.plusMinutes(1), REF_TIME.plusMinutes(5));
        ScheduleItem earlierStart = new ScheduleItemStub(REF_TIME.minusMinutes(1), REF_TIME.plusMinutes(100));

        assertEquals(0, refScheduleItem.compareTo(sameStart));
        assertTrue(refScheduleItem.compareTo(earlierStart) > 0);
        assertTrue(refScheduleItem.compareTo(laterStart) < 0);
    }

    @Test
    public void equals() {
        ScheduleItem refScheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(2));
        ScheduleItem copyScheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(2));

        // same object -> returns true
        assertTrue(refScheduleItem.equals(refScheduleItem));

        // same values -> returns true
        assertTrue(refScheduleItem.equals(copyScheduleItem));

        // null -> returns false
        assertFalse(refScheduleItem.equals(null));

        // different type -> returns false
        assertFalse(refScheduleItem.equals(5));

        // different start time -> returns false
        assertFalse(refScheduleItem.equals(new ScheduleItemStub(REF_TIME.plusMinutes(1),
                REF_TIME.plusMinutes(2))));

        // different end time -> returns false
        assertFalse(refScheduleItem.equals(new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(3))));
    }

    @Test
    public void hashCodeTest() {
        ScheduleItem refScheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(2));
        ScheduleItem copyScheduleItem = new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(2));

        // Same variables
        assertTrue(refScheduleItem.hashCode() == copyScheduleItem.hashCode());
        assertTrue(refScheduleItem.hashCode() == refScheduleItem.hashCode());

        // different end time
        assertFalse(refScheduleItem.hashCode()
                == new ScheduleItemStub(REF_TIME, REF_TIME.plusMinutes(1)).hashCode());

        // different start time
        assertFalse(refScheduleItem.hashCode()
                == new ScheduleItemStub(REF_TIME.plusMinutes(1), REF_TIME.plusMinutes(2)).hashCode());
    }

    // Stub class for testing that does not add additional variables.
    private static class ScheduleItemStub extends ScheduleItem {
        ScheduleItemStub(LocalDateTime start, LocalDateTime end) {
            super(start, end);
        }

        ScheduleItemStub(TimeRange period) {
            super(period);
        }
    }
}
