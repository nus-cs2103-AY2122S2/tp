package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.person.Person;

/**
 * Test class to test the methods of the PaidPersonWhoOwedCommand class.
 */
public class PaidPersonWhoOwedCommandTest {
    private Model model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());

    /**
     * Tests the validity of the command when asked to execute a simple valid input with an unfiltered
     * Person list.
     */
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        PaidPersonWhoOwedCommand paidPersonWhoOwedCommand = new PaidPersonWhoOwedCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(PaidPersonWhoOwedCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(paidPersonWhoOwedCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests the validity of the command when asked to execute an input with an invalid index with
     * an unfiltered Person list.
     */
    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PaidPersonWhoOwedCommand paidPersonWhoOwedCommand = new PaidPersonWhoOwedCommand(outOfBoundIndex);

        assertCommandFailure(paidPersonWhoOwedCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    /**
     * Tests the validity of the command when asked to execute a simple valid input with a filtered
     * Person list.
     */
    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_EXPENSE);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        PaidPersonWhoOwedCommand paidPersonWhoOwedCommand = new PaidPersonWhoOwedCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(PaidPersonWhoOwedCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);


        assertCommandSuccess(paidPersonWhoOwedCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Tests the validity of the command when asked to execute an input with an invalid index with
     * a filtered Person list.
     */
    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_EXPENSE);

        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense expert list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseExpert().getPersonList().size());

        PaidPersonWhoOwedCommand paidPersonWhoOwedCommand = new PaidPersonWhoOwedCommand(outOfBoundIndex);

        assertCommandFailure(paidPersonWhoOwedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        PaidPersonWhoOwedCommand paidFirstCommand = new PaidPersonWhoOwedCommand(INDEX_FIRST_EXPENSE);
        PaidPersonWhoOwedCommand paidSecondCommand = new PaidPersonWhoOwedCommand(INDEX_SECOND_EXPENSE);

        // same object -> returns true
        assertTrue(paidFirstCommand.equals(paidFirstCommand));

        // same values -> returns true
        PaidPersonWhoOwedCommand paidFirstCommandCopy = new PaidPersonWhoOwedCommand(INDEX_FIRST_EXPENSE);
        assertTrue(paidFirstCommand.equals(paidFirstCommandCopy));

        // different types -> returns false
        assertFalse(paidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(paidFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(paidFirstCommand.equals(paidSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    /**
     * Update's model's budget according to the new transaction recorded.
     * @param model the model whose budget is to be updated
     * @param expenseToDelete the transaction recorded
     */
    private void setNewBudget(Model model, Expense expenseToDelete) {
        String difference = String.valueOf((model.getBudget().getBudgetAmount().amount + expenseToDelete
                .getAmount().amount));
        Amount newAmount = new Amount(difference);
        Budget budget = new Budget(newAmount);
        model.setBudget(budget);
    }

}
