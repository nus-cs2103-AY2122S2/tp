package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long.";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        if (phone == null) {
            value = null;
        } else {
            checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
            value = phone;
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the phone number is valid.
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
        if (other == this) { // short circuit if same object
            return true;
        }
        if (!(other instanceof Phone)) { // target object is of different type
            return false;
        }

        Phone targetObject = (Phone) other;

        if (targetObject.value == null && this.value == null) {
            return true;
        } else if (targetObject.value == null) {
            return false;
        } else if (this.value == null) {
            return false;
        } else {
            return value.equals(targetObject.value);
        }
    }

    @Override
    public int hashCode() {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

}
