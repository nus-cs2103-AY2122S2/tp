package seedu.address.logic.commands;

/*
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

 */
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;

public class ExitCommandTest {
    private OldModel oldModel = new OldModelManager();
    private OldModel expectedOldModel = new OldModelManager();

    @Test
    public void execute_exit_success() {
        /*
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
        assertCommandSuccess(new ExitCommand(), oldModel, expectedCommandResult, expectedOldModel);

         */
    }
}
