package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("12345")); // numbers only


        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void boundaryValues() {
        assertFalse(Name.isValidName("")); // empty string

        assertFalse(Name.isValidName(" 1 ")); // 1 number only
        assertFalse(Name.isValidName("99999999999999999999999999999999999999999999999999")); // 50 numbers
        assertFalse(Name.isValidName("999999999999999999999999999999999999999999999999990")); // 51 numbers

        assertTrue(Name.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX")); // 50 characters
        assertFalse(Name.isValidName("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXY")); // 51 characters
        assertFalse(Name.isValidName("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZa"
                + "slkjhdnaosjbfajkshbfasdsas123d")); // 80 characters, max is 50
    }
}
