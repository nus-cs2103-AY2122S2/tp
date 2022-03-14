package seedu.contax.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag's name in the address book.
 * Guarantees: Immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {
    public static final String MESSAGE_CONSTRAINTS = "Tags names should only contain alphanumeric characters and "
            + "spaces.";

    public static final String VALIDATION_REGEX = "(\\p{Alnum}+[ ]?)+";

    private final String name;

    /**
     * Constructs a {@code Name}.
     * @param name A valid name, as defined in {@link #isValidName(String)}.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name.toLowerCase();
    }

    public static boolean isValidName(String name) {
        return name.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Name)) {
            return false;
        }

        return ((Name) o).name.equals(this.name);
    }
}
