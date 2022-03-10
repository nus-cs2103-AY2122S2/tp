package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a Person's name in the address book.
 * The input (person's name) will converted to "title case" when creating a Name object.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
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
        fullName = Name.convertToTitleCase(name);
    }

    /**
     * Converts the given name to title case.
     *
     * @param name the name of the person.
     * @return the persons name in title case.
     */
    private static String convertToTitleCase(String name) {

        String delimiter = " ";
        int firstCharIdx = 0;
        int secondCharIdx = 1;
        int onlyOneChar = 1;

        if (name.isEmpty()) {
            return name;
        }

        // Solution adapted from https://www.baeldung.com/java-string-title-case.
        return Arrays.stream(name.split(delimiter))
                .map(x -> x.length() != onlyOneChar
                     ? (x.substring(firstCharIdx, secondCharIdx).toUpperCase()
                         + x.substring(secondCharIdx).toLowerCase())
                     : x.substring(firstCharIdx, secondCharIdx).toUpperCase())
                .collect(Collectors.joining(delimiter));
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test the string that is being tested.
     * @return a boolean value (true/false) depending if the name is valid.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
