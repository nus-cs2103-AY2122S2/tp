package seedu.address.model.medical;


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
        assertFalse(Weight.isValidWeight(" ")); // space only

        // valid weight
        assertTrue(Weight.isValidWeight("70 kg"));
    }
}
