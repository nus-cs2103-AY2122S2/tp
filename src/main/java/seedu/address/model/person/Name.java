package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Represents a Person's name in Tracey.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final int MAXIMUM_NAME_CHARACTERS_LENGTH = 60;
    public static final String MESSAGE_CONSTRAINTS_FORMAT =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_CHARACTER_LENGTH =
            String.format("Names should not contain more than %d characters", MAXIMUM_NAME_CHARACTERS_LENGTH);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;
    public final String searchName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        searchName = addNameFormat(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS_FORMAT);
        fullName = name;
    }

    /**
     * Return the combination of name for searching purpose
     * @param name a person name (family name with given name)
     * @return string of all combination of name string (John -> J, Jo, Joh, John)
     */
    public String addNameFormat (String name) {
        List<String> combinationArray = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            combinationArray.add(" " + name.substring(0, i + 1) + " ");
        }
        return Arrays.toString(combinationArray.toArray());
    }

    /**
     * Returns true if a given string is a valid name.
     *
     * @param test string to be tested to determine if valid name.
     * @return boolean where true if a given string is a valid name, false otherwise.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a less than the maximum allowed name characters.
     *
     * @param test string to be tested to determine if valid name length in characters.
     * @return boolean where true if a given string is a valid name in characters, false otherwise.
     */
    public static boolean isValidNameLength(String test) {
        return test.length() < MAXIMUM_NAME_CHARACTERS_LENGTH + 1;
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
