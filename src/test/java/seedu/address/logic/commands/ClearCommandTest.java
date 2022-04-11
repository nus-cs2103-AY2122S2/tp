package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplications.getTypicalInternApplyMemory;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyInternApplyMemory_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyInternApplyMemory_success() {
        Model model = new ModelManager(getTypicalInternApplyMemory(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInternApplyMemory(), new UserPrefs());
        expectedModel.setInternApplyMemory(new InternApplyMemory());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
