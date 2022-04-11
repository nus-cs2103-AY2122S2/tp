package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;



public class EnableMouseUxTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());

    @Test
    public void execute_enable_success() {
        Model expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        expectedModel.enableMouseUX();

        //Test if mouseUX is disabled by default
        assertFalse(model.isMouseUxEnabled());

        CommandResult expectedCommandResult = new CommandResult(EnableMouseUxCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new EnableMouseUxCommand(), model, expectedCommandResult, expectedModel);
        assertTrue(model.isMouseUxEnabled());
    }
}
