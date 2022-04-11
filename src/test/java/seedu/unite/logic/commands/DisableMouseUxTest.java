package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;

public class DisableMouseUxTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());

    @Test
    public void execute_enable_success() {
        Model expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        expectedModel.enableMouseUX();
        CommandResult expectedCommandResult = new CommandResult(DisableMouseUxCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new DisableMouseUxCommand(), model, expectedCommandResult, expectedModel);
        assertFalse(model.isMouseUxEnabled());
    }
}
