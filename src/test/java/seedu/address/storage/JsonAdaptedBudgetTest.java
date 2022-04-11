package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedBudget.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBudgets.OMEGA_BUDGET;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;

public class JsonAdaptedBudgetTest {
    private static final String INVALID_BUDGET_AMOUNT = "#1";
    private static final String INVALID_BUDGET_DATE = "2022-30-30";

    private static final String VALID_BUDGET_AMOUNT = OMEGA_BUDGET.getBudgetAmount().toString();
    private static final String VALID_BUDGET_DATE = OMEGA_BUDGET.getBudgetDate().toString();

    @Test
    public void toModelType_validBudgetDetails_returnsBudget() throws Exception {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(OMEGA_BUDGET);
        assertEquals(OMEGA_BUDGET, budget.toModelType());
    }

    @Test
    public void toModelType_invalidBudgetAmount_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(INVALID_BUDGET_AMOUNT, VALID_BUDGET_DATE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, budget::toModelType);
    }

    @Test
    public void toModelType_invalidBudgetDate_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_BUDGET_AMOUNT, INVALID_BUDGET_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, budget::toModelType);
    }

    @Test
    public void toModelType_nullBudgetAmount_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(null, VALID_BUDGET_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, budget::toModelType);
    }

    @Test
    public void toModelType_nullBudgetDate_throwsIllegalValueException() {
        JsonAdaptedBudget budget = new JsonAdaptedBudget(VALID_BUDGET_AMOUNT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, budget::toModelType);
    }
}
