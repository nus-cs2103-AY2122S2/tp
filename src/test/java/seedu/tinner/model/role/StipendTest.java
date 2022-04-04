package seedu.tinner.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StipendTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stipend(null));
    }

    @Test
    public void constructor_invalidStipend_throwsIllegalArgumentException() {
        String invalidStipend = "invalid";
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
        assertFalse(Stipend.isValidStipend("22.22"));
        assertFalse(Stipend.isValidStipend("0"));
        assertFalse(Stipend.isValidStipend("000000"));

        // valid stipends
        assertTrue(Stipend.isValidStipend("")); // empty string
        assertTrue(Stipend.isValidStipend("1000"));
        assertTrue(Stipend.isValidStipend("1")); // one number
        assertTrue(Stipend.isValidStipend("100000000")); // ten numbers
        assertTrue(Stipend.isValidStipend("000000001")); // all zeros
    }

    @Test
    public void equals() {
        assertTrue(new Stipend("000001").toString().equals("1"));

        assertTrue(new Stipend("101").equals(new Stipend("000101")));

        assertFalse(new Stipend("101").equals(new Stipend("010")));
    }

}
