package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.ExpenseCategory;

/**
 * Jackson-friendly version of {@link Expense}.
 */
class JsonAdaptedExpense {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final String description;
    private final String expenseCategory;
    private final String amount;
    private final String expenseDate;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense details.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("description") String description,
                              @JsonProperty("expenseCategory") String expenseCategory,
                             @JsonProperty("amount") String amount, @JsonProperty("expenseDate") String expenseDate) {
        this.description = description;
        this.expenseCategory = expenseCategory;
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Expense source) {
        description = source.getDescription().description;
        expenseCategory = source.getExpenseCategory().expenseCategory;
        amount = source.getAmount().toString();
        expenseDate = source.getExpenseDate().date.toString();
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Expense toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (expenseCategory == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ExpenseCategory.class.getSimpleName()));
        }
        if (!ExpenseCategory.isValidExpenseCategory(expenseCategory)) {
            throw new IllegalValueException(ExpenseCategory.MESSAGE_CONSTRAINTS);
        }
        final ExpenseCategory modelExpenseCategory = new ExpenseCategory(expenseCategory);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (expenseDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(expenseDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(expenseDate);

        return new Expense(modelDescription, modelExpenseCategory, modelAmount, modelDate);
    }

}
