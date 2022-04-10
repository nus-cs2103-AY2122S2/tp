package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class CommandHistoryTest {
    private CommandHistory commandHistory;

    @BeforeEach
    public void setUp() {
        commandHistory = new CommandHistory();
    }

    @Test
    public void constructor() {
        ArrayList<String> commands = new ArrayList<>();
        assertEquals(commands, commandHistory.getPreviousCommands());
    }

    @Test
    public void isEmpty_noCommandsInCommandHistory_returnsTrue() {
        assertTrue(commandHistory.isEmpty());
    }

    @Test
    public void isEmpty_hasCommandInCommandHistory_returnsFalse() {
        String testCommand = ListCommand.COMMAND_WORD;
        commandHistory.addToHistory(testCommand);
        assertFalse(commandHistory.isEmpty());
    }

    @Test
    public void getPreviousCommands_noCommandsInCommandHistory_returnsEmptyList() {
        ArrayList<String> emptyList = new ArrayList<>();
        assertEquals(emptyList, commandHistory.getPreviousCommands());
    }

    @Test
    public void getPreviousCommands_hasCommandInCommandHistory_returnsListOfCommands() {
        String testCommand = ListCommand.COMMAND_WORD;

        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add(testCommand);

        commandHistory.addToHistory(testCommand);
        assertEquals(expectedList, commandHistory.getPreviousCommands());
    }

    @Test
    public void popPreviousCommand_commandHistoryIsEmpty_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> commandHistory.popPreviousCommand());
    }

    @Test
    public void popPreviousCommand_hasCommandInCommandHistory_returnsPreviousCommand() {
        String testCommand = ListCommand.COMMAND_WORD;
        commandHistory.addToHistory(testCommand);

        assertEquals(testCommand, commandHistory.popPreviousCommand());
    }

    @Test
    public void clearHistory_commandHistoryIsEmpty_clearsCommandHistory() {
        commandHistory.clearHistory();
        CommandHistory expectedCommandHistory = new CommandHistory();
        assertEquals(expectedCommandHistory, commandHistory);
    }

    @Test
    public void clearHistory_hasCommandInCommandHistory_clearsCommandHistory() {
        String testCommand = ListCommand.COMMAND_WORD;
        commandHistory.addToHistory(testCommand);
        commandHistory.clearHistory();

        CommandHistory expectedCommandHistory = new CommandHistory();

        assertEquals(expectedCommandHistory, commandHistory);
    }

    @Test
    public void display_commandHistoryIsEmpty_returnsEmptyString() {
        String expectedOutput = "";
        assertEquals(expectedOutput, commandHistory.display());
    }

    @Test
    public void display_hasCommandInCommandHistory_returnsStringRepresentationOfCommandHistory() {
        String testCommand = ListCommand.COMMAND_WORD;
        commandHistory.addToHistory(testCommand);

        String expectedOutput = "1. " + testCommand + "\n";

        assertEquals(expectedOutput, commandHistory.display());
    }

    @Test
    public void equals() {
        String testCommand = ListCommand.COMMAND_WORD;

        // no commands in history -> true
        CommandHistory copyOfCommandHistory = new CommandHistory();
        assertTrue(commandHistory.equals(copyOfCommandHistory));

        // same object -> true
        assertTrue(commandHistory.equals(commandHistory));

        // null -> false
        assertFalse(commandHistory.equals(null));

        // different types -> false
        assertFalse(commandHistory.equals(5));

        // same commands in history -> true
        copyOfCommandHistory.addToHistory(testCommand);
        commandHistory.addToHistory(testCommand);
        assertTrue(commandHistory.equals(copyOfCommandHistory));
    }

}
