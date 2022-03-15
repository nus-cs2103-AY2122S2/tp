package seedu.address.model.transaction;

import java.io.Serializable;

import seedu.address.logic.parser.Prefix;



public abstract class TransactionField implements Serializable {
    public final Prefix prefix;

    public TransactionField(Prefix prefix) {
        this.prefix = prefix;
    }

    public abstract String getValue();
}
