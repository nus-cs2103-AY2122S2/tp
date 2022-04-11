package seedu.address.logic.inputhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInputIndexTest {
    private UserInputIndex testIndex;

    @BeforeEach
    public void setUp() {
        testIndex = new UserInputIndex();
    }

    @Test
    public void testConstructor() {
        assertEquals(-1, testIndex.getCurrentIndex());
    }

    // Tests Increment as well
    @Test
    public void testReset() {
        testIndex.increment();
        assertEquals(0, testIndex.getCurrentIndex());
        assertTrue(testIndex.isInHistoryMode());
        testIndex.reset();
        assertEquals(-1, testIndex.getCurrentIndex());
    }

    @Test
    public void testDecrement() {
        testIndex.increment();
        assertEquals(0, testIndex.getCurrentIndex());
        testIndex.increment();
        assertEquals(1, testIndex.getCurrentIndex());
        testIndex.decrement();
        assertEquals(0, testIndex.getCurrentIndex());
        testIndex.decrement();
        assertEquals(-1, testIndex.getCurrentIndex());
    }

    @Test
    public void testEquals() {
        UserInputIndex otherIndex = new UserInputIndex();
        assertEquals(testIndex, otherIndex);
        testIndex.increment();
        otherIndex.increment();
        assertEquals(testIndex, otherIndex);
        assertEquals(testIndex, testIndex);
    }
}
