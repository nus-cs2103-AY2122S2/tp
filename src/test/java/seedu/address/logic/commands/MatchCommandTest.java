package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MatchCommand.SHOWING_MATCH_MESSAGE;

public class MatchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_match_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_MATCH_MESSAGE, false, true, false);
        assertCommandSuccess(new MatchCommand(), model, expectedCommandResult, expectedModel);
    }
}
