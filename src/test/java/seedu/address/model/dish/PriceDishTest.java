package seedu.address.model.dish;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriceDishTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PriceDish(null));
    }

    @Test
    public void constructor_invalidPrice_throwsIllegalArgumentException() {
        String invalidPrice = "";
        assertThrows(IllegalArgumentException.class, () -> new PriceDish(invalidPrice));
    }

    @Test
    public void isValidPrice() {
        // null Price number
        assertThrows(NullPointerException.class, () -> PriceDish.isValidPrice(null));

        // invalid Price numbers
        assertFalse(PriceDish.isValidPrice("")); // empty string
        assertFalse(PriceDish.isValidPrice(" ")); // spaces only
        assertFalse(PriceDish.isValidPrice("91")); // no 2 d.p.
        assertFalse(PriceDish.isValidPrice("25.0")); // no 2 d.p.
        assertFalse(PriceDish.isValidPrice("price")); // non-numeric
        assertFalse(PriceDish.isValidPrice("9011p041")); // alphabets within digits
        assertFalse(PriceDish.isValidPrice("-1.00")); // symbols with valid digits
        assertFalse(PriceDish.isValidPrice("9312 1534")); // spaces within digits
        assertFalse(PriceDish.isValidPrice("100000.00")); // above 99999.99

        // valid Price numbers
        assertTrue(PriceDish.isValidPrice("0.00")); // edge case
        assertTrue(PriceDish.isValidPrice("99999.99")); // edge case
        assertTrue(PriceDish.isValidPrice("20.00")); // typical case
    }
}
