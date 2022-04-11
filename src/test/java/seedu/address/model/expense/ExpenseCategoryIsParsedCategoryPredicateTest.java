package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class ExpenseCategoryIsParsedCategoryPredicateTest {

    @Test
    public void equals() {
        String firstCategory = "Food";
        String secondCategory = "Travel";

        ExpenseCategoryIsParsedCategoryPredicate firstPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate(firstCategory);
        ExpenseCategoryIsParsedCategoryPredicate secondPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate(secondCategory);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseCategoryIsParsedCategoryPredicate firstPredicateCopy =
                new ExpenseCategoryIsParsedCategoryPredicate(firstCategory);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_categoriesMatch_returnsTrue() {
        //Same category same cases
        ExpenseCategoryIsParsedCategoryPredicate predicate =
                new ExpenseCategoryIsParsedCategoryPredicate("Food");
        assertTrue(predicate.test(new ExpenseBuilder().withExpenseCategory("Food").build()));

        //Same category different cases
        predicate = new ExpenseCategoryIsParsedCategoryPredicate("food");
        assertTrue(predicate.test(new ExpenseBuilder().withExpenseCategory("Food").build()));
    }

    @Test
    public void test_categoriesDoNotMatch_returnsFalse() {
        //Different category
        ExpenseCategoryIsParsedCategoryPredicate predicate =
                new ExpenseCategoryIsParsedCategoryPredicate("Food");
        assertFalse(predicate.test(new ExpenseBuilder().withExpenseCategory("Travel").build()));
    }
}
