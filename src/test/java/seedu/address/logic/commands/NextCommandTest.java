package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandRemark;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class NextCommandTest {

    private final Model model = new ModelManager();

    @Test
    public void execute_nextCommand_success() throws CommandException {
        CommandList.getList().clearAllCommands();
        CommandList.getList().resetPointer();
        CommandList.getList().record("find n/ John1");
        CommandList.getList().record("find n/ John2");
        CommandList.getList().record("find n/ John3");
        CommandList.getList().record("find n/ John4");

        Model expectedModel = new ModelManager();

        CommandList.getList().decreasePointer();
        CommandResult expectedCommandResult = new CommandResult(PreviousCommand.MESSAGE_ON_HISTORY_SUCCESS,
                CommandRemark.HISTORY);
        assertCommandSuccess(new PreviousCommand(), model, expectedCommandResult, expectedModel);
    }


    @Test
    public void execute_noNextCommand_fail() {
        CommandList.getList().clearAllCommands();
        CommandList.getList().resetPointer();
        assertThrows(CommandException.class, NextCommand.MESSAGE_ON_NO_NEXT, () ->
                new NextCommand().execute(model));
    }

}
