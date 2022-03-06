package seedu.address.model.lesson;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

public class DateTimeSlotTest {
    LocalDateTime d = LocalDateTime.of(2022, 1, 20, 18, 0, 0);

    @Test
    public void constructor_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTimeSlot(null, 2));
    }

    @Test
    public void constructor_negativeHours_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new DateTimeSlot(d, -2));
    }

    @Test
    public void constructor_negativeMinutes_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new DateTimeSlot(d, 0, -5));
    }

    @Test
    public void constructor_zeroHours_and_zeroMinutes_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new DateTimeSlot(d, 0, 0));
    }

    @Test
    public void constructor_zeroHours_and_positiveMinutes_instantiatesSuccessfully() {
        assertNotNull(new DateTimeSlot(d, 0, 50));
    }

    @Test
    public void getDateString() {
        LocalDateTime d = LocalDateTime.of(2022, 1, 5, 17, 50, 0);
        DateTimeSlot dateTimeSlot = new DateTimeSlot(d, 1);

        String dateString = dateTimeSlot.getDateString();
        String expectedDateString = "Wednesday [5 January 2022]";

        assertEquals(dateString, expectedDateString);
    }

    @Test
    public void getTimeString() {
        LocalDateTime d = LocalDateTime.of(2022, 1, 5, 17, 50, 0);
        DateTimeSlot dateTimeSlot = new DateTimeSlot(d, 1);

        String timeString = dateTimeSlot.getTimeString();
        String expectedTimeString = "5:50 PM - 6:50 PM";

        assertEquals(timeString, expectedTimeString);
    }
}