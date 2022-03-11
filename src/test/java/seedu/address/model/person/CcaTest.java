package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CcaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Cca(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidCca = " ";
        assertThrows(IllegalArgumentException.class, () -> new Cca(invalidCca));
    }

    @Test
    public void isValidTagName() {
        // null cca
        assertThrows(NullPointerException.class, () -> Cca.isValidTagName(null));

        // invalid cca
        assertFalse(Cca.isValidTagName("")); // empty string
        assertFalse(Cca.isValidTagName(" ")); // spaces only
        assertFalse(Cca.isValidTagName("^")); // only non-alphanumeric characters
        assertFalse(Cca.isValidTagName("bouldering*")); // contains non-alphanumeric characters

        // valid cca
        assertTrue(Cca.isValidTagName("bouldering")); // alphabets only
        assertTrue(Cca.isValidTagName("12345")); // numbers only
        assertTrue(Cca.isValidTagName("bould3ring")); // alphanumeric characters
        assertTrue(Cca.isValidTagName("Bouldering")); // with capital letters
        assertTrue(Cca.isValidTagName("NUS Bouldering and Climbing Team")); // long names
    }
}
