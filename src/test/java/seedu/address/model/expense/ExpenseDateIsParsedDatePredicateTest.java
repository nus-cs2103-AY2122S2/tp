package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class ExpenseDateIsParsedDatePredicateTest {

    @Test
    public void equals() {
        String firstDatePredicate = "2022-03";
        String secondDatePredicate = "2022-04";
        String thirdDatePredicate = "2022-03-03";
        String forthDatePredicate = "2022-03-04";

        ExpenseDateIsParsedDatePredicate firstPredicate = new ExpenseDateIsParsedDatePredicate(firstDatePredicate);
        ExpenseDateIsParsedDatePredicate secondPredicate = new ExpenseDateIsParsedDatePredicate(secondDatePredicate);
        ExpenseDateIsParsedDatePredicate thirdPredicate = new ExpenseDateIsParsedDatePredicate(thirdDatePredicate);
        ExpenseDateIsParsedDatePredicate forthPredicate = new ExpenseDateIsParsedDatePredicate(forthDatePredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpenseDateIsParsedDatePredicate firstPredicateCopy = new ExpenseDateIsParsedDatePredicate(firstDatePredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false (different in months)
        assertFalse(firstPredicate.equals(secondPredicate));

        //different predicate -> return false (different in format of string, one is date, one is year month)
        assertFalse(firstPredicate.equals(thirdPredicate));

        // different predicate -> returns false (different in dates)
        assertFalse(thirdPredicate.equals(forthPredicate));
    }

    @Test
    public void test_dateMatchesDateParsed_returnsTrue() {
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate("2022-03-03");
        assertTrue(predicate.test(new ExpenseBuilder().withExpenseDate("2022-03-03").build()));
    }

    @Test
    public void test_dateMatchesMonthParsed_returnsTrue() {
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate("2022-03");
        assertTrue(predicate.test(new ExpenseBuilder().withExpenseDate("2022-03-03").build()));
    }

    @Test
    public void test_dateDoesNotMatchDateParsed_returnsFalse() {
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate("2022-03-03");
        assertFalse(predicate.test(new ExpenseBuilder().withExpenseDate("2022-03-04").build()));
    }

    @Test
    public void test_dateDoesNotMatchMonthParsed_returnsFalse() {
        ExpenseDateIsParsedDatePredicate predicate = new ExpenseDateIsParsedDatePredicate("2022-03");
        assertFalse(predicate.test(new ExpenseBuilder().withExpenseDate("2022-02-04").build()));
    }
}
