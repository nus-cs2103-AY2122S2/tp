package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    private final String normalRemark = "This is a remark.";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void equals() {
        Remark firstRemark = new Remark("");
        Remark secondRemark = new Remark(normalRemark);

        //different remark message -> returns false
        assertFalse(firstRemark.equals(secondRemark));

        //same remark message -> returns true
        Remark firstRemarkCopy = new Remark("");
        assertTrue(firstRemark.equals(firstRemarkCopy));

        //same object -> returns true
        assertTrue(firstRemark.equals(firstRemark));

        // different types -> returns false
        assertFalse(firstRemark.equals(1));

        // null -> returns false
        assertFalse(firstRemark.equals(null));
    }
}
