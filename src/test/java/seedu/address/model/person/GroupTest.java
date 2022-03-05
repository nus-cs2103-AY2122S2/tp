package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void isValidGroup() {
        // null address
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // valid addresses
        assertTrue(Group.isValidGroup("T02"));
        assertTrue(Group.isValidGroup("-")); // one character
    }
}
