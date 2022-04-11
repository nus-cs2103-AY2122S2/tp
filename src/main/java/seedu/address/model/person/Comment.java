package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Comment implements Comparable<Comment> {

    /*
     * The first character of the comment must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final int MAX_LENGTH = 60;
    public final String value;

    /**
     * Constructor for a comment
     * Represents a comment for a Person.
     *
     * @param comment
     */
    public Comment(String comment) {
        requireNonNull(comment);
        value = comment;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid comment.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Gets the length of the comment
     * @return the length of the comment string
     */
    public int getLength() {
        return this.value.length();
    }

    public boolean isEmpty() {
        return value.equals("");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Comment // instanceof handles nulls
                && value.equals(((Comment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Comment o) {
        return this.value.length() - o.value.length();
    }
}
