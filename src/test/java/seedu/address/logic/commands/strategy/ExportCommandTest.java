package seedu.address.logic.commands.strategy;

import static seedu.address.commons.core.Tabs.DEFAULT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.strategy.ExportCommand.MESSAGE_EXPORT_ACKNOWLEDGEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExportCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXPORT_ACKNOWLEDGEMENT, false, false,
                DEFAULT, false, null, true);
        assertCommandSuccess(new ExportCommand(), model, expectedCommandResult, expectedModel);
    }
}

