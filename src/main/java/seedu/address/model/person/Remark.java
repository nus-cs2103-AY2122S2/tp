package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Remark extends Field {
    public static final Prefix PREFIX = new Prefix("r/", false);
    public final String value;

    /**
     * Constructs a {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        super(PREFIX);
        requireNonNull(remark);
        value = remark.trim();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Remark // instanceof handles nulls
            && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Field other) {
        if (!(other instanceof Remark)) {
            return -1;
        }

        Remark otherRemark = (Remark) other;
        return value.toLowerCase().compareTo(otherRemark.value.toLowerCase());
    }
}
