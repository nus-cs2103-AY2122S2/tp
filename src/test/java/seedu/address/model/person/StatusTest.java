package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null Status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid Statuses
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("abcde")); // not eny of the 3 possible statuses

        // valid Statuses
        assertTrue(Status.isValidStatus("Positive"));
        assertTrue(Status.isValidStatus("Negative")); // one character
        assertTrue(Status.isValidStatus("Close-Contact")); // long Status
    }
}
