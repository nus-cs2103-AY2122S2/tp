package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's name in the hustle book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and"
                    + " single spaces between each name, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]+( [a-zA-Z0-9_]+)*$";

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
        return test.matches(VALIDATION_REGEX);
    }

    public int compare(Name otherName) {
        return this.fullName.compareToIgnoreCase(otherName.fullName);
    }

    /**
     * Returns true the full name matches a given string.
     */
    public boolean containsKeyword(String test) {
        if (test.equals("")) {
            return false;
        } else {
            String[] keywords = test.split(" ");
            String[] names = this.fullName.split(" ");
            List<String> nameList = Arrays.asList(names);
            if (keywords.length == 1) {
                return nameList.stream().anyMatch(name -> containsSingleWord(name, test));
            } else {
                return containsMultipleWords(names, keywords);
            }
        }
    }

    private boolean containsMultipleWords(String[] names, String[] keywords) {
        String keyword = keywords[0];
        int index = 0;
        String name = names[index];

        while (!name.equalsIgnoreCase(keyword)) {
            index++;
            if (index == names.length) {
                return false;
            }
            name = names[index];
        }

        for (int i = 1; i < keywords.length; i++) {
            if (index + i > names.length) {
                return false;
            }

            name = names[index + 1];
            keyword = keywords[i];
            if (!name.equalsIgnoreCase(keyword)) {
                return false;
            }
        }
        return true;
    }

    private boolean containsSingleWord(String name, String test) {
        return name.equalsIgnoreCase(test);
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && fullName.equalsIgnoreCase(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
