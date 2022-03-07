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
        LocalDateTime referenceNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StartDateTime.DATETIME_FORMAT);

        assertEquals("30-10-2020 12:34", new StartDateTime(referenceTime).toString());
        assertEquals(referenceNow.format(formatter), new StartDateTime(referenceNow).toString());
    }

    @Test
    public void objectEquality() {
        LocalDateTime referenceTime1 = LocalDateTime.parse("2020-10-30T12:34:56");
        LocalDateTime referenceTime2 = LocalDateTime.now();
        LocalDateTime referenceTime3 = LocalDateTime.parse("2020-10-30T12:34:46");
        LocalDateTime referenceTime4 = LocalDateTime.parse("2020-10-30T12:34:00");

        StartDateTime reference1 = new StartDateTime(referenceTime1);
        StartDateTime reference2 = new StartDateTime(referenceTime2);

        assertTrue(reference1.equals(new StartDateTime(referenceTime1)));
        assertTrue(reference2.equals(new StartDateTime(referenceTime2)));
        assertTrue(reference1.equals(new StartDateTime(referenceTime3))); // Only differ in seconds
        assertEquals(referenceTime4, reference1.value);
        assertTrue(reference1.equals(reference1));

        assertFalse(reference1.equals("some string"));
        assertFalse(reference1.equals(reference2));
        assertFalse(reference1.equals(new StartDateTime(LocalDateTime.parse("2021-09-29T10:10:10"))));
    }

    @Test
    public void hashCodeEquality() {
        LocalDateTime referenceTime1 = LocalDateTime.parse("2020-10-30T12:34:56");
        LocalDateTime referenceTime2 = LocalDateTime.parse("2020-10-29T12:34:56");
        StartDateTime reference = new StartDateTime(referenceTime1);

        assertEquals(reference.hashCode(), new StartDateTime(referenceTime1).hashCode());
        assertNotEquals(reference.hashCode(), new StartDateTime(referenceTime2).hashCode());
    }
}
