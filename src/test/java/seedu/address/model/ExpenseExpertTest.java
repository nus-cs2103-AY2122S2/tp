package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.ANNUAL_NETFLIX_FEES;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import java.util.Collections;

import org.junit.jupiter.api.Test;

public class ExpenseExpertTest {

    private final ExpenseExpert expenseExpert = new ExpenseExpert();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), expenseExpert.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseExpert.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExpenseExpert_replacesData() {
        ExpenseExpert newData = getTypicalExpenseExpert();
        expenseExpert.resetData(newData);
        assertEquals(newData, expenseExpert);
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseExpert.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInExpenseExpert_returnsFalse() {
        assertFalse(expenseExpert.hasExpense(ANNUAL_NETFLIX_FEES));
    }

    @Test
    public void hasExpense_expenseInExpenseExpert_returnsTrue() {
        expenseExpert.addExpense(ANNUAL_NETFLIX_FEES);
        assertTrue(expenseExpert.hasExpense(ANNUAL_NETFLIX_FEES));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseExpert.getExpenseList().remove(0));
    }
}
