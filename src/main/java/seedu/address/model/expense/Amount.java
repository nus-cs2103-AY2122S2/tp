package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a monetary value's amount (2 decimal place) in the Expense Expert.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount {
    /**
     * Only accepts valid amount format, amount must be in numerical value and cannot be in range or else it cannot be
     * converted into float for summation.
     */
    public static final String VALIDATION_REGEX =
            "^\\$?-?([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}"
                    + "(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$";

    public static final String MESSAGE_CONSTRAINTS =
            "Amount should only contain numerical characters and decimal, "
                    + "in valid number format, and should not be empty";

    public final Double amount;

    /**
     * Constructs a (@code Amount).
     *
     * @param amount A valid amount.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = Double.valueOf(amount.replace(",", ""));
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX) && test != "";
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amount.equals(((Amount) other).amount)); // state check
    }
}
