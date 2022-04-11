package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WeightageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Weightage(null));
    }

    @Test
    public void constructor_invalidWeightage_throwsIllegalArgumentException() {
        String invalidWeightage = "";
        assertThrows(IllegalArgumentException.class, () -> new Weightage(invalidWeightage));
    }

    @Test
    public void isValidWeightage() {
        // null weightage
        assertThrows(NullPointerException.class, () -> Weightage.isValidWeightage(null));

        // invalid weightages
        assertFalse(Weightage.isValidWeightage("")); // empty string
        assertFalse(Weightage.isValidWeightage("  ")); // spaces only
        assertFalse(Weightage.isValidWeightage("abc")); // non-numeric
        assertFalse(Weightage.isValidWeightage("1a")); // alphabet within input
        assertFalse(Weightage.isValidWeightage("-12")); // negative or non-numeric
        assertFalse(Weightage.isValidWeightage("12.5")); // decimal or non-numeric
        assertFalse(Weightage.isValidWeightage("120")); // outside the allowed range

        // valid weightages
        assertTrue(Weightage.isValidWeightage("0")); // lower bound
        assertTrue(Weightage.isValidWeightage("100")); // upper bound
        assertTrue(Weightage.isValidWeightage("50")); // within range
    }
}
