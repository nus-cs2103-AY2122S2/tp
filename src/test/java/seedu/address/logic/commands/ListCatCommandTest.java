package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListCatCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ListCatCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
        expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
    }

    @Test
    void execute_success() {
        String expectedDisplay = String.format(MESSAGE_SUCCESS, model.getExpenseExpert().getExpenseCategoryList());
        CommandResult expectedCommandResult = new CommandResult(expectedDisplay, false, false);
        assertCommandSuccess(new ListCatCommand(), model, expectedCommandResult, expectedModel);
    }
}
