package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ANNUAL_SPOTIFY_FEES;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_CATEGORY_ENTERTAINMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_DATE_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.ExpenseExpert;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.testutil.EditExpenseDescriptorBuilder;
import seedu.address.testutil.ExpenseBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Expense editedExpense = new ExpenseBuilder().build();
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder(editedExpense).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedExpense);
        setNewBudget(expectedModel, model.getFilteredExpenseList().get(0), editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExpense = Index.fromOneBased(model.getFilteredExpenseList().size());
        Expense lastExpense = model.getFilteredExpenseList().get(indexLastExpense.getZeroBased());

        ExpenseBuilder expenseInList = new ExpenseBuilder(lastExpense);
        Expense editedExpense = expenseInList
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR).withExpenseDate(VALID_EXPENSE_DATE_BUILD_A_BEAR).build();

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR)
                .withExpenseDate(VALID_EXPENSE_DATE_BUILD_A_BEAR).build();
        EditCommand editCommand = new EditCommand(indexLastExpense, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        expectedModel.setExpense(lastExpense, editedExpense);
        setNewBudget(expectedModel, lastExpense, editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE, new EditExpenseDescriptor());
        Expense editedExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        noNewBudget(expectedModel, editedExpense);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Expense expenseInFilteredList = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Expense editedExpense = new ExpenseBuilder(expenseInFilteredList)
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE,
                new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_BUILD_A_BEAR).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new ExpenseExpert(model.getExpenseExpert()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedExpense);
        setNewBudget(expectedModel, model.getFilteredExpenseList().get(0), editedExpense);


        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExpenseUnfilteredList_failure() {
        Expense firstExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder(firstExpense).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_EXPENSE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

    @Test
    public void execute_duplicateExpenseFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        // edit expense in filtered list into a duplicate in expense expert
        Expense expenseInList = model.getExpenseExpert().getExpenseList().get(INDEX_SECOND_EXPENSE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE,
                new EditExpenseDescriptorBuilder(expenseInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

    @Test
    public void execute_invalidExpenseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of expense expert
     */
    @Test
    public void execute_invalidExpenseIndexFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);
        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense expert list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseExpert().getExpenseList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditExpenseDescriptorBuilder().withDescription(VALID_DESCRIPTION_BUILD_A_BEAR).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_EXPENSE, DESC_ANNUAL_SPOTIFY_FEES);

        // same values -> returns true
        EditExpenseDescriptor copyDescriptor = new EditExpenseDescriptor(DESC_ANNUAL_SPOTIFY_FEES);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_EXPENSE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_EXPENSE, DESC_ANNUAL_SPOTIFY_FEES)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_EXPENSE, DESC_BUILD_A_BEAR)));
    }

    /**
     * Update's model's budget according to the new transaction recorded.
     */
    private void setNewBudget(Model model, Expense expenseToEdit, Expense editedExpense) {
        double difference = expenseToEdit.getAmount().amount - editedExpense.getAmount().amount;
        String amt = String.valueOf((model.getBudget().getBudgetAmount().amount + difference));
        Amount newAmount = new Amount(amt);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }

    /**
     * Update's model's budget according to the new transaction recorded.
     * @param model model whose budget is to be updated.
     * @param editedExpense the edited transaction
     */
    private void noNewBudget(Model model, Expense editedExpense) {
        double difference = editedExpense.getAmount().amount - editedExpense.getAmount().amount;
        String amt = String.valueOf((model.getBudget().getBudgetAmount().amount + difference));
        Amount newAmount = new Amount(amt);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }

}
