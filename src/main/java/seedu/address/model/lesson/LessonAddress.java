package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the address of the location where the lesson will be held.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class LessonAddress {
    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values and are capped at 100 characters.";
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public static final LessonAddress EMPTY_ADDRESS = new LessonAddress();

    public final String value;

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public LessonAddress(String address) {
        requireNonNull(address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Constructs an empty {@code Address}.
     */
    public LessonAddress() {
        value = "NO ADDRESS ASSIGNED";
    }

    /**
     * Returns true if a given string is a valid address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX) && test.length() <= 100;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonAddress // instanceof handles nulls
                && value.equals(((LessonAddress) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
