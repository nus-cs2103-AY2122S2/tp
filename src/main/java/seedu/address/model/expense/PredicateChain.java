package seedu.address.model.expense;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Expense}'s {@code ExpenseDate} matches the parsed date filter.
 */
public class PredicateChain implements Predicate<Expense> {

    private final List<Predicate<Expense>> predicateList;

    public PredicateChain(List<Predicate<Expense>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public boolean test(Expense expense) {
        for (int i = 0; i < predicateList.size(); i++) {
            if (!predicateList.get(i).test(expense)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateChain // instanceof handles nulls
                && predicateList.equals(((PredicateChain) other).predicateList)); // state check
    }
}
