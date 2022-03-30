package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ScheduleNameTest {
    @Test
    public void isValidScheduleName() {
        // null name
        assertThrows(NullPointerException.class, () -> ScheduleName.isValidScheduleName(null));

        // invalid name
        assertFalse(ScheduleName.isValidScheduleName("")); // empty string
        assertFalse(ScheduleName.isValidScheduleName(" ")); // spaces only
        assertFalse(ScheduleName.isValidScheduleName("^")); // only non-alphanumeric characters
        assertFalse(ScheduleName.isValidScheduleName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ScheduleName.isValidScheduleName("peter jack")); // alphabets only
        assertTrue(ScheduleName.isValidScheduleName("12345")); // numbers only
        assertTrue(ScheduleName.isValidScheduleName("peter the 2nd")); // alphanumeric characters
        assertTrue(ScheduleName.isValidScheduleName("Capital Tan")); // with capital letters
        assertTrue(ScheduleName.isValidScheduleName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
