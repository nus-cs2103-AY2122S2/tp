package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;


public class CommandListTest {

    @Test
    public void resetPointer_onEmptyList_success() {
        CommandList.getList().clearAllCommands();
        CommandList.getList().resetPointer();
        assertEquals(0, CommandList.getList().getPointer());
    }
    @Test
    public void resetPointer_success() throws CommandException {
        CommandList.getList().clearAllCommands();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");
        CommandList.getList().resetPointer();
        assertEquals(4, CommandList.getList().getPointer());
    }
    @Test
    public void getCurrentCommand_success() throws CommandException {
        CommandList.getList().clearAllCommands();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");
        CommandList.getList().decreasePointer();
        assertEquals("find n/ John4", CommandList.getList().getCurrentCommand());
    }
    @Test
    public void getCurrentCommand_fail() throws CommandException {
        CommandList.getList().clearAllCommands();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");

        assertThrows(IndexOutOfBoundsException.class, () -> CommandList.getList().getCurrentCommand());
    }
    @Test
    public void getCurrentCommand_onEmptyList_fail() {
        CommandList.getList().clearAllCommands();
        assertThrows(IndexOutOfBoundsException.class, () -> CommandList.getList().getCurrentCommand());
    }
    @Test
    public void decreasePointer_onEmptyList_fail() {
        CommandList.getList().clearAllCommands();
        assertThrows(CommandException.class, () -> CommandList.getList().decreasePointer());
    }
    @Test
    public void increasePointer_onEmptyList_fail() {
        CommandList.getList().clearAllCommands();
        assertThrows(CommandException.class, () -> CommandList.getList().increasePointer());
    }
}
