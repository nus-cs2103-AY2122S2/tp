package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Date;

/**
 * A utility class to help with building Budget objects.
 */
public class BudgetBuilder {

    public static final String DEFAULT_BUDGET_AMOUNT = "700";
    public static final String DEFAULT_BUDGET_DATE = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private Amount budgetAmount;
    private Date budgetDate;

    /**
     * Creates a {@code BudgetBuilder} with the default details.
     */
    public BudgetBuilder() {
        budgetAmount = new Amount(DEFAULT_BUDGET_AMOUNT);
        budgetDate = new Date(DEFAULT_BUDGET_DATE);
    }

    /**
     * Initializes the BudgetBuilder with the data of {@code budgetToCopy}.
     */
    public BudgetBuilder(Budget budgetToCopy) {
        budgetAmount = budgetToCopy.getBudgetAmount();
        budgetDate = budgetToCopy.getBudgetDate();
    }

    /**
     * Sets the {@code Amount} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withBudgetAmount(String budgetAmount) {
        this.budgetAmount = new Amount(budgetAmount);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Budget} that we are building
     * @param budgetDate expense date to be set
     * @return Budget Builder
     */
    public BudgetBuilder withBudgetDate(String budgetDate) {
        this.budgetDate = new Date(budgetDate);
        return this;
    }

    public Budget build() {
        return new Budget(budgetAmount, budgetDate);
    }
}
