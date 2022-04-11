package seedu.address.testutil;

import seedu.address.model.expense.ExpenseCategory;

public class ExpenseCategoryBuilder {

    public static final String DEFAULT_EXPENSE_CATEGORY = "Transport";

    private ExpenseCategory expenseCategory;

    /**
     * Creates a {@code ExpenseBuilder} with the default details.
     */
    public ExpenseCategoryBuilder() {
        expenseCategory = new ExpenseCategory(DEFAULT_EXPENSE_CATEGORY);
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code expenseToCopy}.
     */
    public ExpenseCategoryBuilder(ExpenseCategory expenseCategoryToCopy) {
        expenseCategory = expenseCategoryToCopy;
    }

    /**
     * Sets the {@code ExpenseCategory} of the {@code Expense} that we are building.
     */
    public ExpenseCategoryBuilder withExpenseCategory(String expenseCategory) {
        this.expenseCategory = new ExpenseCategory(expenseCategory);
        return this;
    }

    public ExpenseCategory build() {
        return expenseCategory;
    }

}
