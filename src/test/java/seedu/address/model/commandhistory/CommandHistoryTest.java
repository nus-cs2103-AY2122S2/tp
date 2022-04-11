package seedu.address.model.commandhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    private CommandHistory commandHistory = new CommandHistory();
    private final CommandHistoryEntry sentinel = CommandHistoryEntry.getEmptyHistory();

    @BeforeEach
    void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    void cacheCommand_success() {
        CommandHistoryEntry expectedEntry = new CommandHistoryEntry("entry");

        commandHistory.cacheCommand("entry");
        CommandHistoryEntry actualEntry = commandHistory.retrieveCommand(1);
        assertEquals(expectedEntry, actualEntry);
    }

    @Test
    void cacheCommand_nullCommandText_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandHistory.cacheCommand(null));
    }

    @Test
    void cacheCommand_emptyCommandText_historyUnchanged() {
        CommandHistory emptyHistory = new CommandHistory();

        // commandHistory should be empty before and after the cacheCommand method
        commandHistory.cacheCommand("");
        assertEquals(emptyHistory, commandHistory);
    }

    @Test
    void retrieveCommand_success() {
        String[] commandTexts = { "command 1", "command 2", "command 3" };
        List<CommandHistoryEntry> entries = new ArrayList<>();
        for (String text : commandTexts) {
            entries.add(new CommandHistoryEntry(text));
            commandHistory.cacheCommand(text);
        }

        // 1 step back in history, last to be added
        assertEquals(entries.get(2), commandHistory.retrieveCommand(1));

        // 2 steps back in history, second last to be added
        assertEquals(entries.get(1), commandHistory.retrieveCommand(2));

        // 3 steps back in history, third last to be added
        assertEquals(entries.get(0), commandHistory.retrieveCommand(3));
    }

    @Test
    void retrieveCommand_indexZero_returnsSentinel() {
        assertEquals(sentinel, commandHistory.retrieveCommand(0));
    }

    @Test
    void retrieveCommand_negativeIndex_returnsSentinel() {
        assertEquals(sentinel, commandHistory.retrieveCommand(-1));
    }

    @Test
    void retrieveCommand_olderThanHistory_returnsSentinel() {
        String[] commandTexts = { "command 1", "command 2", "command 3" };
        for (String text : commandTexts) {
            commandHistory.cacheCommand(text);
        }

        assertEquals(sentinel, commandHistory.retrieveCommand(4));
    }

    @Test
    void equals() {
        String[] commandTexts = { "command 1", "command 2", "command 3" };
        for (String text : commandTexts) {
            commandHistory.cacheCommand(text);
        }

        // same object -> returns true
        assertTrue(commandHistory.equals(commandHistory));

        // different object, same values -> returns true
        CommandHistory copyCommandHistory = new CommandHistory();
        for (String text : commandTexts) {
            copyCommandHistory.cacheCommand(text);
        }
        assertTrue(commandHistory.equals(copyCommandHistory));

        // different values -> returns false
        assertFalse(commandHistory.equals(new CommandHistory()));

    }
}
