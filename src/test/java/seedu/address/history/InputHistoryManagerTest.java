package seedu.address.history;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InputHistoryManagerTest {

    @Test
    void storeInput() {
        InputHistoryManager historyManager = new InputHistoryManager();
        String testString = "test1";
        historyManager.storeInput(testString);
        assertEquals(testString, historyManager.getPreviousUserInput());
    }

    @Test
    void getPreviousUserInput() {
        InputHistoryManager historyManager = new InputHistoryManager();
        String testString = "test1";
        String testString2 = "test2";
        historyManager.storeInput(testString);
        historyManager.storeInput(testString2);
        assertEquals(testString2, historyManager.getPreviousUserInput());
        assertEquals(testString, historyManager.getPreviousUserInput());
        assertEquals(testString, historyManager.getPreviousUserInput());

        String testString3 = "test3";
        historyManager.storeInput(testString3);
        assertEquals(testString3, historyManager.getPreviousUserInput());
    }

    @Test
    void getNextUserInput() {
        InputHistoryManager historyManager = new InputHistoryManager();
        String testString = "test1";
        String testString2 = "test2";
        historyManager.storeInput(testString);
        historyManager.storeInput(testString2);
        historyManager.getPreviousUserInput();
        historyManager.getPreviousUserInput();
        assertEquals(testString2, historyManager.getNextUserInput());
        assertEquals(testString2, historyManager.getNextUserInput());

    }

    @Test
    void canGetNextInput() {
        InputHistoryManager historyManager = new InputHistoryManager();
        assertFalse(historyManager.canGetNextInput());

        String testString = "test1";
        String testString2 = "test2";
        historyManager.storeInput(testString);
        historyManager.storeInput(testString2);
        assertFalse(historyManager.canGetNextInput());

        historyManager.getPreviousUserInput();
        assertTrue(historyManager.canGetNextInput());
    }

    @Test
    void canGetPrevInput() {
        InputHistoryManager historyManager = new InputHistoryManager();
        assertFalse(historyManager.canGetPrevInput());

        String testString = "test1";
        String testString2 = "test2";
        historyManager.storeInput(testString);
        historyManager.storeInput(testString2);
        assertTrue(historyManager.canGetPrevInput());

        historyManager.getPreviousUserInput();
        assertTrue(historyManager.canGetPrevInput());

        historyManager.getPreviousUserInput();
        assertFalse(historyManager.canGetPrevInput());
    }
}
