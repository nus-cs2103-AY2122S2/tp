package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.ENTERTAINMENT_CATEGORY;
import static seedu.address.testutil.TypicalExpenses.TRANSPORT_CATEGORY;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.expense.exceptions.DuplicateExpenseCategoryException;
import seedu.address.model.expense.exceptions.ExpenseCategoryNotFoundException;
import seedu.address.testutil.ExpenseCategoryBuilder;


class ExpenseCategoryListTest {
    private final ExpenseCategoryList expenseCategoryList = new ExpenseCategoryList();

    @Test
    public void contains_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseCategoryList.contains(null));
    }

    @Test
    public void contains_expenseCategoryNotInList_returnsFalse() {
        assertFalse(expenseCategoryList.contains(TRANSPORT_CATEGORY));
    }

    @Test
    public void contains_expenseCategoryInList_returnsTrue() {
        expenseCategoryList.add(TRANSPORT_CATEGORY);
        assertTrue(expenseCategoryList.contains(TRANSPORT_CATEGORY));
    }

    // Checks if categories have the same ignoring capitalization
    @Test
    public void contains_expenseCategoryWithSameIdentityFieldsInList_returnsTrue() {
        expenseCategoryList.add(TRANSPORT_CATEGORY);
        ExpenseCategory editedTransport = new ExpenseCategoryBuilder().withExpenseCategory("tRanSporT").build();
        assertTrue(expenseCategoryList.contains(TRANSPORT_CATEGORY));
    }

    @Test
    public void add_nullExpenseCategory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseCategoryList.add(null));
    }

    @Test
    public void add_duplicateExpenseCategory_throwsDuplicateExpenseCategoryException() {
        expenseCategoryList.add(TRANSPORT_CATEGORY);
        assertThrows(DuplicateExpenseCategoryException.class, () -> expenseCategoryList.add(TRANSPORT_CATEGORY));
    }


    @Test
    public void remove_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseCategoryList.remove(null));
    }

    @Test
    public void remove_expenseDoesNotExist_throwsExpenseNotFoundException() {
        assertThrows(ExpenseCategoryNotFoundException.class, () -> expenseCategoryList.remove(TRANSPORT_CATEGORY));
    }

    @Test
    public void remove_existingExpense_removesExpense() {
        expenseCategoryList.add(TRANSPORT_CATEGORY);
        expenseCategoryList.remove(TRANSPORT_CATEGORY);
        ExpenseCategoryList expectedExpenseCategoryList = new ExpenseCategoryList();
        assertEquals(expectedExpenseCategoryList, expenseCategoryList);
    }

    // setExpense_nullUniqueExpenseList() -> throwsNullPointerException()
    @Test
    public void setExpense_nullUniqueExpenseList() {
        assertThrows(NullPointerException.class, () -> expenseCategoryList
                .setExpenseCategories((ExpenseCategoryList) null));
    }
    // setExpense_UniqueExpenseList -> replacesOwnListWithProvidedUniqueExpenseList
    @Test
    public void setExpenseCategory_expenseCategoryList() {
        expenseCategoryList.add(TRANSPORT_CATEGORY);
        ExpenseCategoryList expectedExpenseCategoryList = new ExpenseCategoryList();
        expectedExpenseCategoryList.add(ENTERTAINMENT_CATEGORY);
        expenseCategoryList.setExpenseCategories(expectedExpenseCategoryList);
        assertEquals(expectedExpenseCategoryList, expenseCategoryList);
    }

    @Test
    public void setExpenseCategory_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseCategoryList
                .setExpenseCategories((List<ExpenseCategory>) null));
    }

    @Test
    public void setExpense_listWithDuplicateExpenses_throwsDuplicateExpenseException() {
        List<ExpenseCategory> listWithDuplicateExpenseCategories = Arrays
                .asList(TRANSPORT_CATEGORY, TRANSPORT_CATEGORY);
        assertThrows(DuplicateExpenseCategoryException.class, () -> expenseCategoryList
                .setExpenseCategories(listWithDuplicateExpenseCategories));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseCategoryList
                .asUnmodifiableObservableList().remove(0));
    }
}
