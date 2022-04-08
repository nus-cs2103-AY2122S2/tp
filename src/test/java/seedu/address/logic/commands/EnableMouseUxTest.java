package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;



public class EnableMouseUxTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_enable_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.enableMouseUX();

        //Test if mouseUX is disabled by default
        assertFalse(model.isMouseUxEnabled());

        CommandResult expectedCommandResult = new CommandResult(EnableMouseUxCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new EnableMouseUxCommand(), model, expectedCommandResult, expectedModel);
        assertTrue(model.isMouseUxEnabled());
    }
}
