package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private final HashMap<String, Boolean> settings = new HashMap<>();

    @Test
    public void execute_help_success() {
        settings.put("help", true);
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, settings);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }
}
