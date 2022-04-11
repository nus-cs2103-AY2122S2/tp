package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Budget;
import seedu.address.model.expense.Date;

/**
 * Jackson-friendly version of {@link Budget}.
 */
class JsonAdaptedBudget {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Budget's %s field is missing!";

    private final String budgetAmount;
    private final String budgetDate;

    /**
     * Constructs a {@code JsonAdaptedBudget} with the given Budget details.
     */
    @JsonCreator
    public JsonAdaptedBudget(@JsonProperty("budgetAmount") String budgetAmount,
                             @JsonProperty("budgetDate") String budgetDate) {
        this.budgetAmount = budgetAmount;
        this.budgetDate = budgetDate;
    }

    /**
     * Converts a given {@code Budget} into this class for Jackson use.
     */
    public JsonAdaptedBudget(Budget source) {
        this.budgetAmount = source.getBudgetAmount().toString();
        this.budgetDate = source.getBudgetDate().date.toString();
    }

    /**
     * Converts this Jackson-friendly adapted Budget object into the model's {@code Budget} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Budget.
     */
    public Budget toModelType() throws IllegalValueException {
        if (budgetAmount == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(budgetAmount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelBudgetAmount = new Amount(budgetAmount);

        if (budgetDate == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(budgetDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelBudgetDate = new Date(budgetDate);


        return new Budget(modelBudgetAmount, modelBudgetDate);
    }
}
