package woofareyou.model.pet;

import static java.util.Objects.requireNonNull;
import static woofareyou.commons.util.AppUtil.checkArgument;

/**
 * Represents a Pet's owner name in WoofAreYou.
 * Guarantees: immutable; is valid as declared in {@link #isValidOwnerName(String)}
 */
public class OwnerName implements Comparable<OwnerName> {

    public static final String MESSAGE_CONSTRAINTS =
            "Owner names should only contain alphabets and spaces, and should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[A-Za-z]+[A-Za-z ]*$";

    public final String value;


    /**
     * Constructs an {@code OwnerName}.
     *
     * @param ownerName A valid ownerName.
     */
    public OwnerName(String ownerName) {
        requireNonNull(ownerName);
        checkArgument(isValidOwnerName(ownerName), MESSAGE_CONSTRAINTS);
        value = ownerName.trim().replaceAll(" +", " ");
    }

    /**
     * Returns if a given string is a valid ownerName.
     */
    public static boolean isValidOwnerName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OwnerName // instanceof handles nulls
                && value.equals(((OwnerName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Compare two {@code ownerName} in lexicographical order
     * @param other {@code ownerName} to be compared with
     * @return an Integer
     */
    @Override
    public int compareTo(OwnerName other) {
        return Integer.compare(this.value.compareToIgnoreCase(other.value), 0);
    }

}
