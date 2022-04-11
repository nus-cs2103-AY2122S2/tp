package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PersonAmount {

    /**
     * Only accepts valid amount format, amount must be in numerical value and cannot be in range or else it cannot be
     * converted into float for summation.
     */
    public static final String VALIDATION_REGEX =
            "^\\$?([1-9]{1}[0-9]{0,2}(\\,[0-9]{3})*(\\.[0-9]{0,2})?|[1-9]{1}[0-9]{0,}"
                    + "(\\.[0-9]{0,2})?|0(\\.[0-9]{0,2})?|(\\.[0-9]{1,2})?)$";

    public static final String MESSAGE_CONSTRAINTS =
            "Amount should only contain numerical characters and decimal, in valid number format, "
                    + "and should not be empty";

    public final Double personAmount;

    /**
     * Constructs a (@code Amount).
     *
     * @param personAmount A valid amount.
     */
    public PersonAmount(String personAmount) {
        requireNonNull(personAmount);
        checkArgument(isValidPersonAmount(personAmount), MESSAGE_CONSTRAINTS);
        this.personAmount = Double.valueOf(personAmount.replace(",", ""));
    }

    /**
     * Returns true if a given string is a valid amount.
     */
    public static boolean isValidPersonAmount(String test) {
        return test.matches(VALIDATION_REGEX) && test != "";
    }

    @Override
    public String toString() {
        return String.format("%.2f", personAmount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonAmount // instanceof handles nulls
                && personAmount.equals(((PersonAmount) other).personAmount)); // state check
    }
}
