package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    void isValidDateTime() {
        // null date time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid date times
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime("15-02-2020")); // date only
        assertFalse(DateTime.isValidDateTime("15/02/2020 1600")); // wrong format
        assertFalse(DateTime.isValidDateTime("15-02-2020 16:00")); // wrong format
        assertFalse(DateTime.isValidDateTime("15 Jan 2020 1200")); // wrong format
        assertFalse(DateTime.isValidDateTime("15-13-2020 1200")); // out of range
        assertFalse(DateTime.isValidDateTime("15-12-2020 2469")); // out of range

        // valid date times
        assertTrue(DateTime.isValidDateTime("15-02-2020 1600"));
        assertTrue(DateTime.isValidDateTime("5-8-2023 0859"));
    }

    @Test
    void compareDateTime() {
        DateTime first = new DateTime("14-04-2000 0000");
        DateTime second = new DateTime("14-04-2000 0001");
        DateTime third = new DateTime("15-04-2000 0000");
        DateTime fourth = new DateTime("14-05-2000 0000");
        DateTime fifth = new DateTime("14-05-2001 0000");
        DateTime equalsFirst = new DateTime("14-04-2000 0000");

        // Check ordering of datetimes
        assertTrue(first.compareTo(second) < 0);
        assertTrue(first.compareTo(third) < 0);
        assertTrue(first.compareTo(fourth) < 0);
        assertTrue(first.compareTo(fifth) < 0);
        assertTrue(first.compareTo(equalsFirst) == 0);
        assertTrue(second.compareTo(first) > 0);
        assertTrue(fifth.compareTo(fourth) > 0);
    }

    @Test
    void toInputFormat() {
        String validInput = "15-12-2020 2359";
        DateTime dateTime = new DateTime(validInput);
        assertEquals(dateTime, new DateTime(dateTime.toInputFormat()));
    }
}
