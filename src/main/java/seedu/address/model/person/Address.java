package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final int CHARACTER_LIMIT = 800;
    public static final String MESSAGE_CONSTRAINTS = "Addresses should not be blank or start with a space, "
            + "and it cannot exceed " + CHARACTER_LIMIT + " characters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].{0," + (CHARACTER_LIMIT - 1) + "}";

    public final String address;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        this.address = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && address.equalsIgnoreCase(((Address) other).address)); // case-insensitive
    }

    /**
     * Returns true if both addresses are identical (case-sensitive).
     *
     * @param otherAddress The other address.
     * @return true if both addresses are identical.
     */
    public boolean exactEquals(Address otherAddress) {
        return otherAddress != null
                && address.equals(otherAddress.address);
    }

    @Override
    public int hashCode() {
        return address.toLowerCase().hashCode();
    }
}
