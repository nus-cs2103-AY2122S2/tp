package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class UndoCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT, false, false, true);
        assertCommandSuccess(new UndoCommand(), model, expectedCommandResult, expectedModel);
    }
}
