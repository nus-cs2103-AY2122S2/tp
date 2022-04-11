package seedu.unite.logic.commands;

import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.Unite;
import seedu.unite.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyUnite_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUnite_success() {
        Model model = new ModelManager(getTypicalUnite(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        expectedModel.setUnite(new Unite());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
