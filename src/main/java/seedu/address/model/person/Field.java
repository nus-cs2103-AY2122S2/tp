package seedu.address.model.person;

import java.io.Serializable;

import seedu.address.logic.parser.Prefix;

public abstract class Field implements Serializable, Comparable<Field> {
    public final Prefix prefix;

    Field(Prefix prefix) {
        this.prefix = prefix;
    }

    public abstract String getValue();
}
