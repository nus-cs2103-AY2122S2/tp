package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PremiumTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Premium(null));
    }

    @Test
    public void constructor_invalidPremium_throwsIllegalArgumentException() {
        String invalidPremium = "";
        assertThrows(IllegalArgumentException.class, () -> new Premium(invalidPremium));
    }

    @Test
    public void isValidPremium() {
        // null premium
        assertThrows(NullPointerException.class, () -> Premium.isValidPremium(null));

        // invalid premiums
        assertFalse(Premium.isValidPremium("")); // empty string
        assertFalse(Premium.isValidPremium(" ")); // spaces only
        assertFalse(Premium.isValidPremium("premium")); // non-numeric
        assertFalse(Premium.isValidPremium("12a3bc4d")); // alphabets within digits
        assertFalse(Premium.isValidPremium("1234 5678")); // spaces within digits
        assertFalse(Premium.isValidPremium("$100")); // special character within digits

        // valid premiums
        assertTrue(Premium.isValidPremium("911"));
        assertTrue(Premium.isValidPremium("93121534"));
        assertTrue(Premium.isValidPremium("124293842033123"));
    }
}
