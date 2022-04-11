package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;

public class CommandResultTest {
    @Test
    public void constructorTests() {
        CommandResult commandResult = new CommandResult("feedback");
        assertEquals(GuiListContentType.UNCHANGED, commandResult.getUiContentType());

        commandResult = new CommandResult("feedback", GuiListContentType.PERSON);
        assertEquals(GuiListContentType.PERSON, commandResult.getUiContentType());

        commandResult = new CommandResult("feedback", true, true);
        assertTrue(commandResult.isExit());
        assertTrue(commandResult.isShowHelp());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", GuiListContentType.UNCHANGED,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different content type -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", GuiListContentType.PERSON,
                false, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", GuiListContentType.UNCHANGED,
                true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", GuiListContentType.UNCHANGED,
                false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different content type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", GuiListContentType.PERSON,
                false, false).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", GuiListContentType.UNCHANGED,
                true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", GuiListContentType.UNCHANGED,
                false, true).hashCode());
    }
}
