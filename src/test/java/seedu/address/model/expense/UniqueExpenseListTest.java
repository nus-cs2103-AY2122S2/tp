package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUILD_A_BEAR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.ANNUAL_NETFLIX_FEES;
import static seedu.address.testutil.TypicalExpenses.ANNUAL_SPOTIFY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.expense.exceptions.DuplicateExpenseException;
import seedu.address.model.expense.exceptions.ExpenseNotFoundException;
import seedu.address.testutil.ExpenseBuilder;

public class UniqueExpenseListTest {

    private final UniqueExpenseList uniqueExpenseList = new UniqueExpenseList();

    @Test
    public void contains_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.contains(null));
    }

    @Test
    public void contains_expenseNotInList_returnsFalse() {
        assertFalse(uniqueExpenseList.contains(ANNUAL_SPOTIFY));
    }

    @Test
    public void contains_expenseInList_returnsTrue() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        assertTrue(uniqueExpenseList.contains(ANNUAL_SPOTIFY));
    }

    // Unique identity fields: all fields but ExpenseCategory
    @Test
    public void contains_expenseWithSameIdentityFieldsInList_returnsTrue() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        Expense editedSpotify = new ExpenseBuilder(ANNUAL_SPOTIFY).build();
        assertTrue(uniqueExpenseList.contains(editedSpotify));
    }

    @Test
    public void add_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.add(null));
    }

    @Test
    public void add_duplicateExpense_throwsDuplicateExpenseException() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        assertThrows(DuplicateExpenseException.class, () -> uniqueExpenseList.add(ANNUAL_SPOTIFY));
    }

    @Test
    public void setExpense_nullTargetExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpense(null, ANNUAL_SPOTIFY));
    }

    @Test
    public void setExpense_nullEditedExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList
                .setExpense(ANNUAL_SPOTIFY, null));
    }

    @Test
    public void setExpense_targetExpenseNotInList_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> uniqueExpenseList
                .setExpense(ANNUAL_SPOTIFY, ANNUAL_SPOTIFY));
    }

    @Test
    public void setExpense_editedExpenseIsSameExpense_success() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        uniqueExpenseList.setExpense(ANNUAL_SPOTIFY, ANNUAL_SPOTIFY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(ANNUAL_SPOTIFY);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasSameIdentity_success() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        Expense editedSpotify = new ExpenseBuilder(ANNUAL_SPOTIFY).withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
                .build();
        uniqueExpenseList.setExpense(ANNUAL_SPOTIFY, editedSpotify);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(editedSpotify);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasDifferentIdentity_success() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        uniqueExpenseList.setExpense(ANNUAL_SPOTIFY, ANNUAL_NETFLIX_FEES);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(ANNUAL_NETFLIX_FEES);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_editedExpenseHasNonUniqueIdentity_throwsDuplicateExpenseException() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        uniqueExpenseList.add(ANNUAL_NETFLIX_FEES);
        assertThrows(DuplicateExpenseException.class, (
        ) -> uniqueExpenseList.setExpense(ANNUAL_SPOTIFY, ANNUAL_NETFLIX_FEES));
    }

    @Test
    public void remove_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.remove(null));
    }

    @Test
    public void remove_expenseDoesNotExist_throwsExpenseNotFoundException() {
        assertThrows(ExpenseNotFoundException.class, () -> uniqueExpenseList.remove(ANNUAL_SPOTIFY));
    }

    @Test
    public void remove_existingExpense_removesExpense() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        uniqueExpenseList.remove(ANNUAL_SPOTIFY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    // setExpense_nullUniqueExpenseList() -> throwsNullPointerException()
    @Test
    public void setExpense_nullUniqueExpenseList() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpenses((UniqueExpenseList) null));
    }
    // setExpense_UniqueExpenseList -> replacesOwnListWithProvidedUniqueExpenseList
    @Test
    public void setExpense_uniqueExpenseList() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(ANNUAL_NETFLIX_FEES);
        uniqueExpenseList.setExpenses(expectedUniqueExpenseList);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueExpenseList.setExpenses((List<Expense>) null));
    }

    @Test
    public void setExpense_list_replacesOwnListWithProvidedList() {
        uniqueExpenseList.add(ANNUAL_SPOTIFY);
        List<Expense> expenseList = Collections.singletonList(ANNUAL_NETFLIX_FEES);
        uniqueExpenseList.setExpenses(expenseList);
        UniqueExpenseList expectedUniqueExpenseList = new UniqueExpenseList();
        expectedUniqueExpenseList.add(ANNUAL_NETFLIX_FEES);
        assertEquals(expectedUniqueExpenseList, uniqueExpenseList);
    }

    @Test
    public void setExpense_listWithDuplicateExpenses_throwsDuplicateExpenseException() {
        List<Expense> listWithDuplicateExpenses = Arrays.asList(ANNUAL_SPOTIFY, ANNUAL_SPOTIFY);
        assertThrows(DuplicateExpenseException.class, () -> uniqueExpenseList.setExpenses(listWithDuplicateExpenses));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueExpenseList
                .asUnmodifiableObservableList().remove(0));
    }
}
