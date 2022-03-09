package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Price(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new Price(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null price
        assertThrows(NullPointerException.class, () -> Price.isValidPrice(null));

        // blank price
        assertFalse(Price.isValidPrice("")); // empty string
        assertFalse(Price.isValidPrice(" ")); // spaces only

        // missing parts
        assertFalse(Price.isValidPrice("100")); // only value
        assertFalse(Price.isValidPrice("$")); // only $
        assertFalse(Price.isValidPrice("$ ")); // only $ and spaces

        // extra leading characters
        assertFalse(Price.isValidPrice("$$100")); // extra $
        assertFalse(Price.isValidPrice("SGD$100")); // extra SGD
        assertFalse(Price.isValidPrice(" $100")); // extra space

        // extra trailing characters
        assertFalse(Price.isValidPrice("$0.")); // extra .
        assertFalse(Price.isValidPrice("$100.0")); // extra .0
        assertFalse(Price.isValidPrice("$100.00")); // extra .00
        assertFalse(Price.isValidPrice("$100 ")); // extra space

        // invalid values
        assertFalse(Price.isValidPrice("$-100")); // negative value
        assertFalse(Price.isValidPrice("$100.00")); // extra .00
        assertFalse(Price.isValidPrice("$100 000")); // space in value
        assertFalse(Price.isValidPrice("$100.000")); // . in value
        assertFalse(Price.isValidPrice("$100,000")); // , in value

        // valid prices
        assertTrue(Price.isValidPrice("$100")); // 10^2
        assertTrue(Price.isValidPrice("$1000")); // 10^3
        assertTrue(Price.isValidPrice("$10000")); // 10^4
        assertTrue(Price.isValidPrice("$100000")); // 10^5
        assertTrue(Price.isValidPrice("$1000000")); // 10^6
        assertTrue(Price.isValidPrice("$10000000")); // 10^7
        assertTrue(Price.isValidPrice("$100000000")); // 10^8
        assertTrue(Price.isValidPrice("$1000000000")); // 10^9
    }
}
