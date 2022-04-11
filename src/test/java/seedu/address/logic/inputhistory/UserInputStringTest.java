package seedu.address.logic.inputhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserInputStringTest {
    @Test
    public void testConstructor() {
        UserInputString testString = new UserInputString("test");
        assertEquals("test", testString.toString());
    }

    @Test
    public void testConstructorNull() {
        assertThrows(NullPointerException.class, () -> new UserInputString(null));
    }

    @Test
    public void testIsEmpty() {
        UserInputString testString = new UserInputString("");
        assertTrue(testString.isEmpty());
    }
}
