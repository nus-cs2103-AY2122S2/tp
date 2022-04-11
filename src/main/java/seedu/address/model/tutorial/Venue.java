package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the venue which a Tutorial is hosted at in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVenue(String)}
 */
public class Venue {

    public static final String MESSAGE_CONSTRAINTS = "Venue can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Venue}.
     *
     * @param address A valid venue.
     */
    public Venue(String address) {
        requireNonNull(address);
        checkArgument(isValidVenue(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid venue.
     */
    public static boolean isValidVenue(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tutorial.Venue // instanceof handles nulls
                && value.equals(((seedu.address.model.tutorial.Venue) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
