package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.help.HelpDescription;
import seedu.address.logic.commands.help.OverallHelpCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class OverallHelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_overallHelp_success() {
        CommandResult expectedCommandResult = new CommandResult(HelpDescription.OVERALL_HELPING_DESCRIPTION,
                null, true, false);
        assertCommandSuccess(new OverallHelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
