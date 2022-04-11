package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Contains unit tests for {@code Address}.
 */
public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AcademicMajor(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new AcademicMajor(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> AcademicMajor.isValidAcademicMajor(null));

        // invalid addresses -> returns false
        assertFalse(AcademicMajor.isValidAcademicMajor("")); // empty string
        assertFalse(AcademicMajor.isValidAcademicMajor(" ")); // spaces only

        // valid addresses -> returns true
        assertTrue(AcademicMajor.isValidAcademicMajor("Computer Science"));
        assertTrue(AcademicMajor.isValidAcademicMajor("-")); // one character
        assertTrue(AcademicMajor.isValidAcademicMajor("Computer Science and Economics DDP")); // long major
    }
}
