package seedu.trackermon.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the show list.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tag must be a keyword that is no more than"
            + " 20 alphanumeric characters";
    public static final String VALIDATION_REGEX = "\\p{Alnum}{1,20}?";

    public final String tagName;

    /**
     * Constructs a {@code Tag} with the provide {@code String}.
     * @param tagName provided {@code String}.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName.toLowerCase();
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
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    /**
     * Returns the hashcode of {@code Tag}
     */
    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
    }

}
