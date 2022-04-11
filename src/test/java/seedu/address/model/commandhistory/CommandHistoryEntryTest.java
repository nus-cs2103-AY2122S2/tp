package seedu.address.model.commandhistory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.commandhistory.CommandHistoryEntry.getEmptyHistory;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.commandhistory.exceptions.HistoryDoesNotExistException;

class CommandHistoryEntryTest {

    private static final CommandHistoryEntry sentinel = getEmptyHistory();

    @Test
    void constructor_nullText_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CommandHistoryEntry(null));
    }

    @Test
    void exists_commandHistoryEntry_returnsTrue() {
        CommandHistoryEntry commandHistoryEntry = new CommandHistoryEntry("random command");
        assertTrue(commandHistoryEntry.exists());
    }

    @Test
    void exists_commandHistorySentinel_returnsFalse() {
        assertFalse(sentinel.exists());
    }

    @Test
    void getCommandText_commandHistoryEntry_success() {
        String commandText = "list";
        CommandHistoryEntry commandHistoryEntry = new CommandHistoryEntry(commandText);
        assertEquals(commandText, commandHistoryEntry.getCommandText());
    }

    @Test
    void getCommandText_commandHistorySentinel_throwsHistoryDoesNotExistException() {
        assertThrows(HistoryDoesNotExistException.class, sentinel::getCommandText);
    }

    @Test
    void equals_commandHistoryEntry() {
        String commandText = "list";
        CommandHistoryEntry commandHistoryEntry = new CommandHistoryEntry(commandText);

        // same object -> return true
        assertTrue(commandHistoryEntry.equals(commandHistoryEntry));

        // different object, same value -> return true
        CommandHistoryEntry historyCopy = new CommandHistoryEntry(commandText);
        assertTrue(commandHistoryEntry.equals(historyCopy));

        // different values -> return false
        assertFalse(commandHistoryEntry.equals(new CommandHistoryEntry("addimg 1")));

        // sentinel and entry -> return false
        assertFalse(commandHistoryEntry.equals(sentinel));

        // sentinel and sentinel -> return true
        assertTrue(sentinel.equals(getEmptyHistory()));
    }
}
