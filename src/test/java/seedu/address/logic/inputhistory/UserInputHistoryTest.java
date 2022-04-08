package seedu.address.logic.inputhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInputHistoryTest {
    private UserInputHistory userInputHistory;

    @BeforeEach
    public void setUp() {
        userInputHistory = new UserInputHistory();
    }

    // Tests previousInput as well
    @Test
    public void testAddToHistory_multipleInputs() {
        String command = "test";
        String command2 = "test2";
        userInputHistory.addToHistory(command);
        userInputHistory.addToHistory(command2);

        // First command
        InputHistoryResult result = userInputHistory.getPreviousInput();
        assertTrue(result.isChanged());
        UserInputString resultString = result.getUserInputString();
        assertEquals(command2, resultString.toString());

        // Second command
        InputHistoryResult result2 = userInputHistory.getPreviousInput();
        assertTrue(result2.isChanged());
        UserInputString resultString2 = result2.getUserInputString();
        assertEquals(command, resultString2.toString());

        // Empty
        InputHistoryResult result3 = userInputHistory.getPreviousInput();
        assertFalse(result3.isChanged());
    }

    @Test
    public void testAddToHistory_nullInput() {
        assertThrows(NullPointerException.class, () -> userInputHistory.addToHistory(null));
    }

    @Test
    public void testPreviousInput_oneCommand() {
        String command = "test";
        userInputHistory.addToHistory(command);

        InputHistoryResult result = userInputHistory.getPreviousInput();
        assertTrue(result.isChanged());
        UserInputString resultString = result.getUserInputString();
        assertEquals(command, resultString.toString());
    }

    @Test
    public void testNextInput_noHistoryAtStart() {
        InputHistoryResult result = userInputHistory.getNextInput();
        assertFalse(result.isChanged());
    }

    @Test
    public void testNextInput_oneCommand() {
        String command = "test";
        userInputHistory.addToHistory(command);

        InputHistoryResult result = userInputHistory.getPreviousInput();
        assertTrue(result.isChanged());
        UserInputString resultString = result.getUserInputString();
        assertEquals(command, resultString.toString());

        InputHistoryResult result2 = userInputHistory.getNextInput();
        assertFalse(result2.isChanged());
    }

    @Test
    public void testNextInput_multipleCommands() {
        // Creating multiple commands in history
        String command = "test";
        String command2 = "test2";
        String command3 = "test3";
        userInputHistory.addToHistory(command);
        userInputHistory.addToHistory(command2);
        userInputHistory.addToHistory(command3);

        // Scroll up 3 times
        InputHistoryResult result = userInputHistory.getPreviousInput();
        assertTrue(result.isChanged());
        UserInputString resultString = result.getUserInputString();
        assertEquals(command3, resultString.toString());

        InputHistoryResult result2 = userInputHistory.getPreviousInput();
        assertTrue(result2.isChanged());
        UserInputString resultString2 = result2.getUserInputString();
        assertEquals(command2, resultString2.toString());

        InputHistoryResult result3 = userInputHistory.getPreviousInput();
        assertTrue(result3.isChanged());
        UserInputString resultString3 = result3.getUserInputString();
        assertEquals(command, resultString3.toString());

        // Scroll to end
        InputHistoryResult result4 = userInputHistory.getNextInput();
        assertTrue(result4.isChanged());
        UserInputString resultString4 = result4.getUserInputString();
        assertEquals(command2, resultString4.toString());

        InputHistoryResult result5 = userInputHistory.getNextInput();
        assertTrue(result5.isChanged());
        UserInputString resultString5 = result5.getUserInputString();
        assertEquals(command3, resultString5.toString());

        InputHistoryResult result6 = userInputHistory.getNextInput();
        assertFalse(result6.isChanged());
    }
}
