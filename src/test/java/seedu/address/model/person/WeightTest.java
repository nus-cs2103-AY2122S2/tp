package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weight(null));
    }

    @Test
    public void constructor_invalidWeight_throwsIllegalArgumentException() {
        String invalidWeight = "";
        assertThrows(IllegalArgumentException.class, () -> new Weight(invalidWeight));
    }

    @Test
    public void isValidWeight() {
        // null weight
        assertThrows(NullPointerException.class, () -> Weight.isValidWeight(null));

        // invalid weight
        assertFalse(Weight.isValidWeight("")); // empty string
        assertFalse(Weight.isValidWeight(" ")); // spaces only
        assertFalse(Weight.isValidWeight("$%#")); // symbols
        assertFalse(Weight.isValidWeight("a24gsd")); // alpha numeric
        assertFalse(Weight.isValidWeight("-124")); // negative weight
        assertFalse(Weight.isValidWeight("201")); // weight greater than 200
        assertFalse(Weight.isValidWeight("0")); // weight lesser than 1
        assertFalse(Weight.isValidWeight("21.2")); // decimal weight

        // valid weight
        assertTrue(Weight.isValidWeight("112")); // between 1 and 200
        assertTrue(Weight.isValidWeight("200")); // inclusive 200
        assertTrue(Weight.isValidWeight("1")); // inclusive 1
    }
}
