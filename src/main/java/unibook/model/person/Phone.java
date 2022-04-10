package unibook.model.person;

import static java.util.Objects.requireNonNull;

import unibook.commons.util.AppUtil;

/**
 * Represents a Person's phone number in the UniBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {


    public static final String MESSAGE_CONSTRAINTS =
        "Phone numbers should only contain numbers, and it should be between 7 - 15 digits long.";
    public static final String VALIDATION_REGEX = "\\d{7,15}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        AppUtil.checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Constructs an empty {@code Phone}
     */
    public Phone() {
        value = "";
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the phone field is empty.
     */
    public boolean isEmpty() {
        return value.equals("");
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Phone // instanceof handles nulls
            && value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}