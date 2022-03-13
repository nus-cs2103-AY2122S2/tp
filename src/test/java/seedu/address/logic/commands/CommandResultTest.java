package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class CommandResultTest {

    private final HashMap<String, Boolean> settings = new HashMap<>();

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", null)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        settings.put("showHelp", true);
        settings.put("exit", false);
        assertFalse(commandResult.equals(new CommandResult("feedback", settings)));

        // different exit value -> returns false
        settings.put("showHelp", false);
        settings.put("exit", true);
        assertFalse(commandResult.equals(new CommandResult("feedback", settings)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        settings.put("showHelp", true);
        settings.put("exit", false);
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", settings).hashCode());

        // different exit value -> returns different hashcode
        settings.put("showHelp", false);
        settings.put("exit", true);
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", settings).hashCode());
    }
}
