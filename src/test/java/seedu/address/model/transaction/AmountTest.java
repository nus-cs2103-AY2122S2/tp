package seedu.address.model.transaction;

import com.sun.nio.sctp.IllegalReceiveException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

public class AmountTest {

    public static final String FIELD_NAME = "Amount";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Amount("-1"));
    }

    @Test
    public void isValid_null_returnsFalse() {
        // null argument
        assertFalse(Amount.isValid(null));
    }

    @Test
    public void isValid_invalidArgument_returnsFalse() {

        // invalid argument
        assertFalse(Amount.isValid("-1"));
        assertFalse(Amount.isValid("0"));
        assertFalse(Amount.isValid(""));
        assertFalse(Amount.isValid("abcd"));
        assertFalse(Amount.isValid("2.33e"));
        assertFalse(Amount.isValid("0x888"));
        assertFalse(Amount.isValid("167x"));
        assertFalse(Amount.isValid("2 + 3"));
    }

    @Test
    public void isValid_validArgument_returnsTrue() {
        // valid argument
        assertTrue(Amount.isValid("45"));
        assertTrue(Amount.isValid("2.33"));
        assertTrue(Amount.isValid("2.33f"));
        assertTrue(Amount.isValid("0.0000000000000000000000000000000000000001"));
        assertTrue(Amount.isValid("0.00123"));
        assertTrue(Amount.isValid("2."));
    }

    @Test
    public void toStringAmount() {
        assertEquals(FIELD_NAME + ": 123.0", new Amount("123").toString());
        assertEquals(FIELD_NAME + ": 0.123", new Amount("000.123").toString());
        assertEquals(FIELD_NAME + ": 1.0", new Amount("1.").toString());
        assertEquals(FIELD_NAME + ": 0.123", new Amount("0.123f").toString());
    }
}
