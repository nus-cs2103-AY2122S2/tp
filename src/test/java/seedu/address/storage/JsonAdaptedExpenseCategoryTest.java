package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.TRANSPORT_CATEGORY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.ExpenseCategory;

class JsonAdaptedExpenseCategoryTest {

    private static final String INVALID_EXPENSE_CATEGORY = "Entert@inment";

    @Test
    void toModelType_validExpenseCategory_returnsExpenseCat() throws Exception {
        JsonAdaptedExpenseCategory expenseCategory = new JsonAdaptedExpenseCategory(TRANSPORT_CATEGORY);
        assertEquals(TRANSPORT_CATEGORY, expenseCategory.toModelType());
    }

    @Test
    public void toModelType_invalidExpenseCategory_throwsIllegalValueException() {
        JsonAdaptedExpenseCategory expenseCategory =
                new JsonAdaptedExpenseCategory(INVALID_EXPENSE_CATEGORY);
        String expectedMessage = ExpenseCategory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expenseCategory::toModelType);
    }


    //TODO: Test for null input once budget is added

}
