package seedu.address.model.lesson;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
}