package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
// import seedu.address.model.budget.Budget;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete);

        ModelManager expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.deleteExpense(expenseToDelete);
        setNewBudget(expectedModel, expenseToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete);

        Model expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.deleteExpense(expenseToDelete);
        showNoExpense(expectedModel);
        setNewBudget(expectedModel, expenseToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense expert list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseExpert().getExpenseList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_EXPENSE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_EXPENSE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoExpense(Model model) {
        model.updateFilteredExpenseList(p -> false);

        assertTrue(model.getFilteredExpenseList().isEmpty());
    }

    private void setNewBudget(Model model, Expense expenseToDelete) {
        String difference = String.valueOf((model.getBudget().getBudgetAmount().amount + expenseToDelete
                .getAmount().amount));
        Amount newAmount = new Amount(difference);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }


}
