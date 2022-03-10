package seedu.address.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StipendTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stipend(null));
    }

    @Test
    public void constructor_invalidStipend_throwsIllegalArgumentException() {
        String invalidStipend = "stipend";
        assertThrows(IllegalArgumentException.class, () -> new Stipend(invalidStipend));
    }

    @Test
    public void isValidStipend() {
        // null stipend
        assertThrows(NullPointerException.class, () -> Stipend.isValidStipend(null));

        // invalid stipends
        assertFalse(Stipend.isValidStipend(" ")); // spaces only
        assertFalse(Stipend.isValidStipend("22222222222")); // more than 10 numbers
        assertFalse(Stipend.isValidStipend("stipend")); // non-numeric
        assertFalse(Stipend.isValidStipend("22e22")); // alphabets within digits
        assertFalse(Stipend.isValidStipend("22 22")); // spaces within digits

        // valid stipends
        assertTrue(Stipend.isValidStipend("")); // no input
        assertTrue(Stipend.isValidStipend("1000"));
        assertTrue(Stipend.isValidStipend("1")); // one number
        assertTrue(Stipend.isValidStipend("100000000")); // ten numbers
    }
}
