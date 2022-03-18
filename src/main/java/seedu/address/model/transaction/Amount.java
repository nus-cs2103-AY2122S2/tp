package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.Prefix;

public class Amount extends TransactionField {

    public static final String FIELD_NAME = "Amount";

    public static final Prefix PREFIX = new Prefix("a/", true);

    public static final String MESSAGE_CONSTRAINT = "Transaction amount must be "
            + "numeric and the number specified "
            + "must be greater than 0 (zero)";

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
        this.value = Double.parseDouble(value);
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
            return Double.parseDouble(value) > 0;
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
        return new Double(value).hashCode();
    }
}
