package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassGroupIdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassGroupId(null));
    }

    @Test
    public void constructor_invalidClassGroupId_throwsIllegalArgumentException() {
        String invalidClassGroupId = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassGroupId(invalidClassGroupId));
    }

    @Test
    public void isValidClassGroupId() {
        // null class group ID
        assertThrows(NullPointerException.class, () -> ClassGroupId.isValidClassGroupId(null));

        // invalid class group IDs
        assertFalse(ClassGroupId.isValidClassGroupId("")); // empty string
        assertFalse(ClassGroupId.isValidClassGroupId(" ")); // spaces only
        assertFalse(ClassGroupId.isValidClassGroupId("BB23")); // more than 3 characters
        assertFalse(ClassGroupId.isValidClassGroupId("T0!")); // contains symbol
        assertFalse(ClassGroupId.isValidClassGroupId("01")); // starts with a non-alphabetic character

        // valid class group IDs
        assertTrue(ClassGroupId.isValidClassGroupId("B01"));
        assertTrue(ClassGroupId.isValidClassGroupId("T13"));
    }
}
