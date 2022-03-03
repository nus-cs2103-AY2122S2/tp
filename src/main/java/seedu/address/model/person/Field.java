package seedu.address.model.person;

import seedu.address.logic.parser.Prefix;

public abstract class Field {
    public final Prefix prefix;

    Field(Prefix prefix) {
        this.prefix = prefix;
    }

    @Override
    public abstract String toString();
}
