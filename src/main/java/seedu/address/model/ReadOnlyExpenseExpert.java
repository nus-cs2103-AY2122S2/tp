package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an expense expert
 */
public interface ReadOnlyExpenseExpert {

    /**
     * Returns an unmodifiable view of the expenses list.
     * This list will not contain any duplicate expenses.
     */
    ObservableList<Expense> getExpenseList();
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the expenses list.
     * This list will not contain any duplicate expenses.
     */
    ObservableList<ExpenseCategory> getExpenseCategoryList();

    /** Returns the budget in Expense Expert */
    Budget getBudget();

    /** Returns whether budget in Expense Expert has been defined */
    boolean hasUndefinedBudget();
}
