package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's cca tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Cca extends Tag {
    public final String value;

    /**
     * Constructs a {@code Cca}.
     *
     * @param cca a cca tag.
     */
    public Cca(String cca) {
        super(requireNonNull(cca));
        this.value = cca.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Cca
                && this.value.equals(((Cca) other).value));
    }
}
