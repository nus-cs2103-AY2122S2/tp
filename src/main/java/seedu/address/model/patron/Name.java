package seedu.address.model.patron;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;

/**
 * Represents a Patron's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain up to a max of 40 alphanumeric characters and spaces, and should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        String[] nameArray = test.split("");
        return ((nameArray.length < 40) && test.matches(VALIDATION_REGEX));
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && getPatronNameForComparison().equals(((Name) other).getPatronNameForComparison())); // state check
    }

    /**
     * Returns the full name of the patron, converted to lowercase and without whitespaces.
     */
    private String getPatronNameForComparison() {
        StringBuilder builder = new StringBuilder();
        String[] splittedName = fullName.trim().toLowerCase().split("\\s+");
        Arrays.stream(splittedName).forEachOrdered(s -> builder.append(s));
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return getPatronNameForComparison().hashCode();
    }

}
