package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for MatriculationNumber class and its methods.
 */
public class MatriculationNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatriculationNumber(null));
    }

    @Test
    public void constructor_invalidMatriculationNumber_throwsIllegalArgumentException() {
        String invalidMatriculationNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new MatriculationNumber(invalidMatriculationNumber));
    }

    @Test
    public void isValidMatriculationNumber() {
        // null matriculation number
        assertThrows(NullPointerException.class, () -> MatriculationNumber.isValidMatriculationNumber(null));

        // invalid matriculation number
        assertFalse(MatriculationNumber.isValidMatriculationNumber("")); // empty string
        assertFalse(MatriculationNumber.isValidMatriculationNumber(" ")); // spaces only
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A123456D")); // 6 numbers
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A12345678")); // 8 numbers
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A12345678$")); // format is of AXXXXXXX{Alpha}
        assertFalse(MatriculationNumber.isValidMatriculationNumber("B12345678G")); // first character
        // should be an a/A

        // valid matriculation number
        assertTrue(MatriculationNumber.isValidMatriculationNumber("a1234567g")); // all lowercase, correct number
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0000000H")); // all uppercase, correct number
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0000000d")); // all uppercase,
        // lowercase alphanumeric, correct number
        assertTrue(MatriculationNumber.isValidMatriculationNumber("a0000000D")); // all uppercase,
        // lowercase first character, correct number
    }
}
