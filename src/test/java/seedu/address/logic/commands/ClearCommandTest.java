package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;

import org.junit.jupiter.api.Test;

import seedu.address.model.HireLah;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyHireLah_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHireLah_success() {
        Model model = new ModelManager(getTypicalHireLah(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHireLah(), new UserPrefs());
        expectedModel.setHireLah(new HireLah());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
