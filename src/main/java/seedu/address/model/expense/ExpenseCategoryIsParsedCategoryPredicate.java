package seedu.address.model.expense;

import java.util.function.Predicate;

/**
 * Tests that a {@code Expense}'s {@code Name} matches any of the keywords given.
 */
public class ExpenseCategoryIsParsedCategoryPredicate implements Predicate<Expense> {
    private final String category;

    public ExpenseCategoryIsParsedCategoryPredicate(String category) {
        this.category = category;
    }

    @Override
    public boolean test(Expense expense) {
        return category.equalsIgnoreCase(expense.getExpenseCategory().expenseCategory);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseCategoryIsParsedCategoryPredicate // instanceof handles nulls
                && category.equals(((ExpenseCategoryIsParsedCategoryPredicate) other).category)); // state check
    }

}
