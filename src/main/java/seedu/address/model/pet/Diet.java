package seedu.address.model.pet;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Pet's diet in the address book.
 * Guarantees: immutable; is always valid
 */
public class Diet {
    public final String value;

    /**
     * Constructs a {@code Diet}.
     *
     * @param diet A pet's diet.
     */
    public Diet(String diet) {
        requireNonNull(diet);
        value = diet.trim().replaceAll(" +", " ");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Diet
                && value.equals(((Diet) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
