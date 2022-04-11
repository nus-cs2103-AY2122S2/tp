package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Amount(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidAmount1 = ".";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount1));

        String invalidAmount2 = ".111";
        assertThrows(IllegalArgumentException.class, () -> new Amount(invalidAmount2));
    }

    @Test
    public void isValidAmount() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid Amounts
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount(" ")); // spaces only
        assertFalse(Amount.isValidAmount("111.111")); // 3 d.p
        assertFalse(Amount.isValidAmount(".111")); // 3 d.p
        assertFalse(Amount.isValidAmount(".")); // decimal only
        assertFalse(Amount.isValidAmount("111 111.1")); // space division
        assertFalse(Amount.isValidAmount("111 111.111")); // more than 2 decimal place
        assertFalse(Amount.isValidAmount("11,11111")); // not multiple of 3 comma spacing
        assertFalse(Amount.isValidAmount("1111,111")); // comma spacing
        assertFalse(Amount.isValidAmount("1,111111")); // comma spacing

        // valid Amounts
        assertTrue(Amount.isValidAmount("111.1")); // single digit after decimal
        assertTrue(Amount.isValidAmount("0")); // Zero
        assertTrue(Amount.isValidAmount("0.0")); // Zero with decimal
        assertTrue(Amount.isValidAmount("22")); // normal integer
        assertTrue(Amount.isValidAmount("2.55")); // double digits after decimal
        assertTrue(Amount.isValidAmount("0.55")); // double digits after decimal with leading 0
        assertTrue(Amount.isValidAmount(".55")); // double digits after decimal without any whole number
        assertTrue(Amount.isValidAmount(".5")); // single digits after decimal without any whole number
        assertTrue(Amount.isValidAmount("22")); // normal integer
        assertTrue(Amount.isValidAmount("1,111,111")); // comma spacing
        assertTrue(Amount.isValidAmount("1,111,111.11")); // comma spacing with decimals
    }
}
