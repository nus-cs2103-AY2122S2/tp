package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidName));
    }

    @Test
    public void isValidGroup() {
        // null group
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // invalid group
        assertFalse(Group.isValidGroup("")); // empty string
        assertFalse(Group.isValidGroup(" CS3243")); // spaces only

        // valid group
        assertTrue(Group.isValidGroup("T01"));
        assertTrue(Group.isValidGroup("S12"));
        assertTrue(Group.isValidGroup("W12"));
        assertTrue(Group.isValidGroup("T12"));
        assertTrue(Group.isValidGroup("T12"));
    }
}

