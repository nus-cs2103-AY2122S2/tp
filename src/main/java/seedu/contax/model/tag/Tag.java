package seedu.contax.model.tag;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public final Name tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        this.tagName = new Name(tagName);
    }

    /**
     * Constructs a {@code Tag} with a given {@code Name} object.
     */
    public Tag(Name tagName) {
        requireNonNull(tagName);
        this.tagName = tagName;
    }

    /**
     * Returns the tag name in {@code String} form.
     */
    public String getTagNameString() {
        return tagName.toString();
    }

    /**
     * Returns the tag name as a {@code Name} object.
     */
    public Name getTagName() {
        return tagName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return Name.isValidName(test);
    }

    /**
     * Checks if both tags have the same name.
     * This defines a weaker notion of equality between two tags.
     * @param otherTag The specified tag to check.
     * @return true if both tags are the same.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == this) {
            return true;
        }

        return otherTag != null && otherTag.getTagName().equals(tagName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName.toString() + ']';
    }

}
