package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandEnumTest {

    @Test
    public void getMessageUsage_validCommand_success() {
        // valid commands
        assertEquals(AddCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("add").getMessageUsage());
        assertEquals(ClearCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("clear").getMessageUsage());
        assertEquals(DeleteCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("delete").getMessageUsage());
        assertEquals(EditCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("edit").getMessageUsage());
        assertEquals(ExitCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("exit").getMessageUsage());
        assertEquals(FindCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("find").getMessageUsage());
        assertEquals(FlagCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("flag").getMessageUsage());
        assertEquals(HelpCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("help").getMessageUsage());
        assertEquals(ListCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("list").getMessageUsage());
        assertEquals(MeetCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("meet").getMessageUsage());
        assertEquals(RedoCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("redo").getMessageUsage());
        assertEquals(SortCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("sort").getMessageUsage());
        assertEquals(UndoCommand.MESSAGE_USAGE, Command.CommandEnum.valueOf("undo").getMessageUsage());
    }

    @Test
    public void isValidCommandEnum() {
        // null command
        assertThrows(NullPointerException.class, () -> Command.CommandEnum.isValidCommand(null));

        // invalid commands
        assertFalse(Command.CommandEnum.isValidCommand(""));
        assertFalse(Command.CommandEnum.isValidCommand(" "));
        assertFalse(Command.CommandEnum.isValidCommand(" add"));
        assertFalse(Command.CommandEnum.isValidCommand(" clear"));
        assertFalse(Command.CommandEnum.isValidCommand(" delete"));
        assertFalse(Command.CommandEnum.isValidCommand(" edit"));
        assertFalse(Command.CommandEnum.isValidCommand(" exit"));
        assertFalse(Command.CommandEnum.isValidCommand(" find"));
        assertFalse(Command.CommandEnum.isValidCommand(" flag"));
        assertFalse(Command.CommandEnum.isValidCommand(" help"));
        assertFalse(Command.CommandEnum.isValidCommand(" list"));
        assertFalse(Command.CommandEnum.isValidCommand(" meet"));
        assertFalse(Command.CommandEnum.isValidCommand(" redo"));
        assertFalse(Command.CommandEnum.isValidCommand(" sort"));
        assertFalse(Command.CommandEnum.isValidCommand(" undo"));

        // valid commands
        assertTrue(Command.CommandEnum.isValidCommand("add"));
        assertTrue(Command.CommandEnum.isValidCommand("clear"));
        assertTrue(Command.CommandEnum.isValidCommand("delete"));
        assertTrue(Command.CommandEnum.isValidCommand("edit"));
        assertTrue(Command.CommandEnum.isValidCommand("exit"));
        assertTrue(Command.CommandEnum.isValidCommand("find"));
        assertTrue(Command.CommandEnum.isValidCommand("flag"));
        assertTrue(Command.CommandEnum.isValidCommand("help"));
        assertTrue(Command.CommandEnum.isValidCommand("list"));
        assertTrue(Command.CommandEnum.isValidCommand("meet"));
        assertTrue(Command.CommandEnum.isValidCommand("redo"));
        assertTrue(Command.CommandEnum.isValidCommand("sort"));
        assertTrue(Command.CommandEnum.isValidCommand("undo"));
    }
}
