package seedu.address.model.assessment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FullMarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FullMark(null));
    }

    @Test
    public void constructor_invalidFullMark_throwsIllegalArgumentException() {
        String invalidFullMark = "";
        assertThrows(IllegalArgumentException.class, () -> new FullMark(invalidFullMark));
    }

    @Test
    public void isValidFullMark() {
        // null full mark
        assertThrows(NullPointerException.class, () -> FullMark.isValidFullMark(null));

        // invalid full marks
        assertFalse(FullMark.isValidFullMark("")); // empty string
        assertFalse(FullMark.isValidFullMark("  ")); // spaces only
        assertFalse(FullMark.isValidFullMark("abc")); // non-numeric
        assertFalse(FullMark.isValidFullMark("1a00")); // alphabet within input
        assertFalse(FullMark.isValidFullMark("-10")); // negative or non-numeric
        assertFalse(FullMark.isValidFullMark("10.5")); // decimal or non-numeric
        assertFalse(FullMark.isValidFullMark("0")); // outside allowed range
        assertFalse(FullMark.isValidFullMark("1001")); // outside allowed range

        // valid full marks
        assertTrue(FullMark.isValidFullMark("1")); // lower bound
        assertTrue(FullMark.isValidFullMark("1000")); // upper bound
        assertTrue(FullMark.isValidFullMark("100")); // within range
    }
}
