package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentNumber(null));
    }

    @Test
    public void constructor_invalidStudentNumber_throwsIllegalArgumentException() {
        String invalidStudentNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentNumber(invalidStudentNumber));
    }

    @Test
    public void isValidStudentNumber() {
        // null student number
        assertThrows(NullPointerException.class, () -> StudentNumber.isValidStudentNumber(null));

        // invalid student numbers
        assertFalse(StudentNumber.isValidStudentNumber("")); // empty string
        assertFalse(StudentNumber.isValidStudentNumber(" ")); // spaces only
        assertFalse(StudentNumber.isValidStudentNumber("A91B")); // not the desired length
        assertFalse(StudentNumber.isValidStudentNumber("AgenZ")); // non-numeric
        assertFalse(StudentNumber.isValidStudentNumber("A9011p041B")); // alphabets within digits
        assertFalse(StudentNumber.isValidStudentNumber("A9312 534B")); // spaces within digits
        assertFalse(StudentNumber.isValidStudentNumber("B1234567X")); // begins with capital letter that is not A

        // valid student numbers
        assertTrue(StudentNumber.isValidStudentNumber("A0225771K")); // exact format as desired
        assertTrue(StudentNumber.isValidStudentNumber("A9312153P")); // another valid number
        assertTrue(StudentNumber.isValidStudentNumber("a1234567b")); // lowercase letters are allowed
        assertTrue(StudentNumber.isValidStudentNumber("a1234567B")); // lowercase beginning A letter allowed
    }
}
