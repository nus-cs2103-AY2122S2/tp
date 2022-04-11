package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_DATE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_DATE_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OUTDATED_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UPDATED_DATE;
import static seedu.address.testutil.TypicalBudgets.ALPHA_BUDGET;
import static seedu.address.testutil.TypicalBudgets.BETA_BUDGET;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BudgetBuilder;

public class BudgetTest {

    @Test
    public void getBudgetAmount() {
        Budget betaCopy = new BudgetBuilder(BETA_BUDGET).build();
        assertTrue(betaCopy.getBudgetAmount().equals(new Amount(VALID_BUDGET_AMOUNT_BETA)));
        assertFalse(betaCopy.getBudgetAmount().equals(new Amount(VALID_BUDGET_AMOUNT_ALPHA)));
    }

    @Test
    public void getBudgetMonth() {
        Budget betaCopy = new BudgetBuilder(BETA_BUDGET).build();
        assertTrue(betaCopy.getBudgetMonth().equals(new Date(VALID_BUDGET_DATE_BETA).getMonth()));
        assertFalse(betaCopy.getBudgetMonth().equals(new Date(VALID_BUDGET_DATE_ALPHA).getMonth()));
    }

    @Test
    public void getBudgetDate() {
        Budget betaCopy = new BudgetBuilder(BETA_BUDGET).build();
        assertTrue(betaCopy.getBudgetDate().equals(new Date(VALID_BUDGET_DATE_BETA)));
        assertFalse(betaCopy.getBudgetDate().equals(new Date(VALID_BUDGET_DATE_ALPHA)));
    }

    @Test
    public void isUndefined() {
        Budget alphaCopy = new BudgetBuilder(ALPHA_BUDGET).withBudgetDate(VALID_UPDATED_DATE).build();
        Budget betaCopy = new BudgetBuilder(BETA_BUDGET).withBudgetDate(VALID_OUTDATED_DATE).build();

        assertFalse(alphaCopy.isUndefined());
        assertTrue(betaCopy.isUndefined());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Budget aliceCopy = new BudgetBuilder(ALPHA_BUDGET).build();
        assertTrue(ALPHA_BUDGET.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALPHA_BUDGET.equals(ALPHA_BUDGET));

        // null -> returns false
        assertFalse(ALPHA_BUDGET.equals(null));

        // different type -> returns false
        assertFalse(ALPHA_BUDGET.equals(5));

        // different expense -> returns false
        assertFalse(ALPHA_BUDGET.equals(BETA_BUDGET));

        // different amount -> returns false
        Budget editedAlpha = new BudgetBuilder(ALPHA_BUDGET).withBudgetAmount(VALID_BUDGET_AMOUNT_BETA).build();
        assertFalse(ALPHA_BUDGET.equals(editedAlpha));

        // different description -> returns false
        editedAlpha = new BudgetBuilder(ALPHA_BUDGET).withBudgetDate(VALID_BUDGET_DATE_BETA).build();
        assertFalse(ALPHA_BUDGET.equals(editedAlpha));
    }
}
