package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_AMOUNT_BETA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_DATE_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_DATE_BETA;

import seedu.address.model.expense.Budget;

/**
 * A utility class containing a list of {@code Budget} objects to be used in tests.
 */
public class TypicalBudgets {
    public static final Budget OMEGA_BUDGET = new BudgetBuilder()
            .withBudgetAmount("1000")
            .withBudgetDate("2020-01-02")
            .build();
    public static final Budget GAMMA_BUDGET = new BudgetBuilder()
            .withBudgetAmount("10000")
            .withBudgetDate("2020-05-01")
            .build();

    public static final Budget ALPHA_BUDGET = new BudgetBuilder()
            .withBudgetAmount(VALID_BUDGET_AMOUNT_ALPHA)
            .withBudgetDate(VALID_BUDGET_DATE_ALPHA)
            .build();
    public static final Budget BETA_BUDGET = new BudgetBuilder()
            .withBudgetAmount(VALID_BUDGET_AMOUNT_BETA)
            .withBudgetDate(VALID_BUDGET_DATE_BETA)
            .build();

    private TypicalBudgets() {}
}
