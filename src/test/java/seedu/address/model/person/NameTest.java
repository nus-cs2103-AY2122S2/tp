package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LineupName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new LineupName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> LineupName.isValidLineupName(null));

        // invalid name
        assertFalse(LineupName.isValidLineupName("")); // empty string
        assertFalse(LineupName.isValidLineupName(" ")); // spaces only
        assertFalse(LineupName.isValidLineupName("^")); // only non-alphanumeric characters
        assertFalse(LineupName.isValidLineupName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(LineupName.isValidLineupName("peter jack")); // alphabets only
        assertTrue(LineupName.isValidLineupName("12345")); // numbers only
        assertTrue(LineupName.isValidLineupName("peter the 2nd")); // alphanumeric characters
        assertTrue(LineupName.isValidLineupName("Capital Tan")); // with capital letters
        assertTrue(LineupName.isValidLineupName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
