package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.StringUtil.isSameStringIgnoreCases;

/**
 * Represents a Tag in the task list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag implements Comparable<Tag> {

    public static final String MESSAGE_CONSTRAINTS = "Tags should only contain alphanumeric characters, "
            + "it should not be blank or contain spaces, and the length should be less than 51 characters.";

    /* The first character of the tag name must not be a whitespace,
            * and only alphanumeric characters are allowed.
            * The maximum number of characters allowed is 50.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]{1,50}";

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && isSameStringIgnoreCases(tagName, ((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        requireNonNull(this.tagName);
        return tagName.toLowerCase().hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    @Override
    public int compareTo(Tag other) {
        return tagName.compareToIgnoreCase(other.tagName);
    }
}
