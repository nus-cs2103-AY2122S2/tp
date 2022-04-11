package seedu.address.logic.commands.applicant;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.applicant.TabApplicant.SHOWING_TABAPPLICANT_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;


public class TabApplicantTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_TABAPPLICANT_MESSAGE,
                true, false, true);
        assertCommandSuccess(new TabApplicant(), model, expectedCommandResult, expectedModel);
    }
}
