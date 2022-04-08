package seedu.contax.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class StartDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StartDateTime(null));
    }

    @Test
    public void stringConversion() {
        LocalDateTime referenceTime = LocalDateTime.parse("2020-10-30T12:34:56");
        DateTimeFormatter formatter = StartDateTime.DATETIME_FORMATTER;

        assertEquals(formatter.format(referenceTime), new StartDateTime(referenceTime).toString());
        assertEquals(formatter.format(LocalDateTime.MAX), new StartDateTime(LocalDateTime.MAX).toString());
        assertEquals(formatter.format(LocalDateTime.MIN), new StartDateTime(LocalDateTime.MIN).toString());
    }

    @Test
    public void objectEquality() {
        LocalDateTime referenceTime = LocalDateTime.parse("2020-10-30T12:34:56");
        LocalDateTime roundedReferenceTime = LocalDateTime.parse("2020-10-30T12:34:00");
        LocalDateTime differentSeconds = LocalDateTime.parse("2020-10-30T12:34:46");
        LocalDateTime differentTime = LocalDateTime.parse("2020-10-30T11:20:56");
        LocalDateTime differentDate = LocalDateTime.parse("2021-09-29T12:34:56");

        StartDateTime referenceObj = new StartDateTime(referenceTime);
        StartDateTime differentSecondsObj = new StartDateTime(differentSeconds);
        StartDateTime differentTimeObj = new StartDateTime(differentTime);
        StartDateTime differentDateObj = new StartDateTime(differentDate);

        assertTrue(referenceObj.equals(referenceObj));
        assertTrue(referenceObj.equals(new StartDateTime(referenceTime)));
        assertTrue(referenceObj.equals(differentSecondsObj));
        assertEquals(roundedReferenceTime, referenceObj.value);

        assertFalse(referenceObj.equals(null)); // Null
        assertFalse(referenceObj.equals("some string")); // Different Type
        assertFalse(referenceObj.equals(differentTimeObj)); // Different Time
        assertFalse(referenceObj.equals(differentDateObj)); // Different Date
    }

    @Test
    public void hashCodeEquality() {
        LocalDateTime referenceTime = LocalDateTime.parse("2020-10-30T12:34:56");
        LocalDateTime differentDate = LocalDateTime.parse("2020-10-29T12:34:56");
        StartDateTime reference = new StartDateTime(referenceTime);

        assertEquals(reference.hashCode(), new StartDateTime(referenceTime).hashCode());
        assertNotEquals(reference.hashCode(), new StartDateTime(differentDate).hashCode());
    }

    @Test
    public void comparisonTest() {
        StartDateTime refTime = new StartDateTime(LocalDateTime.parse("2020-10-30T12:34:00"));
        StartDateTime timeBefore = new StartDateTime(LocalDateTime.parse("2020-10-30T12:33:00"));
        StartDateTime dateBefore = new StartDateTime(LocalDateTime.parse("2020-10-29T12:34:00"));
        StartDateTime timeAfter = new StartDateTime(LocalDateTime.parse("2020-10-30T12:35:00"));
        StartDateTime dateAfter = new StartDateTime(LocalDateTime.parse("2020-11-01T12:34:00"));
        StartDateTime refTimeDifferentSeconds = new StartDateTime(LocalDateTime.parse("2020-10-30T12:34:54"));

        assertEquals(0, refTime.compareTo(refTime));
        assertEquals(0, refTime.compareTo(refTimeDifferentSeconds));

        assertTrue(refTime.compareTo(timeBefore) > 0); // Different Time
        assertTrue(refTime.compareTo(dateBefore) > 0); // Different Date

        assertTrue(refTime.compareTo(timeAfter) < 0); // Different Time
        assertTrue(refTime.compareTo(dateAfter) < 0); // Different Date
    }
}
