package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class PredicateChainTest {

    @Test
    public void equals() {
        Predicate<Expense> firstPredicate = new ExpensePredicateStub();
        Predicate<Expense> secondPredicate = new ExpensePredicateStubTwo();
        Predicate<Expense> thirdPredicate = new ExpensePredicateStubThree();

        PredicateChain predicateChain = new PredicateChain(List.of(firstPredicate, secondPredicate));
        PredicateChain predicateChainTwo = new PredicateChain(List.of(secondPredicate, thirdPredicate));

        // same object -> returns true
        assertTrue(predicateChain.equals(predicateChain));

        // same values -> returns true
        PredicateChain predicateChainCopy =
                new PredicateChain(List.of(firstPredicate, secondPredicate));
        assertTrue(predicateChain.equals(predicateChainCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(predicateChain.equals(predicateChainTwo));
    }

    @Test
    public void test_predicatesMatch_returnsTrue() {
        //One item in predicate chain using stub
        PredicateChain predicateChain =
                new PredicateChain(List.of(new ExpensePredicateStub()));
        assertTrue(predicateChain.test(new ExpenseBuilder().build()));

        //Two items in predicate chain using stub
        predicateChain = new PredicateChain(List.of(new ExpensePredicateStub(), new ExpensePredicateStubTwo()));
        assertTrue(predicateChain.test(new ExpenseBuilder().build()));

        //Using one implemented predicate
        ExpenseCategoryIsParsedCategoryPredicate categoryPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate("Food");
        predicateChain = new PredicateChain(List.of(categoryPredicate));
        assertTrue(predicateChain.test(new ExpenseBuilder().withExpenseCategory("Food").build()));

        //Using two implemented predicates
        ExpenseDateIsParsedDatePredicate datePredicate = new ExpenseDateIsParsedDatePredicate("2022-03");
        predicateChain = new PredicateChain(List.of(categoryPredicate, datePredicate));
        assertTrue(predicateChain.test(new ExpenseBuilder()
                .withExpenseCategory("Food")
                .withExpenseDate("2022-03-02").build()));
    }

    @Test
    public void test_categoriesDoNotMatch_returnsFalse() {
        //Using one implemented predicate, different categories
        ExpenseCategoryIsParsedCategoryPredicate categoryPredicate =
                new ExpenseCategoryIsParsedCategoryPredicate("Food");
        PredicateChain predicateChain = new PredicateChain(List.of(categoryPredicate));
        assertFalse(predicateChain.test(new ExpenseBuilder().withExpenseCategory("Travel").build()));

        //Using two implemented predicates, different categories
        ExpenseDateIsParsedDatePredicate datePredicate = new ExpenseDateIsParsedDatePredicate("2022-03");
        predicateChain = new PredicateChain(List.of(categoryPredicate, datePredicate));
        assertFalse(predicateChain.test(new ExpenseBuilder()
                .withExpenseCategory("Travel")
                .withExpenseDate("2022-03-02").build()));

        //Using two implemented predicates, different dates
        assertFalse(predicateChain.test(new ExpenseBuilder()
                .withExpenseCategory("Food")
                .withExpenseDate("2022-02-02").build()));

        //Using two implemented predicates, different dates and categories
        assertFalse(predicateChain.test(new ExpenseBuilder()
                .withExpenseCategory("Travel")
                .withExpenseDate("2022-02-02").build()));
    }
}

class ExpensePredicateStub implements Predicate<Expense> {
    @Override
    public boolean test(Expense expense) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ExpensePredicateStub;
    }
}

class ExpensePredicateStubTwo implements Predicate<Expense> {
    @Override
    public boolean test(Expense expense) {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ExpensePredicateStubTwo;
    }
}

class ExpensePredicateStubThree implements Predicate<Expense> {
    @Override
    public boolean test(Expense expense) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof ExpensePredicateStubThree;
    }
}
