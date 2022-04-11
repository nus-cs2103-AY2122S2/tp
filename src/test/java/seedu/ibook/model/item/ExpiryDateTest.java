package seedu.ibook.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ibook.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class ExpiryDateTest {
    @Test
    void create_correctFormat_success() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("en"));
        LocalDate target = LocalDate.parse("7 Feb 2022", format);

        assertEquals(new ExpiryDate("7 Feb 2022").expiryDate, target);
        assertEquals(new ExpiryDate("07 Feb 2022").expiryDate, target);
        assertEquals(new ExpiryDate("2022-02-07").expiryDate, target);
    }

    @Test
    void create_invalidFormat_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("07-02-2022"));

        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("07/02/2022"));
    }

    /**
     * Dates are of valid format but invalid.
     * e.g. 30 Feb 2022, 2022-04-31, etc.
     */
    @Test
    void create_invalidDate_throwsIllegalArgumentException() {
        // Invalid day for February
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("30 Feb 2022"));
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("2022-02-30"));

        // Invalid day for months with 30 days only
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("31 Apr 2022"));
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("2022-04-31"));

        // Invalid 29th of February in an ordinary year
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("29 Feb 2022"));
        assertThrows(IllegalArgumentException.class, () -> new ExpiryDate("2022-02-29"));
    }
}
