package seedu.address.model.expense;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents the Budget in the Expense Expert.
 */
public class Budget {
    /**
     * Attribute fields
     */
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Amount budgetAmount;
    private final Date budgetDate;

    /**
     * Constructs a (@code Budget).
     * Every field must be present and not null.
     *
     * @param budgetAmount is valid non-null amount
     * @param budgetDate is valid non-null date
     */
    public Budget(Amount budgetAmount, Date budgetDate) {
        requireAllNonNull(budgetAmount, budgetDate);
        this.budgetAmount = budgetAmount;
        this.budgetDate = budgetDate;
    }

    /**
     * Constructs a (@code Budget).
     * Every field must be present and not null.
     *
     * @param budgetAmount is valid non-null amount
     */
    public Budget(Amount budgetAmount) {
        requireAllNonNull(budgetAmount);
        this.budgetAmount = budgetAmount;
        this.budgetDate = new Date(LocalDate.now().format(dtf));
    }

    /**
     * Returns the amount of budget left in Expense Expert.
     * Returns 0 if the budget is undefined or depleted.
     */
    public Amount getBudgetAmount() {
        return this.budgetAmount;
    }

    /**
     * Returns the month when budget is set in Expense Expert.
     */
    public String getBudgetMonth() {
        return this.budgetDate.getMonth();
    }

    /**
     * Returns the date when budget is set in Expense Expert.
     */
    public Date getBudgetDate() {
        return this.budgetDate;
    }

    /**
     * Returns whether the budget is defined. The same method can be
     * used to check whether the budget has been depleted.
     */
    public boolean isUndefined() {
        return this.budgetDate.isOutdated();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Budget amount: ")
                .append(getBudgetAmount())
                .append("; Budget set on: ")
                .append(getBudgetDate());

        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return otherBudget.getBudgetAmount().equals(getBudgetAmount())
                && otherBudget.getBudgetDate().equals(getBudgetDate());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(budgetAmount, budgetDate);
    }
}
