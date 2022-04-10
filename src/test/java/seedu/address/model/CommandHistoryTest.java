package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void equals() {
        
    }

}
