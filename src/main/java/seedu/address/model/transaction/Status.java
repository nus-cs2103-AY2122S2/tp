package seedu.address.model.transaction;

import seedu.address.logic.parser.Prefix;

public class Status extends TransactionField {

    public static final String FIELD_NAME = "Status";

    public static final Prefix PREFIX = new Prefix("--paid", false, true);

    private final boolean isPaid;

    /**
     * Parses and constructs a status object
     */
    public Status(String isPaid) {
        super(PREFIX);
        this.isPaid = Boolean.parseBoolean(isPaid);
    }

    @Override
    public String getValue() {
        return isPaid ? "Paid" : "Not Paid";
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String generateStringRep() {
        return FIELD_NAME + ": " + generateStringRep();
    }

    @Override
    public String toString() {
        return generateStringRep();
    }
}
