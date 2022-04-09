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
        checkIfResultValid(result, command2);

        // Second command
        InputHistoryResult result2 = userInputHistory.getPreviousInput();
        checkIfResultValid(result2, command);

        // Empty
        InputHistoryResult result3 = userInputHistory.getPreviousInput();
        checkIfResultNoChange(result3);
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
        checkIfResultValid(result, command);
    }

    @Test
    public void testNextInput_noHistoryAtStart() {
        InputHistoryResult result = userInputHistory.getNextInput();
        checkIfResultNoChange(result);
    }

    @Test
    public void testNextInput_oneCommand() {
        String command = "test";
        userInputHistory.addToHistory(command);

        InputHistoryResult result = userInputHistory.getPreviousInput();
        checkIfResultValid(result, command);

        InputHistoryResult result2 = userInputHistory.getNextInput();
        checkIfResultNoChange(result2);
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
        checkIfResultValid(result, command3);

        InputHistoryResult result2 = userInputHistory.getPreviousInput();
        checkIfResultValid(result2, command2);

        InputHistoryResult result3 = userInputHistory.getPreviousInput();
        checkIfResultValid(result3, command);

        // Scroll to end
        InputHistoryResult result4 = userInputHistory.getNextInput();
        checkIfResultValid(result4, command2);

        InputHistoryResult result5 = userInputHistory.getNextInput();
        checkIfResultValid(result5, command3);

        InputHistoryResult result6 = userInputHistory.getNextInput();
        checkIfResultNoChange(result6);
    }

    /**
     * Check if the provided {@code InputHistoryResult} has the correct command returned.
     *
     * @param result The {@code InputHistoryResult} to check.
     * @param correctResult The correct command that has been saved to the history.
     */
    private void checkIfResultValid(InputHistoryResult result, String correctResult) {
        assertTrue(result.isChanged());
        UserInputString resultString = result.getUserInputString();
        assertEquals(correctResult, resultString.toString());
    }

    /**
     * Check if the provided {@code InputHistoryResult} returns no change.
     *
     * @param result The {@code InputHistoryResult} to check.
     */
    private void checkIfResultNoChange(InputHistoryResult result) {
        assertFalse(result.isChanged());
    }
}
