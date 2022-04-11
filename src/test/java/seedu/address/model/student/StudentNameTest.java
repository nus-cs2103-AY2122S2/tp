package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentNameTest {

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
    public void isValidStudentName() {
        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWX")); // 50 characters
        assertTrue(Name.isValidName("999999999999999999999ABC99999999999999999999999999")); // 50 alphanumeric
        assertTrue(Name.isValidName("999999999999999999999ABC9"
                + " 999999999999999999999999")); // 50 alphanumeric w space

    }

    @Test
    public void isInvalidStudentName() {
        assertThrows(NullPointerException.class, () -> Name.isValidName(null)); // null student name
        assertFalse(Name.isValidName("")); // empty student name
        assertFalse(Name.isValidName(" ")); // whitespaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName("12345")); // numbers only


        assertFalse(Name.isValidName(" 1 ")); // 1 number only w whitespace
        assertFalse(Name.isValidName("1234")); // only numbers
        assertFalse(Name.isValidName("99999999999999999999999999999999999999999999999999")); // 50 numbers
        assertFalse(Name.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWXY")); // 51 alphanumeric
        assertFalse(Name.isValidName("9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWXY"
                + "9bcdefghijklmnopqrstuvwxyzA1CDEFGHIJKLMNO3QRSTUVWXY")); // 102 alphanumeric

    }
}
