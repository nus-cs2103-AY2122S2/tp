package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false, false, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true, false, false, false)));

        // different summarise value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true, false, false)));

        // different email value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true, false)));

        // different resize value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, false, true)));
    }

    @Test
    public void constructor_helpCommandResultAssignment_success() {
        CommandResult helpCommand =
                new CommandResult(HelpCommand.SHOWING_HELP_MESSAGE, true, false, false, false, false);
        assertTrue(helpCommand.isShowHelp());
    }

    @Test
    public void constructor_summariseCommandResultAssignment_success() {
        CommandResult summariseCommand =
                new CommandResult(SummariseCommand.MESSAGE_SUMMARISE_PERSON_SUCCESS, false,
                        false, true, false, false);
        assertTrue(summariseCommand.isSummarise());
    }

    @Test
    public void constructor_resizeCommandResultAssignment_success() {
        CommandResult resizeCommand =
                new CommandResult(ResizeCommand.MESSAGE_RESIZE, false,
                        false, false, false, true);
        assertTrue(resizeCommand.isResize());
    }

    @Test
    public void constructor_exitCommandResultAssignment_success() {
        CommandResult exitCommand =
                new CommandResult(SummariseCommand.MESSAGE_SUMMARISE_PERSON_SUCCESS, false,
                        true, false, false, false);
        assertTrue(exitCommand.isExit());
    }

    @Test
    public void constructor_emailCommandResultAssignment_success() {
        CommandResult emailCommand =
                new CommandResult(SummariseCommand.MESSAGE_SUMMARISE_PERSON_SUCCESS, false,
                        false, false, true, false);
        assertTrue(emailCommand.isShowEmail());
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true,
                false, false, false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                true, false, false, false).hashCode());

        // different summarise value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, true, false, false).hashCode());

        // different email value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false, true, false).hashCode());

        //different resize value -> return different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false,
                false, false, false, true).hashCode());
    }
}
