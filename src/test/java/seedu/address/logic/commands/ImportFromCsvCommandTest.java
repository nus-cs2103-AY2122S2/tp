package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportFromCsvCommand.MESSAGE_SUCCESS;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ImportFromCsvCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private final HashMap<String, Boolean> settings = new HashMap<>();

    @Test
    public void execute_exit_success() {
        settings.put("importFromCsv", true);
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_SUCCESS, settings);
        assertCommandSuccess(new ImportFromCsvCommand(), model, expectedCommandResult, expectedModel);
    }
}
