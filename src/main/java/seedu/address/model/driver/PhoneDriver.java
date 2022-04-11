package seedu.address.model.driver;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class PhoneDriver {


    public static final String MESSAGE_CONSTRAINTS =
            "Driver phone number should only contain numbers, and it should be only 8 digits long and start"
                    + " with 8 or 9";

    public static final String VALIDATION_REGEX = "[8-9][0-9]{7}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public PhoneDriver(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneDriver // instanceof handles nulls
                && value.equals(((PhoneDriver) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}