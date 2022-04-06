package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_invalidDateTime_throwsDateTimeException() {
        assertThrows(DateTimeException.class, () -> new DateTime(2022, 13, 12, 11, 11));
    }

    @Test
    public void isValidDateTime() {
        assertFalse(DateTime.isValidDateTime(2000, 10, 11, 9, 9));
        assertFalse(DateTime.isValidDateTime(2022, 2, 30, 9, 9));
        assertFalse(DateTime.isValidDateTime(2022, 4, 1, 9, 9));

        assertTrue(DateTime.isValidDateTime(2023, 10, 10, 12, 8));
    }
}
