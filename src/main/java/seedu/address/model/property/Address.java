package seedu.address.model.property;

import java.util.Objects;

public class Address {

    public static final String MESSAGE_CONSTRAINTS = "Address should not be empty";

    private String address;

    public Address(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && address.equals(((Address) other).address)); // state check
    }

    /**
     * Checks if the location string is empty or not.
     *
     * @param address The string.
     * @return False if string is empty, True otherwise.
     */
    public static boolean isValidAddress(String address) {
        return !address.isEmpty();
    }
}
