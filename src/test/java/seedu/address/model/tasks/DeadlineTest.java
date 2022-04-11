package seedu.address.model.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Deadline(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(AssertionError.class, () -> new Deadline(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Deadline.isValidDeadline(null));

        // invalid deadline
        assertFalse(Deadline.isValidDeadline("")); // empty string
        assertFalse(Deadline.isValidDeadline(" ")); // spaces only
        assertFalse(Deadline.isValidDeadline("^")); // only non-alphanumeric characters
        assertFalse(Deadline.isValidDeadline("homework*")); // contains non-alphanumeric characters
        assertFalse(Deadline.isValidDeadline("0000-01-01T00:00")); // invalid year
        assertFalse(Deadline.isValidDeadline("0001-00-01T00:00")); // invalid month (lower)
        assertFalse(Deadline.isValidDeadline("0001-13-01T00:00")); // invalid month (upper)
        assertFalse(Deadline.isValidDeadline("0001-01-00T00:00")); // invalid day (lower)
        assertFalse(Deadline.isValidDeadline("0001-01-32T00:00")); // invalid day (upper)
        assertFalse(Deadline.isValidDeadline("0022-02-29T00:00")); // invalid non-leap day (upper)
        assertFalse(Deadline.isValidDeadline("0001-01-32T24:00")); // invalid hour
        assertFalse(Deadline.isValidDeadline("0001-01-32T00:60")); // invalid minute

        // valid deadline
        assertTrue(Deadline.isValidDeadline("0001-01-01T00:00")); // minimum time allowed
        assertTrue(Deadline.isValidDeadline("9999-12-31T23:59")); // maximum time allowed
        assertTrue(Deadline.isValidDeadline("0020-02-29T00:00")); // valid leap day
        assertTrue(Deadline.isValidDeadline("0020-02-28T00:00")); // valid non-leap day
    }
}
