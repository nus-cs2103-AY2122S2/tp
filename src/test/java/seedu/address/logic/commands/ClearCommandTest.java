package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyExpenseExpert_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExpenseExpert_success() {
        Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
        expectedModel.setExpenseExpert(new ExpenseExpert());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
