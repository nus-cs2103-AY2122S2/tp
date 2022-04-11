package seedu.address.model.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.lab.LabMark.MARKS_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.lab.exceptions.IllegalLabStateException;

public class LabMarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LabMark(null));
    }

    @Test
    public void constructor_invalidLabMark_throwsIllegalArgumentException() {
        String invalidMark = "A";
        assertThrows(IllegalArgumentException.class, () -> new LabMark(invalidMark));
    }

    @Test
    public void isValidLabMark() {
        // null lab score
        assertThrows(NullPointerException.class, () -> LabMark.isValidLabMark(null));

        // blank lab score
        assertFalse(LabMark.isValidLabMark("")); // empty string
        assertFalse(LabMark.isValidLabMark(" ")); // spaces only

        // invalid lab
        assertFalse(LabMark.isValidLabMark("1a")); // invalid lab mark
        assertFalse(LabMark.isValidLabMark("-1")); // negative integer
        assertFalse(LabMark.isValidLabMark("101")); // integer greater than 100
        assertFalse(LabMark.isValidLabMark("0101")); // leading 0s ignored
        assertFalse(LabMark.isValidLabMark("123456789")); // integer greater than 100

        // valid lab
        assertTrue(LabMark.isValidLabMark("0")); // smallest possible lab mark
        assertTrue(LabMark.isValidLabMark("2")); // valid integer
        assertTrue(LabMark.isValidLabMark("01")); // leading 0s ignored
        assertTrue(LabMark.isValidLabMark("100")); // largest possible lab mark
    }

    @Test
    public void toString_noMarkInitialized_returnsUnknown() {
        LabMark marks = new LabMark();
        assertEquals(marks.toString(), LabMark.MARKS_UNKNOWN);
    }

    @Test
    public void toString_markInitialized_returnsMark() {
        LabMark marks = new LabMark("10");
        assertEquals(marks.toString(), String.valueOf(10));
    }

    @Test
    public void equals_sameMark_success() {
        LabMark marks1 = new LabMark("1");
        LabMark marks2 = new LabMark("1");
        assertEquals(marks1, marks2);
    }

    @Test
    public void equals_differentMark_failure() {
        LabMark marks1 = new LabMark("1");
        LabMark marks2 = new LabMark("2");
        assertNotEquals(marks1, marks2);
    }

    @Test
    public void equals_oneMarkUninitialized_failure() {
        LabMark marks1 = new LabMark("1");
        LabMark marks2 = new LabMark();
        assertNotEquals(marks1, marks2);
    }

    @Test
    public void describe_success() {
        LabMark marks1 = new LabMark("1");
        assertEquals(String.format(MARKS_DESCRIPTION, "1"), marks1.describe());
    }

    @Test
    public void describe_illegalLabMark_throwsIllegalLabStateException() {
        LabMark illegalLabMark = new LabMark();
        assertThrows(IllegalLabStateException.class, illegalLabMark::describe);
    }
}
