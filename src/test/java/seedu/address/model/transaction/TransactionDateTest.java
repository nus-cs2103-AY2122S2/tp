package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TransactionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TransactionDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        // empty string
        assertThrows(IllegalArgumentException.class, () -> new TransactionDate(""));
        // date not exist
        assertThrows(IllegalArgumentException.class, () -> new TransactionDate("2020-20-20"));
        // not in the format YYYY-MM-DD
        assertThrows(IllegalArgumentException.class, () -> new TransactionDate("26 September 2020"));
        // Not a valid date
        assertThrows(IllegalArgumentException.class, () -> new TransactionDate("This is not a valid date"));
    }

    @Test
    public void isValid_null_throwsNullPointerException() {
        // null argument
        assertThrows(NullPointerException.class, () ->TransactionDate.isValid(null));
    }

    @Test
    public void isValid_invalidArgument_returnsFalse() {
        // invalid argument
        assertFalse(TransactionDate.isValid(""));
        assertFalse(TransactionDate.isValid("2020-20-20"));
        assertFalse(TransactionDate.isValid("abcd"));
        assertFalse(TransactionDate.isValid("26 September 2020"));
        assertFalse(TransactionDate.isValid("2020/11/11"));
    }

    @Test
    public void isValid_validArgument_returnsTrue() {
        // valid argument
        assertTrue(TransactionDate.isValid("2020-11-11"));
        assertTrue(TransactionDate.isValid("2020-12-26"));
        assertTrue(TransactionDate.isValid("2020-01-01"));
    }
}
