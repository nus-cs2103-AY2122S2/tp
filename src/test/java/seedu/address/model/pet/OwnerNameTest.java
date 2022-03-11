package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OwnerNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OwnerName(null));
    }

    @Test
    public void constructor_invalidOwnerName_throwsIllegalArgumentException() {
        String invalidOwnerName = "";
        assertThrows(IllegalArgumentException.class, () -> new OwnerName(invalidOwnerName));
    }

    @Test
    public void isValidOwnerName() {
        // null ownerName
        assertThrows(NullPointerException.class, () -> OwnerName.isValidOwnerName(null));

        // invalid ownerName
        assertFalse(OwnerName.isValidOwnerName("")); // empty string
        assertFalse(OwnerName.isValidOwnerName(" ")); // spaces only
        assertFalse(OwnerName.isValidOwnerName("^")); // only non-alphanumeric characters
        assertFalse(OwnerName.isValidOwnerName("peter*")); // contains non-alphanumeric characters

        // valid ownerName
        assertTrue(OwnerName.isValidOwnerName("peter jack")); // alphabets only
        assertTrue(OwnerName.isValidOwnerName("12345")); // numbers only
        assertTrue(OwnerName.isValidOwnerName("peter the 2nd")); // alphanumeric characters
        assertTrue(OwnerName.isValidOwnerName("Capital Tan")); // with capital letters
        assertTrue(OwnerName.isValidOwnerName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
