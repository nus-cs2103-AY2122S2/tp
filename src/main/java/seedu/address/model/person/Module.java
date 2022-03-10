package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person's cca tag in the address book.
 * Guarantees: immutable; is always valid
 */
public class Module extends Tag {
    public final String value;

    /**
     * Constructs a {@code Module}.
     *
     * @param module a module tag.
     */
    public Module(String module) {
        super(requireNonNull(module));
        this.value = module.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Module
                && this.value.equals(((Module) other).value));
    }
}
