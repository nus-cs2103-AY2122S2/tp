package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MatchCommand.SHOWING_MATCH_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class MatchCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_match_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_MATCH_MESSAGE,
                false, true, false, false, false);
        assertCommandSuccess(new MatchCommand(), model, expectedCommandResult, expectedModel);
    }
}
