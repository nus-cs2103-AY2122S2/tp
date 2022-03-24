package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class CommandResultTest {

    private static Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_forEvent_commandResultIsEvent() throws CommandException {
        Command eventCommand = new ShowEventsCommand();
        CommandResult commandResult = eventCommand.execute(model);

        assertTrue(commandResult.isEvent());
        assertFalse(commandResult.isShowFriendCommand());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void execute_forShowFriend_commandResultIsShowFriend() throws CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Command showFriendCommand = new ShowFriendCommand(person);
        CommandResult commandResult = showFriendCommand.execute(model);

        assertTrue(commandResult.isShowFriendCommand());
        assertFalse(commandResult.isEvent());
        assertFalse(commandResult.isExit());
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void execute_exitCommand_commandResultIsExit() throws CommandException {
        Command exitCommand = new ExitCommand();
        CommandResult commandResult = exitCommand.execute(model);

        assertTrue(commandResult.isExit());
        assertFalse(commandResult.isShowFriendCommand());
        assertFalse(commandResult.isEvent());
        assertFalse(commandResult.isShowHelp());
    }

    @Test
    public void execute_helpCommand_commandResultIsHelp() throws CommandException {

        Command helpCommand = new HelpCommand();
        CommandResult commandResult = helpCommand.execute(model);

        assertTrue(commandResult.isShowHelp());
        assertFalse(commandResult.isShowFriendCommand());
        assertFalse(commandResult.isEvent());
        assertFalse(commandResult.isExit());
    }

    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        //different showFriend value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, false, true)));

        //different showEvent value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, false, true)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        //different showFriend value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", false, false, false, true).hashCode());

        //different showEvent value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", false, false, true).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }
}
