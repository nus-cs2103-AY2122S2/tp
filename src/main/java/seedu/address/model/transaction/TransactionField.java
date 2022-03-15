package seedu.address.model.transaction;

import seedu.address.logic.parser.Prefix;

import java.io.Serializable;

public abstract class TransactionField implements Serializable {
    public final Prefix prefix;

    public TransactionField(Prefix prefix) {
        this.prefix = prefix;
    }

    abstract public String getValue();
}
