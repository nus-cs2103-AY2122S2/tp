package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's Membership in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Membership extends Field {
    public static final Prefix PREFIX = new Prefix("m/", true);
    public static final String MESSAGE_CONSTRAINTS =
            "Membership names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String value;

    /**
     * Constructs a {@code Membership}.
     *
     * @param name A valid membership.
     */
    public Membership(String name) {
        super(PREFIX);
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Membership // instanceof handles nulls
                && value.equals(((Membership) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
