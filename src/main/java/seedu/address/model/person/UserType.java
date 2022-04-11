package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a User Type in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidUserType(String)}
 */
public class UserType {

    public static final String BUYER_REGEX = "buyer";
    public static final String SELLER_REGEX = "seller";
    public static final String VALIDATION_REGEX = BUYER_REGEX + "|" + SELLER_REGEX;
    public static final String MESSAGE_CONSTRAINTS = "User type name should either be \"buyer\" or \"seller\" ";
    public static final String MESSAGE_USAGE = "Either property or preference is inputted, not both or none!";

    public final String value;

    /**
     * Constructs a {@code UserType}.
     *
     * @param value A valid User Type name.
     */
    public UserType(String value) {
        requireNonNull(value);
        checkArgument(isValidUserType(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid User Type name.
     */
    public static boolean isValidUserType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the current User Type is a buyer.
     */
    public boolean isBuyer() {
        return value.matches(BUYER_REGEX);
    }

    /**
     * Returns true if the current User Type is a buyer.
     */
    public boolean isSeller() {
        return value.matches(SELLER_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserType // instanceof handles nulls
                && value.equals(((UserType) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return value;
    }

}
