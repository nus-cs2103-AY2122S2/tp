package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ExpenseCategory {
    /*
     * The first character of the ExpenseCategory must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public static final String MESSAGE_CONSTRAINTS =
            "Categories should only contain alphanumeric characters and spaces, and it should not be blank";

    public final String expenseCategory;

    /**
     * Constructs a {@code ExpenseCategory}
     *
     * @param expenseCategory to indicate the category of the expense.
     */
    public ExpenseCategory(String expenseCategory) {
        requireNonNull(expenseCategory);
        checkArgument(isValidExpenseCategory(expenseCategory), MESSAGE_CONSTRAINTS);
        this.expenseCategory = expenseCategory;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidExpenseCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return expenseCategory;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseCategory // instanceof handles nulls
                && expenseCategory.equals(((ExpenseCategory) other).expenseCategory)); // state check
    }

    @Override
    public int hashCode() {
        return expenseCategory.hashCode();
    }

    /**
     * Returns true if a the expenseCategories have the same String regardless of case.
     */
    public boolean isSameExpenseCategory(ExpenseCategory otherExpenseCategory) {
        if (otherExpenseCategory == this) {
            return true;
        }

        return otherExpenseCategory != null
                && otherExpenseCategory.toString().equalsIgnoreCase(toString());
    }
}
