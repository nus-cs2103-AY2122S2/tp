package seedu.address.model.transaction;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.Prefix;


public class Amount extends TransactionField {

    public static final String FIELD_NAME = "Amount";

    public static final Double MAXIMUM_INPUT = 9999999.99;

    public static final Prefix PREFIX = new Prefix("a/", true);

    public static final String MESSAGE_CONSTRAINT = "Transaction amount must be "
            + "numeric and the number specified "
            + "must be between 0 (zero) and 9999999.99 inclusive";

    private final double value;

    /**
     * Constructs the amount object
     *
     * @param value the value of the amount
     */
    public Amount(String value) {
        super(PREFIX);
        requireNonNull(value);
        checkArgument(isValid(value), MESSAGE_CONSTRAINT);
        double number = Double.parseDouble(value);
        String formattedNumber = String.format("%.2f", number);
        number = Double.parseDouble(formattedNumber);
        this.value = Math.abs(number);
    }

    private String generateStringRep() {
        return FIELD_NAME + ": " + this.value;
    }

    @Override
    public String toString() {
        return generateStringRep();
    }

    @Override
    public String getValue() {
        return "" + this.value;
    }

    /**
     *  Checks the validity of the amount inserted
     *
     * @param value of the transaction
     * @return whether or not the amount is valid
     */
    public static boolean isValid(String value) {
        try {
            double number = Double.parseDouble(value);
            return number >= 0 && number <= MAXIMUM_INPUT;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Amount)) {
            return false;
        }
        return ((Amount) other).getValue().equals(getValue());
    }

    @Override
    public int hashCode() {
        return hash(value);
    }
}
