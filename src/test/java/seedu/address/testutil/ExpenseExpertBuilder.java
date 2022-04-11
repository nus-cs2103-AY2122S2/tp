package seedu.address.testutil;

import seedu.address.model.ExpenseExpert;
import seedu.address.model.expense.Expense;

/**
 * A utility class to help with building ExpenseExpert objects.
 * Example usage: <br>
 *     {@code ExpenseExpert ee = new ExpenseExpertBuilder().withExpense("Grab", "Groceries").build();}
 */
public class ExpenseExpertBuilder {

    private ExpenseExpert expenseExpert;

    public ExpenseExpertBuilder() {
        expenseExpert = new ExpenseExpert();
    }

    public ExpenseExpertBuilder(ExpenseExpert expenseExpert) {
        this.expenseExpert = expenseExpert;
    }

    /**
     * Adds a new {@code Expense} to the {@code ExpenseExpert} that we are building.
     */
    public ExpenseExpertBuilder withExpense(Expense expense) {
        expenseExpert.addExpense(expense);
        return this;
    }

    public ExpenseExpert build() {
        return expenseExpert;
    }
}
