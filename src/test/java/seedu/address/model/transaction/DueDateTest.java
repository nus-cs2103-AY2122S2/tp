package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TransactionUtil.VALID_DUE_DATE_ONE;
import static seedu.address.testutil.TransactionUtil.VALID_DUE_DATE_TWO;

import org.junit.jupiter.api.Test;

public class DueDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DueDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        // empty string
        assertThrows(IllegalArgumentException.class, () -> new DueDate(""));
        // date not exist
        assertThrows(IllegalArgumentException.class, () -> new DueDate("2020-20-20"));
        // not in the format YYYY-MM-DD
        assertThrows(IllegalArgumentException.class, () -> new DueDate("26 September 2020"));
        // Not a valid date
        assertThrows(IllegalArgumentException.class, () -> new DueDate("This is not a valid date"));
    }

    @Test
    public void isValid_null_throwsNullPointerException() {
        // null argument
        assertThrows(NullPointerException.class, () ->DueDate.isValid(null));
    }

    @Test
    public void isValid_invalidArgument_returnsFalse() {
        // invalid argument
        assertFalse(DueDate.isValid(""));
        assertFalse(DueDate.isValid("2020-20-20"));
        assertFalse(DueDate.isValid("abcd"));
        assertFalse(DueDate.isValid("26 September 2020"));
        assertFalse(DueDate.isValid("2020/11/11"));
    }

    @Test
    public void isValid_validArgument_returnsTrue() {
        // valid argument
        assertTrue(DueDate.isValid("2020-11-11"));
        assertTrue(DueDate.isValid("2020-12-26"));
        assertTrue(DueDate.isValid("2020-01-01"));
    }

    @Test
    public void equalsTest() {
        DueDate firstDueDate = new DueDate(VALID_DUE_DATE_ONE);
        DueDate secondDueDate = new DueDate(VALID_DUE_DATE_TWO);
        DueDate firstDueDateCopy = new DueDate(firstDueDate.getValue());

        // compare with itself -> returns true
        assertTrue(firstDueDate.equals(firstDueDate));

        // compare with object with same constructor args
        assertTrue(firstDueDate.equals(firstDueDateCopy));

        //compare with different date
        assertFalse(firstDueDate.equals(secondDueDate));

        //compare with different object
        assertFalse(firstDueDate.equals("2021-11-11"));
    }
}
