package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // valid remarks
        assertTrue(Remark.isValidRemark("In love with sky diving"));
        assertTrue(Remark.isValidRemark("-")); // one character
        assertTrue(Remark.isValidRemark("")); // empty remark
        assertTrue(Remark.isValidRemark("Waterfall Enthusiast who loves to play "
                + "water sports and loves car race")); // long remark exactly 70 characters long
        assertFalse(Remark.isValidRemark("Waterfall Enthusiast who also loves to play water sports, "
                + "board games, watch movies and loves gambling")); // long remark above 70 characters
    }
}
