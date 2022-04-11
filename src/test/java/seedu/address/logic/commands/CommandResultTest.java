package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.DETAILED_VIEW;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.EXIT;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.NONE;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.SHOW_HELP;
import static seedu.address.logic.commands.CommandResult.SpecialCommandResult.VIEW_IMAGES;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", NONE)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", SHOW_HELP)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", EXIT)));

        // different view images value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", VIEW_IMAGES)));

        // different detailed view value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", DETAILED_VIEW)));

    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", SHOW_HELP).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", EXIT).hashCode());

        // different view images value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", VIEW_IMAGES).hashCode());

        // different detailed view value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", DETAILED_VIEW).hashCode());

    }
}
