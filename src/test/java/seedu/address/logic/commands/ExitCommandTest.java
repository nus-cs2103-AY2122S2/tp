package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private final HashMap<String, Boolean> settings = new HashMap<>();

    @Test
    public void execute_exit_success() {
        settings.put("exit", true);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, settings);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
}
