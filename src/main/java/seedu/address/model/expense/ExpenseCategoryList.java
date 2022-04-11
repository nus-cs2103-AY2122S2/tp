package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.expense.exceptions.DuplicateExpenseCategoryException;
import seedu.address.model.expense.exceptions.ExpenseCategoryNotFoundException;

public class ExpenseCategoryList implements Iterable<ExpenseCategory> {
    private final ObservableList<ExpenseCategory> internalList = FXCollections.observableArrayList();
    private final ObservableList<ExpenseCategory> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent expense as the given argument.
     */
    public boolean contains(ExpenseCategory toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameExpenseCategory);
    }

    /**
     * Adds an expense to the list.
     * The expense must not already exist in the list.
     */
    public void add(ExpenseCategory toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateExpenseCategoryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent expense from the list.
     * The expense must exist in the list.
     */
    public void remove(ExpenseCategory toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ExpenseCategoryNotFoundException();
        }
    }

    public void setExpenseCategories(ExpenseCategoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code expenses}.
     * {@code expenses} must not contain duplicate expenses.
     */
    public void setExpenseCategories(List<ExpenseCategory> expenses) {
        requireAllNonNull(expenses);
        if (!expenseCategoriesAreUnique(expenses)) {
            throw new DuplicateExpenseCategoryException();
        }
        internalList.setAll(expenses);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ExpenseCategory> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }


    @Override
    public Iterator<ExpenseCategory> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseCategoryList // instanceof handles nulls
                && internalList.equals(((ExpenseCategoryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code expenses} contains only unique expenses.
     */
    private boolean expenseCategoriesAreUnique(List<ExpenseCategory> expenseCategories) {
        for (int i = 0; i < expenseCategories.size() - 1; i++) {
            for (int j = i + 1; j < expenseCategories.size(); j++) {
                if (expenseCategories.get(i).isSameExpenseCategory(expenseCategories.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}

