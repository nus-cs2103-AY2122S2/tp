package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.ExpenseCategory;

public class JsonAdaptedExpenseCategory {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense Category List %s is missing!";

    private final String expenseCategory;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense category details.
     */
    @JsonCreator
    public JsonAdaptedExpenseCategory(@JsonProperty("expenseCategory") String expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedExpenseCategory(ExpenseCategory source) {
        expenseCategory = source.expenseCategory;
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public ExpenseCategory toModelType() throws IllegalValueException {

        if (expenseCategory == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ExpenseCategory.class.getSimpleName()));
        }
        if (!ExpenseCategory.isValidExpenseCategory(expenseCategory)) {
            throw new IllegalValueException(ExpenseCategory.MESSAGE_CONSTRAINTS);
        }

        return new ExpenseCategory(expenseCategory);

    }

}
