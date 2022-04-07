package seedu.trackermon.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the the UI) for {@code CommandBuilder}.
 */
class CommandBuilderTest {

    private CommandBuilder commandBuilder = new CommandBuilder("Delete", "delete INDEX");

    /**
     * Test method to test the command title getter of the {@code CommandBuilder}.
     */
    @Test
    void getCommandTitle() {
        assertEquals("Delete", commandBuilder.getCommandTitle());
    }

    /**
     * Test method to test the command input getter of the {@code CommandBuilder}.
     */
    @Test
    void getCommandInput() {
        assertEquals("delete INDEX", commandBuilder.getCommandInput());
    }

    /**
     * Test method to test the command title property of the {@code CommandBuilder}.
     */
    @Test
    void commandTitleProperty() {
        assertTrue(commandBuilder.commandTitleProperty().get().equals("Delete"));
    }

    /**
     * Test method to test the command input property of the {@code CommandBuilder}.
     */
    @Test
    void commandInputProperty() {
        assertTrue(commandBuilder.commandInputProperty().get().equals("delete INDEX"));
    }
}
