package seedu.address.logic.inputhistory;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InputHistoryResultTest {
    // EP: Input normal String
    @Test
    public void testConstructor() {
        String testString = "test";
        UserInputStringStub stringStub = new UserInputStringStub(testString);
        InputHistoryResult newResult = new InputHistoryResult(stringStub);
        assertTrue(newResult.isChanged());
        UserInputString resultString = newResult.getUserInputString();
        assertEquals(testString, resultString.toString());
    }

    // EP: Input nothing
    @Test
    public void testConstructor_noChange() {
        InputHistoryResult newResult = new InputHistoryResult();
        assertFalse(newResult.isChanged());
    }

    // EP: Input null
    @Test
    public void testConstructor_null() {
        assertThrows(NullPointerException.class, () -> new InputHistoryResult(null));
    }

    private static class UserInputStringStub extends UserInputString {
        private final String userInputTest;

        public UserInputStringStub(String userInput) {
            super("");
            requireNonNull(userInput);
            this.userInputTest = userInput;
        }

        @Override
        public String toString() {
            return userInputTest;
        }
    }
}
