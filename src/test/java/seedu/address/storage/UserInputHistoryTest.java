package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserInputHistoryTest {

    @Test
    public void testUserInputs() {
        UserInputHistory inputHistory = new UserInputHistory();
        inputHistory.up();
        assertEquals("", inputHistory.get()); //currently, no input history
        inputHistory.add("input 1");
        inputHistory.add("input 2");
        inputHistory.add("input 3");
        inputHistory.up();
        assertEquals("input 3", inputHistory.get());
        inputHistory.up();
        assertEquals("input 2", inputHistory.get());
        inputHistory.down();
        assertEquals("input 3", inputHistory.get());
        inputHistory.down();
        assertEquals("", inputHistory.get()); //no more input history

    }
}
