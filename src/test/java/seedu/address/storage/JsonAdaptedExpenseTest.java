package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExpenses.BASEBALL_LESSON_FEES;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.ExpenseCategory;

public class JsonAdaptedExpenseTest {

    private static final String INVALID_AMOUNT = "#1";
    private static final String INVALID_DESCRIPTION = "@venture Cove";
    private static final String INVALID_EXPENSE_CATEGORY = "Entert@inment";
    private static final String INVALID_EXPENSE_DATE = "2022-30-30";

    private static final String VALID_DESCRIPTION = BASEBALL_LESSON_FEES.getDescription().toString();
    private static final String VALID_EXPENSE_CATEGORY = BASEBALL_LESSON_FEES.getExpenseCategory().toString();
    private static final String VALID_AMOUNT = BASEBALL_LESSON_FEES.getAmount().toString();
    private static final String VALID_EXPENSE_DATE = BASEBALL_LESSON_FEES.getExpenseDate().toString();

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(BASEBALL_LESSON_FEES);
        assertEquals(BASEBALL_LESSON_FEES, expense.toModelType());
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_EXPENSE_CATEGORY, INVALID_AMOUNT, VALID_EXPENSE_DATE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidExpenseDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_EXPENSE_CATEGORY, VALID_AMOUNT, INVALID_EXPENSE_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_DESCRIPTION, VALID_EXPENSE_CATEGORY, VALID_AMOUNT, VALID_EXPENSE_DATE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidExpenseCategory_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_DESCRIPTION, INVALID_EXPENSE_CATEGORY, VALID_AMOUNT, VALID_EXPENSE_DATE);
        String expectedMessage = ExpenseCategory.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(null, VALID_EXPENSE_CATEGORY,
                VALID_AMOUNT, VALID_EXPENSE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullExpenseCategory_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_DESCRIPTION, null, VALID_AMOUNT,
                VALID_EXPENSE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpenseCategory.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_EXPENSE_CATEGORY, null,
                VALID_EXPENSE_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullExpenseDate_throwsIllegalValueException() {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(VALID_DESCRIPTION, VALID_EXPENSE_CATEGORY,
                VALID_AMOUNT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }


}
